package com.wjx.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestPool {

    public static void main(String[] args) {

        JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
        JedisPool jedisPoolInstance2 = JedisPoolUtil.getJedisPoolInstance();
        System.out.println(jedisPoolInstance == jedisPoolInstance2);

        Jedis jedis = null;

        try{
            jedis = jedisPoolInstance.getResource();
            jedis.set("aa", "bb");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JedisPoolUtil.release(jedisPoolInstance, jedis);
        }
    }
}
