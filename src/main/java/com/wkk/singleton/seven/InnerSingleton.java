package com.wkk.singleton.seven;

/**
 * 静态内部类
 * 机制和饿汉模式的思想差不多，都是利用了类的加载机制保证了并发情况下的线程安全
 * 不过实现了懒加载
 * instance不会被立马初始化，
 * 因为SingletonHolder类没有被主动使用，
 * 只有显示通过调用getInstance方法时，
 * 才会显示装载SingletonHolder类，显然它达到了lazy loading效果
 *
 * @author weikunkun
 * @since 2021/4/24
 */
public class InnerSingleton {
    private static InnerSingleton instance;

    private InnerSingleton() {

    }

    private static class SingletonHolder {
        private static InnerSingleton INSTANCE = new InnerSingleton();
    }

    public static InnerSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
