package com.atws.beans;

/**
 * @author wangshan
 * @date 2023-08-28 22:17
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
