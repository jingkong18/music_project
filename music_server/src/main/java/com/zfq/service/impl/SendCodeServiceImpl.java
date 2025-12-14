package com.zfq.service.impl;

import com.zfq.service.ISendCodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName SendCodeService
 * @Author zfq
 * @Date 2025/12/7
 */
@Service
public class SendCodeServiceImpl implements ISendCodeService {

    @Value("2360589290@qq.com")
    private String sendAddress;

    @Resource
    private MailSender mailSender;

    @Async
    @Override
    public void sendCode(String code, String reciveAddress) {
        // 1. 构建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendAddress); // 使用注入的发件人邮箱
        message.setTo(reciveAddress); // 收件人邮箱
        message.setSubject("【音乐】邮箱验证码"); // 邮件主题
        message.setText("你的验证码是：" + code + "，有效期5分钟，请妥善保管！"); // 文本内容

        // 2. 发送邮件
        mailSender.send(message);
    }
}
