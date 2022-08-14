package com.wkk.java8.completablefuture;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/8/14
 */
@Data
@AllArgsConstructor
public class Quote {
    private final String shopName;

    private final double price;

    private final Code discountCode;

    // 根据shop中返回的结果 构造Quote对象
    public Quote(String product) {
        String[] split = product.split(":");
        this.shopName = split[0];
        this.price = Double.parseDouble(split[1]);
        this.discountCode = Code.valueOf(split[2]);
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Code discountCode = Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }
}
