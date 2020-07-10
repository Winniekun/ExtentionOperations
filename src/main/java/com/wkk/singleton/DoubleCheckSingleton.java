package com.wkk.singleton;

/**
 * @Time: 2020/7/2下午8:51
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {

    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
