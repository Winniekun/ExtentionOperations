package com.wkk.java8.completablefuture;

/**
 * 折扣枚举类
 *
 * @author weikunkun
 * @since 2022/8/14
 */
public enum Code {
    NONE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15),
    DIAMOND(20),
    ;

    public final int percentage;

    Code(int percentage) {
        this.percentage = percentage;
    }
}
