package com.wkk.sprinngdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/7/14
 */
@Slf4j
@Service
public class ShoppingService implements Callback {

    @Autowired
    private StoreService storeService;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public void buy(String name) {
        log.info("开始下单购买， 时间：{}", LocalDateTime.now().format(DATE_FORMATTER));
        Runnable runnable = () -> {
            storeService.order(name, this);
        };

        Thread thread = new Thread(runnable);
        thread.start();
        log.info("{} 下单完成，等待结果，时间：{}", name, LocalDateTime.now().format(DATE_FORMATTER));

    }

    @Override
    public void success(String name, String price) {
        log.info("{} 购买成功， 价格是：{}, 时间是：{}", name, price, LocalDateTime.now().format(DATE_FORMATTER));
    }

    @Override
    public void fail(String name) {
        log.info("{} 购买失败，时间是：{}", name, LocalDateTime.now().format(DATE_FORMATTER));
    }
}
