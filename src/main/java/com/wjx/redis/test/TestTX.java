package com.wjx.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.sound.midi.Soundbank;

public class TestTX {

    public boolean transMethod(){
        Jedis jedis = new Jedis("192.168.79.160", 6381);
        //可用余额
        int balance;
        //欠额
        int debt;
        //实刷额度
        int amtToSubtract = 10;

        jedis.watch("balance");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance = Integer.parseInt(jedis.get("balance"));
        if( balance < amtToSubtract ){
            jedis.unwatch();
            System.out.println("modify");
            return false;
        }else{
            System.out.println("*************transaction");
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance", amtToSubtract);
            transaction.incrBy("debt",amtToSubtract);
            transaction.exec();
            balance = Integer.parseInt(jedis.get("balance"));
            debt = Integer.parseInt(jedis.get("debt"));

            System.out.println("************" + balance);
            System.out.println("************" + debt);
            return true;
        }
    }

    public static void main(String[] args) {
        TestTX testTX = new TestTX();
        boolean retValue = testTX.transMethod();
        System.out.println("main retValue--------: " + retValue);
    }

//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("192.168.79.160", 6381);
//        //事务提交
//        Transaction transaction = jedis.multi();
//
//        transaction.set("k44", "v44");
//        transaction.set("k55", "v55");
//        transaction.set("k66", "v66");
//
////        transaction.exec();
//        transaction.discard();
//    }
}
