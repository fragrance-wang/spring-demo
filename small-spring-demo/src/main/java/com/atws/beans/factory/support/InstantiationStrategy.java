package com.atws.beans.factory.support;

import com.atws.beans.BeansException;
import com.atws.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 在实例化接口 instantiate 方法中添加必要的入参信息，包括：beanDefinition、 beanName、ctor、args
 * 其中 Constructor 你可能会有一点陌生，它是 java.lang.reflect 包下的 Constructor 类，里面包含了一些必要的类信息，有这个参数的目的就是为了拿到符合入参信息相对应的构造函数。
 * 而 args 就是一个具体的入参信息了，最终实例化时候会用到。
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}