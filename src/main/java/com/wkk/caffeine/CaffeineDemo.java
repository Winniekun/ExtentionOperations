package com.wkk.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Time: 2020/7/2下午7:15
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class CaffeineDemo {
    public static void main(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                .build();
        cache.put("hello","hello");
        System.out.println(cache.getIfPresent("hello"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache.getIfPresent("hello"));
    }

}
