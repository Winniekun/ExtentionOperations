package com.wkk.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author weikunkun
 * @since 2021/8/28
 */
public class FillTest {
    Random random = new Random();
    int num = 100;
    String key = "test";
    @Test
    public void fillZset() {
        try (Jedis jedis = new Jedis("47.95.224.162", 6379);) {
            jedis.auth("kj1110"); //如果Redis服务连接需要密码，制定密码
            Set<String> strings = jedis.zrangeByScore(key, 10, 90);
            Long zrank = jedis.zrank(key, "wkk" + 10);
            // 根据name获取排名
            System.out.println(jedis.zrank(key, "wkk" + 20));
            // 根据排名范围获取 元素
            Set<String> zrevrange = jedis.zrevrange(key, 20, 30);
            System.out.println(zrevrange);
            System.out.println(zrank);
            System.out.println(strings);
            System.out.println("ok");
        } catch (Exception e) {

        }
    }
}