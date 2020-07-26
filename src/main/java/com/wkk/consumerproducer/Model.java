package com.wkk.consumerproducer;

/**
 * @Time: 2020/7/26下午9:35
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public interface Model {
    Runnable newRunnableProducer();
    Runnable newRunnableConsumer();
}
