package com.atws;

import com.atws.service.UserService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
         reader.loadBeanDefinitions("beans.xml");
        UserService bean = (UserService)beanFactory.getBean("userService");
        System.out.println(bean);


    }
}
