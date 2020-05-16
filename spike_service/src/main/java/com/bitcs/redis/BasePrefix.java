package com.bitcs.redis;

/**
 * 基础的前缀类
 *
 * @author GeChen
 */
public abstract class BasePrefix implements KeyPrefix {
    private int expireSeconds;
    private String prefix;

    public BasePrefix(String prefix) {
        this.expireSeconds = 0;
        this.prefix = prefix;
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 默认0代表永不过期
     *
     * @return
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
