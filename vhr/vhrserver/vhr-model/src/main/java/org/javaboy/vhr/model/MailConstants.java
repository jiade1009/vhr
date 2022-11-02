package org.javaboy.vhr.model;

public class MailConstants {
    public static final Integer DELIVERING = 0;//消息投递中
    public static final Integer SUCCESS = 1;//消息投递成功
    public static final Integer FAILURE = 2;//消息投递失败
    public static final Integer MAX_TRY_COUNT = 3;//最大重试次数
    public static final Integer MSG_TIMEOUT = 1;//消息超时时间
    /*员工入职消息的rabbit配置*/
    public static final String MAIL_QUEUE_NAME = "ghk.mail.queue";
    public static final String MAIL_EXCHANGE_NAME = "ghk.mail.exchange";
    public static final String MAIL_ROUTING_KEY_NAME = "ghk.mail.routing.key";

    /*股票消息的rabbit配置*/
    public static final String STOCK_QUEUE_NAME = "ghk.stock.queue";
    public static final String STOCK_EXCHANGE_NAME = "ghk.stock.exchange";
    public static final String STOCK_ROUTING_KEY_NAME = "ghk.stock.routing.key";
}
