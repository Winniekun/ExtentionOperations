package com.wkk.singleton;

/**
 * @Time: 2020/7/2下午8:50
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton(){

    }

    public static LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
