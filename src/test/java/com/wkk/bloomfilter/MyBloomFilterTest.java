package com.wkk.bloomfilter;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.wkk.utils.BloomFilterUtil;
import edu.princeton.cs.algs4.BinaryIn;
import org.junit.Test;

import java.util.List;

/**
 * @Time: 2020/7/10下午7:52
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class MyBloomFilterTest {
    @Test
    public void test(){
        String value1 = "https://www.kongwiki.me";
        String value2 = "维坤坤";
        String value3 = "维坤坤";
//        System.out.println(value2 == value3);
        MyBloomFilter filter = new MyBloomFilter();
        filter.add(value1);
        filter.add(value2);
        filter.add(value3);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
    }
    @Test
    public void quality(){
        BloomFilter filter  = BloomFilter.create(
                Funnels.integerFunnel(),
                1<<25,
                0.01
        );
        MyBloomFilter filter1 = new MyBloomFilter();
        List<Integer> generate = BloomFilterUtil.generate(1000000, 100000);
        for (Integer integer : generate) {
            filter.put(integer);
            filter1.add(integer);
        }
        boolean succeed = true;
        for (Integer integer : generate) {
            if(filter.mightContain(integer) != filter1.contains(integer)){
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "算法正确" : "艹 错了");

    }
}
