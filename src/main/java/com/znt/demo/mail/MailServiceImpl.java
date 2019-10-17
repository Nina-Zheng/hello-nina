package com.znt.demo.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author znt
 * @date 2019/10/17
 */
@Component
public class MailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String countent) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(countent);

        try{
            mailSender.send(simpleMailMessage);
            logger.info("SimpleMailMessage sended!");
        }catch (Exception e){
            logger.error("SimpleMailMessage Exception！", e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content);
            //抄送
            helper.setCc("zhengniting@dianwoda.com");
            mailSender.send(message);
            logger.info("html邮件已经发送。");
        } catch (MessagingException e) {
            logger.info("html邮件已经发送。");
            e.printStackTrace();
        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content);
            //抄送
            helper.setCc("zhengniting@dianwoda.com");
            //添加附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName,file);
            mailSender.send(message);
            logger.info("带附件邮件已经发送!");
        } catch (MessagingException e) {
            logger.info("带附件邮件已经发送!");
            e.printStackTrace();
        }

    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content);
            //抄送
            helper.setCc("zhengniting@dianwoda.com");
            //添加附件
            FileSystemResource file = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,file);
            mailSender.send(message);
            logger.info("带静态资源文件邮件已经发送。");
        } catch (MessagingException e) {
            logger.info("带静态资源文件邮件已经发送。");
            e.printStackTrace();
        }
    }


}
