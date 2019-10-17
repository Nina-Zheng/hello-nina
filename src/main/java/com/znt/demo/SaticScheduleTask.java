package com.znt.demo;

import com.znt.demo.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author znt
 * @date  2019/10/17
 * @Configuration 主要用于标记配置类，兼备Component的效果。
 * @EnableScheduling  开启定时任务
 */
@Component
@Configuration
@EnableScheduling

public class SaticScheduleTask {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MailService mailService;


    @Scheduled(cron = "0 15 10 * * ?")

    private void configureTasks() {

        logger.info("执行静态定时任务时间: " + LocalDateTime.now());

        mailService.sendSimpleMail("zhengniting@dianwoda.com","这是一封简单邮件","大家好，这是我的第一封邮件！");

    }

}
