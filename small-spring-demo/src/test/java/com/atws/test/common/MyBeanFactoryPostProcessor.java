package com.atws.test.common;

import com.atws.beans.BeansException;
import com.atws.beans.PropertyValue;
import com.atws.beans.PropertyValues;
import com.atws.beans.factory.ConfigurableListableBeanFactory;
import com.atws.beans.factory.config.BeanDefinition;
import com.atws.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
 