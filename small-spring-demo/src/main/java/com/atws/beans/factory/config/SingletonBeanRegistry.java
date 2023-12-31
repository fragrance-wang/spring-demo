package com.atws.beans.factory.config;

/**
 * @author wangshan
 * @date 2023-08-28 22:24
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
