package com.zfq.service.impl;

import com.zfq.service.ISendCodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
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

    @Override
    public void sendCode(String code, String reciveAddress) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(MimeMessage.RecipientType.TO,
                        new InternetAddress(reciveAddress));
                mimeMessage.setFrom(new InternetAddress(sendAddress));
                mimeMessage.setText("您好！\n" +
                        "\n" +
                        "本次操作的验证码为" + code + "。\n" +
                        "\n" +
                        "验证码有效5分钟，请在有效期内完成验证。\n" +
                        "如非本人操作，请忽略此邮件，切勿泄露验证码给他人。\n" +
                        "\n" +
                        "此致");
            }
        };
        mailSender.send((SimpleMailMessage) preparator);
    }
}
