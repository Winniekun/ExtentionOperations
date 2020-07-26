package com.wkk;

/**
 * @Time: 2020/7/15下午3:12
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class Son extends Super{
    public static void main(String[] args) {
        Super sp = new Super();
        sp.method();
    }

    public static void show(){
        System.out.println("子类的静态方法");
    }
    @Override
    public void method(){
        System.out.println("子类的一般方法");
    }
}
