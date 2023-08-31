package com.atws.test.common;

import com.atws.beans.BeansException;
import com.atws.beans.factory.config.BeanPostProcessor;
import com.atws.test.bean.UserService1;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService1 userService1 = (UserService1) bean;
            userService1.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
 