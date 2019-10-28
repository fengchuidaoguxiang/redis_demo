package com.wjx.redis.test;

import redis.clients.jedis.Jedis;

public class TestPing {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.79.160",6381);
        System.out.println(jedis.ping());
    }
}
