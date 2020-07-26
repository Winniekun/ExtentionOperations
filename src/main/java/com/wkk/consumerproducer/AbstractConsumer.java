package com.wkk.consumerproducer;

/**
 * @Time: 2020/7/26下午9:34
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public abstract class AbstractConsumer implements Runnable, Consumer {

    @Override
    public void run() {
        try{
            consumer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
