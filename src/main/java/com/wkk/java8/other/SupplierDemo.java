package com.wkk.java8.other;

import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * Supplier使用
 * 是一个函数式接口，五参数，返回值为T
 * 可以使用 lambda 表达式或方法引用来实现 T get()
 * @author weikunkun
 * @since 2022/5/23
 */
public class SupplierDemo {

    public static void supplierUse() {
        Supplier<Integer> supplier = () -> new Random().nextInt(10);
        int nextInt = new Random().nextInt(10);
        System.out.println(supplier.get());
        System.out.println(nextInt);
    }

    public static void main(String[] args) {
        IntSupplier intSupplier = () -> new Random().nextInt(10);
        System.out.println(intSupplier.getAsInt());
        supplierUse();
    }
}
