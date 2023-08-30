package com.atws.test;

import cn.hutool.core.io.IoUtil;
import com.atws.beans.factory.support.DefaultListableBeanFactory;
import com.atws.beans.factory.xml.XmlBeanDefinitionReader;
import com.atws.context.support.ClassPathXmlApplicationContext;
import com.atws.core.io.DefaultResourceLoader;
import com.atws.core.io.Resource;
import com.atws.test.bean.UserService;
import com.atws.test.common.MyBeanFactoryPostProcessor;
import com.atws.test.common.MyBeanPostProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangshan
 * @date 2023-08-28 22:07
 */


public class APITest6 {

    /**
     * 不用应用上下文
     */
    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


    /**
     * 使用应用上下文
     * 另外使用新增加的 ClassPathXmlApplicationContext 应用上下文类，再操作起来就方便多了，
     * 这才是面向用户使用的类，在这里可以一步把配置文件交给 ClassPathXmlApplicationContext，也不需要管理一些自定义实现的 Spring 接口的类
     */
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


}