package com.wkk.bloomfilter;

import java.util.BitSet;

/**
 * @Time: 2020/7/10下午7:15
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class MyBloomFilter {
    /**
     * 位数组的大小
     */
    private static final int DEFAULT_SIZE = 1<<25;

    /**
     * 位数组
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 通过该数组可以创建6个不同的hash函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 存放hash函数的类的数组
     */
    private final SimpleHash[] funcs = new SimpleHash[SEEDS.length];

    /**
     * 初始化的时候, 会初始化包含多个hash函数的数组funcs
     */
    public MyBloomFilter(){
        for (int i = 0; i < SEEDS.length; i++) {
            funcs[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到bloomfilter中
     */
    public void add(Object value){
        for (SimpleHash func : funcs) {
            bits.set(func.hash(value), true);
        }
    }

    /**
     * 判断是否存在值
     */
    public boolean get(Object value){
        boolean ret = true;
        for (SimpleHash func : funcs) {
            ret = ret&&bits.get(func.hash(value));
        }
        return ret;
    }

    /**
     * 静态内部类 用于hash操作
     */
    private static class SimpleHash{
        /**
         * 位数组的容量
         */
        private int capacity;
        /**
         *
         */
        private int seed;

        public SimpleHash(int capacity, int seed) {
            this.capacity = capacity;
            this.seed = seed;
        }

        public int hash(Object value){
            int h;
            return (value==null) ? 0 : Math.abs(seed *(capacity-1) & ((h = value.hashCode()) ^ (h>>>16)));
        }
    }

    public static void main(String[] args) {

    }
}
