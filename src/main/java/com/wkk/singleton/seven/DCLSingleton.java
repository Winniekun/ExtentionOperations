package com.wkk.singleton.seven;

/**
 * @author weikunkun
 * @since 2021/4/24
 */
public class DCLSingleton {
    private static volatile DCLSingleton instance;

    private DCLSingleton() {

    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
