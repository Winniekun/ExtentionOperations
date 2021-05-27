package com.wkk.singleton.seven;

/**
 * 懒汉模式
 * 优点：
 *      懒加载
 * 缺点：
 *      并发情况下， 线程不安全，可能会重复创建实例
 * <p>
 * 懒汉模式II
 *   针对多线程情况下，线程不安全问题，所以对获取实例方法上加锁
 * 缺点：
 *     虽然加锁解决了并发情况下的线程安全问题，但是也将该方法的访问变成了串行方式
 *     所以效率很低
 * @author weikunkun
 * @since 2021/4/24
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public static synchronized LazySingleton getInstanceII() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
