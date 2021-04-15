package com.wkk.singleton;

/**
 * @Time: 2020/7/2下午8:52
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public enum  EnumSingleton {
    SINGLETON;
    private Resource instance;
    EnumSingleton() {
        instance = new Resource();
    }
    public Resource getInstance() {
        return instance;
    }
}

class Resource{

}
