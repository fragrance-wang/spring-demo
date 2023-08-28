package com.atws.beans.factory;


import com.atws.beans.BeansException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;

}