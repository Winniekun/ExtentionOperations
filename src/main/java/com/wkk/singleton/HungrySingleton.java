package com.wkk.singleton;

/**
 * @Time: 2020/7/2下午8:51
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
