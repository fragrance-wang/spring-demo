package com.atws.context.event;

import com.atws.context.ApplicationContext;
import com.atws.context.ApplicationEvent;

/**
 * ApplicationEvent 是定义事件的抽象类，所有的事件包括关闭、刷新，以及用户自己实现的事件，都需要继承这个类。
 * ContextClosedEvent、ContextRefreshedEvent，分别是 Spring 框架自己实现的两个事件类，可以用于监听刷新和关闭动作。
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
 