package com.wjx.redis.test;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class TestAPI {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.79.160",6381);

        jedis.set("k2", "v2");
        jedis.set("k3", "v3");

        System.out.println(jedis.get("k3"));

        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size());

    }
}
