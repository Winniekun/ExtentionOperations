package com.wkk.loadbalancer;

/**
 * @author weikunkun
 * @since 2021/8/15
 */
public class ConHashBalancer {
    public static void main(String[] args) {
        String a = "192.168.0.0:111";
        String b = "192.168.1.0:111";
        String c = "192.168.2.0:111";
        String d = "192.168.3.0:111";
        String e = "192.168.4.0:111";
        int h;
        System.out.println("192.168.0.0:111 的哈希值：" + ((h = a.hashCode()) ^ (h >>> 16)));
        System.out.println("192.168.0.1:111 的哈希值：" + ((h = b.hashCode()) ^ (h >>> 16)));
        System.out.println("192.168.0.2:111 的哈希值：" + ((h = c.hashCode()) ^ (h >>> 16)));
        System.out.println("192.168.0.3:111 的哈希值：" + ((h = d.hashCode()) ^ (h >>> 16)));
        System.out.println("192.168.0.4:111 的哈希值：" + ((h = e.hashCode()) ^ (h >>> 16)));
    }
}
