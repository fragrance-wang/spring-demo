package com.atws;

import com.atws.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * - BeanFactory与ApplicationContext的关系
 * 1）BeanFactory是Spring的早期接口，称为Spring的Bean工厂，ApplicationContext是后期更高级接口，称之为Spring 容器；
 * 2）ApplicationContext在BeanFactory基础上对功能进行了扩展，例如：监听功能、国际化功能等。BeanFactory的
 *    API更偏向底层，ApplicationContext的API大多数是对这些底层API的封装；
 * 3）Bean创建的主要逻辑和功能都被封装在BeanFactory中，ApplicationContext不仅继承了BeanFactory，而且
 *    ApplicationContext内部还维护着BeanFactory的引用，所以，ApplicationContext与BeanFactory既有继承关系，又有融合关系。
 * 4）Bean的初始化时机不同，原始BeanFactory是在首次调用getBean时才进行Bean的创建，而ApplicationContext则
 *    是配置文件加载，容器一创建就将Bean都实例化并初始化好。
 *
 *  ApplicationContext除了继承了BeanFactory外，还继承了ApplicationEventPublisher（事件发布器）、
 *  ResouresPatternResolver（资源解析器）、MessageSource（消息资源）等。但是ApplicationContext的核心功能还是BeanFactory
 *
 *  <img  src="C:\java_soft\idea_code\spring-demo\src\main\resources\imgs\img.png"/>
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        //创建ApplicationContext,加载配置文件，实例化容器
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        //根据beanName获得容器中的Bean实例
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);

    }
}
