package com.bitcs.redis;

/**
 * Redis中Key前缀的接口类
 *
 * @author GeChen
 */
public interface KeyPrefix {
    /**
     * 过期时间
     *
     * @return
     */
    int expireSeconds();

    /**
     * 得到具体的前缀
     *
     * @return
     */
    String getPrefix();
}
