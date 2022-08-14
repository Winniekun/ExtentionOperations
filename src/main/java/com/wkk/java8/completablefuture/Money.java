package com.wkk.java8.completablefuture;

/**
 * 汇率
 *
 * @author weikunkun
 * @since 2022/8/14
 */
public enum Money {
    USD(1.0),
    EUR(1.35387),
    GBP(1.69715),
    CAD(.92106),
    MXN(.07683);

    public final double rate;

    Money(double rate) {
        this.rate = rate;
    }
}
