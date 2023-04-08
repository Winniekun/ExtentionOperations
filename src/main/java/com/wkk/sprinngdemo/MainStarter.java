package com.wkk.sprinngdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/7/14
 */
@SpringBootApplication
public class MainStarter implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        new SpringApplication(MainStarter.class).run(args);
    }


    @Override
    public void run(String... args) throws Exception {
        SaveOrder saveOrder = new SaveOrder();
        saveOrder.setId(1L);
        orderService.saveOrder(saveOrder);

        UpdateOrder updateOrder = new UpdateOrder();
        updateOrder.setOrderId(2L);
        orderService.updateOrder(updateOrder);
    }
}
