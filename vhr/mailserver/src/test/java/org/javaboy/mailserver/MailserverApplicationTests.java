package org.javaboy.mailserver;

//import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.mailserver.utils.DateUtils;
import org.javaboy.vhr.model.util.CommandType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
class MailserverApplicationTests {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;
    @Autowired
    TemplateEngine templateEngine;

//    @Test
    void contextLoads() {
    }
    @Test
    public void testSendMail(){
        LinkedHashMap<CommandType, Boolean> params = new LinkedHashMap();
        params.put(CommandType.WEEKLY_EMA, true);
        params.put(CommandType.WEEKLY, false);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json_str = mapper.writeValueAsString(params);
            System.out.println(json_str);
            Map map = mapper.readValue(json_str, Map.class);
            StringBuilder sbd = new StringBuilder("");
            for (Object key : map.keySet()) {
                CommandType type = CommandType.valueOf((String)key);
                Boolean flag = (Boolean) map.get(key);
                sbd.append("【" + type.getCommandDesc() + "】");
                if (flag) {
                    sbd.append("脚本命令已执行<br/>");
                } else {
                    sbd.append("脚本命令暂未执行<br/>");
                }
            }

            //收到消息，发送邮件
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            try {
                Date now = new Date();
                String title = "巡检结果";
                helper.setTo("17979563@qq.com");
                helper.setFrom(mailProperties.getUsername());
                helper.setSubject(title + " - " + DateUtils.formatDate(now, DateUtils.yyyyMMdd));
                helper.setSentDate(now);
                Context context = new Context();
                context.setVariable("name", "sam");
                context.setVariable("content", sbd.toString());
                context.setVariable("label", title);
                String mail = templateEngine.process("mail_stock_discover", context);
                helper.setText(mail, true);
                javaMailSender.send(msg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}