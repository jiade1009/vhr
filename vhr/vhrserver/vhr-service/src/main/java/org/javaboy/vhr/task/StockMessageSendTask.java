package org.javaboy.vhr.task;

import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.MailConstants;
import org.javaboy.vhr.model.RabbitBean;
import org.javaboy.vhr.model.StockMessageLog;
import org.javaboy.vhr.service.EmployeeService;
import org.javaboy.vhr.service.StockMessageLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StockMessageSendTask {

    @Autowired
    StockMessageLogService stockMessageLogService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    EmployeeService employeeService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void messageResendTask() {
        List<StockMessageLog> logs = stockMessageLogService.getStockMessageLogByStatus();
        if (logs == null || logs.size() == 0) {
            return;
        }
        logs.forEach(log -> {
            if (log.getCount() >= 3) {
                stockMessageLogService.updateStockMessageLogStatus(log.getMsgid(), 2);//直接设置该条消息发送失败
            } else {
                stockMessageLogService.updateCount(log.getMsgid(), new Date());
                Employee emp = employeeService.getEmployeeById(log.getEmpid());
                RabbitBean bean = new RabbitBean(emp, log.getMessageType(), log.getSendType());
                rabbitTemplate.convertAndSend(MailConstants.STOCK_EXCHANGE_NAME, MailConstants.STOCK_ROUTING_KEY_NAME,
                        bean, new CorrelationData(log.getMsgid()));
            }
        });
    }
}
