package org.javaboy.mailserver.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.MailConstants;
import org.javaboy.vhr.model.RabbitBean;
import org.javaboy.vhr.model.util.CommandType;
import org.javaboy.vhr.model.util.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Component
public class MailReceiver {

    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    StringRedisTemplate redisTemplate;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel) throws IOException {
        Employee employee = (Employee) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");
        if (redisTemplate.opsForHash().entries("mail_log").containsKey(msgId)) {
            //redis 中包含该 key，说明该消息已经被消费过
            logger.info(msgId + ":消息已经被消费");
            channel.basicAck(tag, false);//确认消息已消费
            return;
        }
        //收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setTo(employee.getEmail());
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject("入职欢迎");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("joblevelName", employee.getJobLevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail, true);
            javaMailSender.send(msg);
            redisTemplate.opsForHash().put("mail_log", msgId, "javaboy");
            channel.basicAck(tag, false);
            logger.info(msgId + ":邮件发送成功");
        } catch (MessagingException e) {
            channel.basicNack(tag, false, true);
            e.printStackTrace();
            logger.error("邮件发送失败：" + e.getMessage());
        }
    }


    @RabbitListener(queues = MailConstants.STOCK_QUEUE_NAME)
    public void handlerStockMessage(Message message, Channel channel) throws IOException {
        //获取消息体bean
        RabbitBean bean = (RabbitBean) message.getPayload();
        Employee employee = bean.getEmployee();
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");

        if (redisTemplate.opsForHash().entries("mail_log").containsKey(msgId)) {
            //redis 中包含该 key，说明该消息已经被消费过
            logger.info(msgId + ":消息已经被消费");
            channel.basicAck(tag, false);//确认消息已消费
            return;
        }

        // 根据messageType判断进行不同的内容发送，0信号发现、1买入、2卖出
        MessageType messageType = bean.getMessageType();
        Integer sendType = bean.getSendType();  //（0短信、1邮件、2微信服务通知）
        String flag = bean.getFlag();
        if (sendType == 1) { //邮件发送
            String title = bean.getTitle();
//            String title = messageType.getName();
//            if (!(flag == null || flag.trim().equals(""))) {
//                title = flag + "股" + title;
//            }
            String content = bean.getContent();
            if (messageType == MessageType.INSPECTION) {
                ObjectMapper mapper = new ObjectMapper();
                Map map = mapper.readValue(content, Map.class);
                StringBuilder sbd = new StringBuilder("");
                for (Object key : map.keySet()) {
                    CommandType type = CommandType.valueOf((String)key);
                    Boolean b = (Boolean) map.get(key);
                    sbd.append("【" + type.getCommandDesc() + "】");
                    if (b) {
                        sbd.append("脚本命令已执行<br/>");
                    } else {
                        sbd.append("脚本命令暂未执行<br/>");
                    }
                }
                content = sbd.toString();
            }
            sendStockMail(employee, channel, title, content, tag, msgId);
        } else if (sendType == 0) {

        } else if (sendType == 2) {

        }

//        if (messageType == 0) {
//
//        } else if (messageType == 1) {
//
//        } else if (messageType == 2) {
//
//        } else {
//            logger.error("stock message发送失败：messageType 不合法 {}" + messageType);
//        }


    }

    private void sendStockMail(Employee employee, Channel channel, String title, String content, Long tag, String msgId) throws IOException {
        //收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            Date now = new Date();
            helper.setTo(employee.getEmail());
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject(title);
            helper.setSentDate(now);
            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("content", content);
            context.setVariable("label", title);
            String mail = templateEngine.process("mail_stock_discover", context);
            helper.setText(mail, true);
            javaMailSender.send(msg);
            redisTemplate.opsForHash().put("mail_log", msgId, "ghk");
            channel.basicAck(tag, false);
            logger.info(msgId + ": 邮件发送成功");
        } catch (MessagingException e) {
            /**
             * deliveryTag:该消息的index
             * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
             * requeue：被拒绝的是否重新入队列
             */
            channel.basicNack(tag, false, true);
            e.printStackTrace();
            logger.error("邮件发送失败：" + e.getMessage());
        }
    }
}
