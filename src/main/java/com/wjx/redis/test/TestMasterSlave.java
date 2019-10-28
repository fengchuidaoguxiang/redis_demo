package com.wjx.redis.test;

import redis.clients.jedis.Jedis;

public class TestMasterSlave {

    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("192.168.79.160",6379);
        Jedis jedis_S = new Jedis("192.168.79.160",6380);
//        System.out.println(jedis_M.ping());
//        System.out.println(jedis_S.ping());

        jedis_S.slaveof("192.168.79.160",6379);

        jedis_M.set("class","112233");

        String aClass = jedis_S.get("class");

        System.out.println(aClass);

    }
}
