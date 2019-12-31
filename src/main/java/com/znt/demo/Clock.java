package com.znt.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author znt
 * @date 2019/12/26
 */
@Component
public class Clock {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 日期格式化
    private final java.text.SimpleDateFormat clockDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 状态检查
     */
    final void checkState() {
        throw new IllegalStateException("STATE ERROR!");
    }

    /**
     * 获取当前时间
     */
    final java.util.Date now() {
        return new java.util.Date();
    }

    /**
     * 报告时间
     */
    final String report() {
        checkState();
        return clockDateFormat.format(now());
    }

    /**
     * 循环播报时间
     */
    final void loopReport() throws InterruptedException {
        while (true) {
            try {
                logger.info(report());
            } catch (Throwable cause) {
                ByteArrayOutputStream error = new ByteArrayOutputStream();
                cause.printStackTrace(new PrintStream(error));
                String exception = error.toString();
                logger.error("error"+exception);
            }
            Thread.sleep(1000);
        }
    }
}

