package com.wkk.jackson;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author kongwiki@163.com
 * @since 2020/10/23
 */
public class DemoBeanTest {
    private static final  String HOST = "47.95.224.162";
    private static final  int PORT = 6379;
    private static final String POWER_SWAP_START_TIMEOUT_KEY = "DPG#POWERSWAP#START#TIMEOUT";
    private static final String POWER_SWAP_FINISH_TIMEOUT_KEY = "DPG#POWERSWAP#FINISH#TIMEOUT";
    private static Jedis jedis;

    @Before
    public void genJedis() {
        jedis = new Jedis(HOST, PORT);
    }

    @Test
    public void testZSet() {
        for (int i = 0; i < 100; i++) {
            long time = (long) (Math.random() * 1000) + 1000L;
            System.out.println(time);
            jedis.zadd(POWER_SWAP_FINISH_TIMEOUT_KEY, time, String.valueOf(i+1));
        }
    }

    @Test
    public void testZSetRange() {
        Set<String> strings = jedis.zrangeByScore(POWER_SWAP_FINISH_TIMEOUT_KEY, 0, 1000);
        System.out.println(strings);
    }

    @Test
    public void test() throws Exception {
        DemoBean bean = DemoBean.builder().age("111").build();
        String s = JsonUtils.bean2Json(bean);
        System.out.println(s);
    }

    @Test
    public void testI() {
    }

}