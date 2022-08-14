package com.wkk.consumerproducer.twice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author weikunkun
 * @since 2021/4/24
 */
@Slf4j
public abstract class AbstractProducer implements Runnable, Producer{
    @Override
    public void run() {
        try {
            producer();
        } catch (Exception e) {
            log.error("producer error, msg: ", e);
        }
    }

    @Override
    public void producer() throws InterruptedException{
        throw new UnsupportedOperationException();
    }
}
