package com.bitcs.redis;

/**
 * @author GeChen
 */
public class OrderKey extends BasePrefix {
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
