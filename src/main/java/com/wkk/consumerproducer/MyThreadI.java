package com.wkk.consumerproducer;

/**
 * @author weikunkun
 * @since 2021/4/24
 */
public class MyThreadI implements Runnable{
    @Override
    public void run() {
        System.out.println("run");
    }
}
