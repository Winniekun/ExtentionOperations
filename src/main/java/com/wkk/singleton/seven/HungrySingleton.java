package com.wkk.singleton.seven;

/**
 * 饿汉模式
 * 优点： 在类加载完成之后，就将实例创建好了
 *       所以不会出现并发情况下的安全问题
 * 缺点：
 *       未达到懒加载的效果，如果该实例一直未被占用，则会造成内存的浪费
 *       通过反射的形式，可以进行破坏
 *
 * @author weikunkun
 * @since 2021/4/24
 */
public class HungrySingleton {
    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return singleton;
    }
}
