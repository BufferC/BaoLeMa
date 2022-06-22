package com.fc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 短信发送工具类
 */
@Component
public class EmailUtil {
    // 邮件发送器
    private static JavaMailSender sender;
    // 发件人
    private static String senderMail;

    // 从容器中获取
    @Autowired
    public void setSender(JavaMailSender sender) {
        EmailUtil.sender = sender;
    }

    @Value("${spring.mail.username}")
    public void setSenderMail(String senderMail) {
        EmailUtil.senderMail = senderMail;
    }

    /**
     * 发送短信/邮件
     *
     * @param signName     签名
     * @param phoneNumbers 手机号
     * @param param        参数
     */
    public static void sendMessage(String signName, String phoneNumbers, String param) {
        System.out.println("使用邮件业务来获取验证码：" + param);

        // 创建一个邮件对象
        MimeMessage mimeMessage = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(senderMail);
            // 设置发送的时间（定时邮件）
            helper.setSentDate(new Date());

            helper.setTo(new String[] {senderMail});

            helper.setSubject("验证码");

            helper.setText(
                    "【" + signName + "】亲爱的" + phoneNumbers + "您好，您的验证码为：" + param +
                            "请在2分钟内输入验证，逾期后您需要重新获取一个验证码--曹操外卖"
                    );

            sender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
