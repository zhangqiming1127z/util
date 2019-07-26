package org.utils.redis;

import redis.clients.jedis.Jedis;


public class RedisUtil {
    public static boolean setExNx(String key,String value,int time){
        Jedis resource = null;
        try {
            resource = RedisLink.getResource();
            String set = resource.set(key, value, "nx", "ex", time);
            if (set=="1"){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (resource != null){
                resource.close();
            }
        }
        return false;
    }
    public static void setRedisValue(String key,String value){
        Jedis resource = null;
        try {
            resource = RedisLink.getResource();
            resource.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resource != null){
                resource.close();
            }
        }
    }
    public static String getRedisValue(String key){
        Jedis resource = null;
        String redisValue="";
        try {
            resource = RedisLink.getResource();
            redisValue = resource.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resource != null){
                resource.close();
            }
        }
        return redisValue;
    }

    public static void delRedisValue(String key){
        Jedis resource = null;
        try {
            resource = RedisLink.getResource();
            String[] split = key.split(",");
            for (String s : split) {
                resource.del(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resource != null){
                resource.close();
            }
        }

    }
    public static boolean existsKey(String key){
        Jedis resource = null;
        Boolean exists;
        try {
            resource = RedisLink.getResource();
            exists = resource.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (resource != null){
                resource.close();
            }
        }
        return exists;
    }

    public static void expire(String key,int time){
        Jedis resource = null;
        try {
            resource = RedisLink.getResource();
            resource.expire(key, time);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (resource != null){
                resource.close();
            }
        }
    }
    public static void setEx(String key,String value,int time){
        Jedis resource = null;
        try {
            resource = RedisLink.getResource();
            resource.setex(key,time,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (resource != null){
                resource.close();
            }
        }
    }
}
