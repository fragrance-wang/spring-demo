package com.atws.test;

import com.atws.beans.factory.config.BeanDefinition;
import com.atws.beans.factory.BeanFactory;
import com.atws.beans.factory.support.DefaultListableBeanFactory;
import com.atws.test.bean.UserService;
import org.junit.Test;

/**
 * @author wangshan
 * @date 2023-08-28 22:07
 */


public class APITest {
    @Test
    public void test_BeanFactory() {
       /* // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();*/

        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();

    }

}