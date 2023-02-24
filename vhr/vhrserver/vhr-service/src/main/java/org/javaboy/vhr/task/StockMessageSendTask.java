package org.javaboy.vhr.task;

import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.RabbitBean;
import org.javaboy.vhr.model.StockMessageLog;
import org.javaboy.vhr.model.util.MessageType;
import org.javaboy.vhr.service.EmployeeService;
import org.javaboy.vhr.service.StockMessageLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@PropertySource("classpath:/application.yml")
public class StockMessageSendTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockMessageSendTask.class);
    @Autowired
    StockMessageLogService stockMessageLogService;
    @Autowired
    RabbitTemplate stockRabbitTemplate;
    @Autowired
    EmployeeService employeeService;

    @Async("stockMessageAsync")
    @Scheduled(cron = "${task.cron.StockMessageSend.resend}")
    public void resend() {
        LOGGER.debug("-----------begin StockMessageSend task------------");
        List<StockMessageLog> logs = stockMessageLogService.getStockMessageLogByStatus(0);
        if (logs == null || logs.size() == 0) {
            return;
        }
        logs.forEach(log -> {
            if (log.getCount() >= 3) {
                stockMessageLogService.updateStockMessageLogStatus(log.getMsgid(), 2);//直接设置该条消息发送失败
            } else {
                Employee emp = employeeService.getEmployeeById(log.getEmpid());
                RabbitBean bean = new RabbitBean(emp, log.getContent(),
                        MessageType.getMessageType(log.getMessageType()), log.getSendType(), log.getFlag());
                stockRabbitTemplate.convertAndSend(log.getExchange(), log.getRoutekey(), bean, new CorrelationData(log.getMsgid()));
                stockMessageLogService.updateCount(log.getMsgid(), new Date());
            }
        });
    }
}
