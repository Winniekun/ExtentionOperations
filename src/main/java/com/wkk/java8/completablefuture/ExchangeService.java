package com.wkk.java8.completablefuture;

import static com.wkk.java8.completablefuture.Utils.delay;

/**
 * 根据汇率获取对应的倍数
 *
 * @author weikunkun
 * @since 2022/8/14
 */
public class ExchangeService {

    public static double getRate(Money source, Money destination) {
        return getRateWithDelay(source, destination);
    }

    private static double getRateWithDelay(Money source, Money destination) {
        delay();
        return destination.rate / source.rate;
    }
}
