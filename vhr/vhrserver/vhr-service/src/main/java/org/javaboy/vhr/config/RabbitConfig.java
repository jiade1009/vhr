package org.javaboy.vhr.config;

import org.javaboy.vhr.model.MailConstants;
import org.javaboy.vhr.service.MailSendLogService;
import org.javaboy.vhr.service.StockMessageLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Configuration
public class RabbitConfig {
    public final static Logger logger = LoggerFactory.getLogger(RabbitConfig.class);
    @Resource
    CachingConnectionFactory cachingConnectionFactory;
    @Resource
    MailSendLogService mailSendLogService;
    @Resource
    StockMessageLogService stockMessageLogService;

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                logger.info(msgId + ":消息发送成功到Exchange");
                mailSendLogService.updateMailSendLogStatus(msgId, 1);//修改数据库中的记录，消息投递成功
            } else {
                logger.info(msgId + ":消息发送失败");
            }
        });
        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        // 此处和spring.rabbitmq.template.mandatory=true效果一样
        rabbitTemplate.setMandatory(true);
        /**
         * 消息路由失败，回调，消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
         * 消息(带有路由键routingKey)到达交换机，与交换机的所有绑定键进行匹配，匹配不到触发回调
         */
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            String exchange = returnedMessage.getExchange();
            String routingKey = returnedMessage.getRoutingKey();
            //String queue = returnedMessage.getMessage().getMessageProperties().getConsumerQueue();
            logger.info("消息路由失败（Exchange->Queue)：消息从" + exchange + "到路由key为" + routingKey);
            System.out.println("消息为：" + new String(returnedMessage.getMessage().getBody(), StandardCharsets.UTF_8));
        });

        /*rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            logger.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}",
                    exchange, routingKey, replyCode, replyText, message);
        });*/
        return rabbitTemplate;
    }

    @Bean
    RabbitTemplate stockRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                logger.info(msgId + ":消息发送成功到Exchange");
                stockMessageLogService.updateStockMessageLogStatus(msgId, 1);//修改数据库中的记录，消息投递成功
            } else {
                logger.info(msgId + ":消息发送失败");
            }
        });
        rabbitTemplate.setMandatory(true);
        /**
         * 消息路由失败，回调，消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
         * 消息(带有路由键routingKey)到达交换机，与交换机的所有绑定键进行匹配，匹配不到触发回调
         */
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            String exchange = returnedMessage.getExchange();
            String routingKey = returnedMessage.getRoutingKey();
            //String queue = returnedMessage.getMessage().getMessageProperties().getConsumerQueue();
            logger.info("消息路由失败（Exchange->Queue)：消息从" + exchange + "到路由key为" + routingKey);
            System.out.println("消息为：" + new String(returnedMessage.getMessage().getBody(), StandardCharsets.UTF_8));
        });
        return rabbitTemplate;
    }

    @Bean
    Queue mailQueue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange mailExchange() {
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }

    @Bean
    Queue stockQueue() {
        return new Queue(MailConstants.STOCK_QUEUE_NAME, true);
    }

    /**
     * 可以考虑共用一个Exchange交换机，目前暂时分开使用
     * @return
     */
    @Bean
    DirectExchange stockExchange() {
        return new DirectExchange(MailConstants.STOCK_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding stockBinding() {
        return BindingBuilder.bind(stockQueue()).to(stockExchange()).with(MailConstants.STOCK_ROUTING_KEY_NAME);
    }

}
