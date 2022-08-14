package com.wkk.java8.completablefuture;

import lombok.Data;

import java.util.Random;

import static com.wkk.java8.completablefuture.Utils.delay;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/8/11
 */
@Data
public class Shop {

    private Random random = new Random();

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    /**
     * 查询价格 网络请求，延迟1s
     *
     * @param product
     * @return double
     */
    public double getPriceNumber(String product) {
        return calculatePrice(product);
    }

    /**
     * 获取价格信息
     * @param product
     * @return
     */
    public String getPrice(String product) {
        double price = calculatePrice(product);
        Code code = Code.values()[random.nextInt(Code.values().length)];

        return name + ":" + price + ":" + code;
    }

    /**
     * 模拟实际查询价格的方法
     *
     * @param product
     * @return
     */
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
