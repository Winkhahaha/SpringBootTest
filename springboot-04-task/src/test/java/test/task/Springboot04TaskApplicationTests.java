package test.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot04TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //邮件设置
        mailMessage.setSubject("通知");
        mailMessage.setText("开会");
        mailMessage.setTo("mineok@qq.com");
        mailMessage.setFrom("mineok@foxmail.com");
        javaMailSender.send(mailMessage);
    }

    @Test
    void contextLoads2() throws MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        //邮件设置
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage,true);

        mimeMessageHelper.setSubject("通知");
        mimeMessageHelper.setText("<b>开会</b>",true);    // 这段内容是html
        mimeMessageHelper.setTo("mineok");
        mimeMessageHelper.setFrom("mineok");

        // 上传文件
        mimeMessageHelper.addAttachment("1.jpg",new File("1.jpg"));
        mimeMessageHelper.addAttachment("1.jpg",new File("1.jpg"));
    }


}
