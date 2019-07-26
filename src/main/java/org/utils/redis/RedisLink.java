package org.utils.redis;

import org.utils.ReloadUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisLink {


    private static String host = ReloadUtil.configuration.getString("redis.host");
    private static Integer port = ReloadUtil.configuration.getInt("redis.port");
    static {
        connection();
    }
    private static JedisPool jedisPool;

    private static void connection() {
        //创建redis配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //创建总连接数
        jedisPoolConfig.setMaxTotal(500);
        //创建空闲时间最小连接数
        jedisPoolConfig.setMaxIdle(100);
        //创建空闲时间最大连接数
        jedisPoolConfig.setMinIdle(100);
        //验证链接信息
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }

    public static Jedis getResource() {
        return jedisPool.getResource();
    }
}
