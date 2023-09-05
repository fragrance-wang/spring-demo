package com.atws.test;

import com.atws.context.support.ClassPathXmlApplicationContext;
import com.atws.test.event.CustomEvent;
import org.junit.Test;

/**
 * @author wangshan
 * @date 2023-08-31 19:18
 */
public class Test10Event {

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }


}
