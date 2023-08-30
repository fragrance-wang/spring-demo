package com.atws.test;

import com.atws.beans.factory.support.DefaultListableBeanFactory;
import com.atws.beans.factory.xml.XmlBeanDefinitionReader;
import com.atws.context.support.ClassPathXmlApplicationContext;
import com.atws.test.bean.UserService;
import com.atws.test.common.MyBeanFactoryPostProcessor;
import com.atws.test.common.MyBeanPostProcessor;
import org.junit.Test;

/**
 * @author wangshan
 * @date 2023-08-28 22:07
 */


public class APITest7 {


    /**
     * 本文主要完成了关于初始和销毁在使用接口定义 implements InitializingBean, DisposableBean 和在spring.xml中配置 init-method="initDataMethod" destroy-method="destroyDataMethod" 的
     * 两种具体在 AbstractAutowireCapableBeanFactory 完成初始方法和 AbstractApplicationContext 处理销毁动作的具体实现过程。
     *
     * 通过本文的实现内容，可以看到目前这个 Spring 框架对 Bean 的操作越来越完善了，可扩展性也不断的增强。你既可以在Bean注册完成实例化前进行 BeanFactoryPostProcessor 操作，
     * 也可以在Bean实例化过程中执行前置和后置操作，现在又可以执行Bean的初始化方法和销毁方法。所以一个简单的Bean对象，已经被赋予了各种扩展能力。
     *
     * 在学习和动手实践 Spring 框架学习的过程中，特别要注意的是它对接口和抽象类的把握和使用，尤其遇到类似，A继承B实现C时，C的接口方法由A继承的父类B实现，这样的操作都蛮有意思的。
     * 也是可以复用到通常的业务系统开发中进行处理一些复杂逻辑的功能分层，做到程序的可扩展、易维护等特性
     */
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        //关于销毁方法需要在虚拟机执行关闭之前进行操作，所以这里需要用到一个注册钩子的操作，如：Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
        // 这段代码你可以执行测试，另外你可以使用手动调用 ApplicationContext.close 方法关闭容器
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


}