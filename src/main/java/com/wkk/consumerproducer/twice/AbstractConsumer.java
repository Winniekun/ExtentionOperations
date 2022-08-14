package com.wkk.consumerproducer.twice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author weikunkun
 * @since 2021/4/24
 */
@Slf4j
public abstract class AbstractConsumer implements Consumer, Runnable{
    @Override
    public void run() {
        try {
            consumer();
        } catch (Exception e) {
            log.error("consumer error, message: ", e);
        }
    }

    @Override
    public void consumer() throws InterruptedException{
        throw new UnsupportedOperationException();
    }
}
