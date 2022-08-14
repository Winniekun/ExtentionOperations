package com.wkk.java8.completablefuture;

import org.junit.Test;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/8/14
 */
public class ShopTest {

    @Test
    public void testGetPrice() {
        Shop shop = new Shop("BestPrice");
        System.out.println(shop.getPrice("product"));
    }
}
