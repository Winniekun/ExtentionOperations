package com.wkk.consumerproducer.twice;

/**
 * 生产者消费者模型
 * @author weikunkun
 * @since 2021/4/24
 */
public interface Model {
    Runnable newRunnableConsumer();
    Runnable newRunnableProducer();
}
