package com.bitcs.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis服务类
 *
 * @author GeChen
 */
@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;

    /**
     * 获得缓存
     *
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            String res = jedis.get(realKey);
            T t = stringToBean(res, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置缓存
     *
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String valueString = beanToString(value);
            if (valueString == null || valueString.length() <= 0) {
                return false;
            }
            String realKey = keyPrefix.getPrefix() + key;
            int expireSeconds = keyPrefix.expireSeconds();
            if (expireSeconds <= 0) {
                jedis.set(realKey, valueString);
            } else {
                jedis.setex(realKey, expireSeconds, valueString);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 是否存在key
     *
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 把对应键的值增加
     *
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 把对应键的值减少
     *
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == Integer.class) {
            return String.valueOf(value);
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == Long.class) {
            return String.valueOf(value);
        } else {
            return JSON.toJSONString(value);
        }
    }

    private <T> T stringToBean(String res, Class<T> clazz) {
        if (res == null || res.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == Integer.class) {
            return (T) Integer.valueOf(res);
        } else if (clazz == String.class) {
            return (T) res;
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(res);
        } else {
            return JSON.toJavaObject(JSON.parseObject(res), clazz);
        }
    }


    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
