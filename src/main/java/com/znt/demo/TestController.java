package com.znt.demo;

import com.znt.demo.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author znt
 * @date  2019/10/18
 */

@Controller
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MailService mailService;

    @RequestMapping("/")
    public String test(){
        return "项目启动成功~";
    }


    @RequestMapping("/test")
    public String mailSend() {

        mailService.sendSimpleMail("zhengniting@dianwoda.com", "这是一封简单邮件", "大家好，这是我的第一封邮件！");

        return "邮件发送完毕！";

    }

}
