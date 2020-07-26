package com.wkk.consumerproducer;

/**
 * @Time: 2020/7/26下午9:33
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public abstract class AbstractProducer implements Runnable, Producer{

    @Override
    public void run() {
        while (true){
            try{
                produce();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
