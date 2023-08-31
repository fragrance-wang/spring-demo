package com.atws.test;

import com.atws.context.support.ClassPathXmlApplicationContext;
import com.atws.test.bean.UserService1;
import org.junit.Test;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class ApiTest8 {

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService1 userService1 = applicationContext.getBean("userService", UserService1.class);
        String result = userService1.queryUserInfo();
        System.out.println("测试结果：" + result);

        System.out.println("ApplicationContextAware："+ userService1.getApplicationContext());
        System.out.println("BeanFactoryAware："+ userService1.getBeanFactory());

    }

}
