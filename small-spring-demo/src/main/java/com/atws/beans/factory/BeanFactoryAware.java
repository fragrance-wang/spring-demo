package com.atws.beans.factory;

import com.atws.beans.BeansException;

/**
 * 容器感知类
 */
public interface BeanFactoryAware extends Aware {

   void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
 