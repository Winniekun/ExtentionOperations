package com.wkk.java8.completablefuture;

import static com.wkk.java8.completablefuture.Utils.format;
import static com.wkk.java8.completablefuture.Utils.randomDelay;

/**
 * Discount类，用于返回折扣之后的价格，可以理解为远程HTTP调用
 *
 * @author weikunkun
 * @since 2022/8/14
 */
public class Discount {

    /**
     * 折扣
     * 返回打完折之后的价格
     * @param price 价格
     * @param code  折扣枚举类
     * @return   ShopName:price:DiscountCode
     */
    public static double apply(double price, Code code) {
        randomDelay();
        return format(price * (100 - code.percentage) / 100);
    }

    /**
     * quote -> price desc
     * @param quote
     * @return
     */
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                apply(quote.getPrice(), quote.getDiscountCode());
    }
}
