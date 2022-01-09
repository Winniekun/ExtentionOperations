package com.wkk.loadbalancer;

import org.junit.Before;
import org.junit.Test;

/**
 * @author weikunkun
 * @since 2021/8/14
 */
public class DemoTest {
    int requests = 10;
    String[] ips = new String[requests];

    @Before
    public void fillData() {
        for (int i = 0; i < requests; i++) {
            int val = (int) (Math.random() * 100) + 1;
            ips[i] = String.valueOf(val);
        }
    }

    /**
     * 轮询方式
     * 按照请求的次序进行分配到不同的服务器上，均衡的对待每一个服务器，不过忽略了服务器自身的连接数和负载情况
     * <p>
     * 目的：在于请求的绝对均衡,但是在实际的情况下,可能服务器并不是完全一样. 导致有些性能高的服务器不能完全发挥出来.
     */
    @Test
    public void testPolling() {
        for (int i = 0; i < requests; i++) {
            System.out.println(Balancer.getPollingServer());
        }
    }

    /**
     * 随机方式
     * 通过系统的随机函数,根据后端服务器列表的大小来随机获取其中的一台来访问,随着调用量的增大,
     * 实际效果越来越近似于平均分配到每一台服务器.和轮询的效果类似.
     * <p>
     * 和轮询算法比较,在并发的场景下,轮询需要加锁,随机法想比而言性能好点.
     */
    @Test
    public void testRandom() {
        for (int i = 0; i < requests; i++) {
            System.out.println(Balancer.getRandomServer());
        }
    }

    /**
     * 源地址 hash法
     *
     * hash法的好处是,在服务器列表不变的情况下,每次客户端访问的服务器都是同一个服务器.利用这个
     * 特性可以有状态的session会话.无需额外的操作就可以实现粘性会话.
     *
     */
    @Test
    public void testSourceAddressHash() {
        for (int i = 0; i < requests; i++) {
            System.out.println(Balancer.getSourceAddressHashServer(ips[i]));
        }
    }

    /**
     * 加权轮询
     * 就是根据某种加权的方式，将权重大的节点 有更多的机会处理请求
     */
    @Test
    public void testWeightPolling() {
        for (int i = 0; i < requests; i++) {
            System.out.println(Balancer.getWeightRandomServer());
        }
    }
}