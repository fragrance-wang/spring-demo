package com.atws.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.atws.beans.BeansException;
import com.atws.beans.PropertyValue;
import com.atws.beans.PropertyValues;
import com.atws.beans.factory.config.BeanDefinition;
import com.atws.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    // 首先在 AbstractAutowireCapableBeanFactory 抽象类中定义了一个创建对象的实例化策略属性类 InstantiationStrategy instantiationStrategy，这里我们选择了 Cglib 的实现类。
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * // 抽取 createBeanInstance 方法，在这个方法中需要注意 Constructor 代表了你有多少个构造函数，通过 beanClass.getDeclaredConstructors() 方式可以获取到你所有的构造函数，是一个集合。
     * // 接下来就需要循环比对出构造函数集合与入参信息 args 的匹配情况，这里我们对比的方式比较简单，只是一个数量对比，
     * // 而实际 Spring 源码中还需要比对入参类型，否则相同数量不同入参类型的情况，就会抛异常了。
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * Bean 属性填充
     * 通过获取 beanDefinition.getPropertyValues() 循环进行属性填充操作，如果遇到的是 BeanReference，那么就需要递归获取 Bean 实例，调用 getBean 方法。
     * 当把依赖的 Bean 对象创建完成后，会递归回现在属性填充中。这里需要注意我们并没有去处理循环依赖的问题，这部分内容较大，后续补充。
     * BeanUtil.setFieldValue(bean, name, value) 是 hutool-all 工具类中的方法，你也可以自己实现
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                // A 依赖 B，获取 B 的实例化
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            // 属性填充
            BeanUtil.setFieldValue(bean, name, value);
        }

    }

    /*    最初版本的方案
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            //类信息，反射，通过无参构造器，创建对象
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }*/

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}