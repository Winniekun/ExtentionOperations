package com.wkk.consumerproducer.twice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weikunkun
 * @since 2021/4/24
 */
@Slf4j
public class ModelWithBlockingQueue implements Model {
    private BlockingQueue<Task> blockingQueue;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public ModelWithBlockingQueue(int capacity) {
        this.blockingQueue = new LinkedBlockingQueue<>(capacity);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ProducerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ConsumerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer implements Runnable, Consumer {

        @Override
        public void consumer() {
            try {
                Task take = blockingQueue.take();
                Thread.sleep((long) (500 + Math.random() * 5000));
                System.out.println("consumer a task " + take.getTask() + " " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                log.error("consumer something error, msg: ", e);
            }
        }
    }

    private class ProducerImpl extends AbstractProducer implements Runnable, Producer {
        @Override
        public void producer() {
            try {
                Thread.sleep((long) (50 + Math.random() * 5000));
                Task task = new Task();
                task.setTask(atomicInteger.getAndIncrement());
                System.out.println("producer a task " + task.getTask() + " " + Thread.currentThread().getName());
                blockingQueue.put(task);
            } catch (InterruptedException e) {
                log.error("producer something error, msg: ", e);
            }
        }
    }

    public static void main(String[] args) {
        ModelWithBlockingQueue queue = new ModelWithBlockingQueue(5);
        for (int i = 0; i < 5; i++) {
            new Thread(queue.newRunnableProducer(), "producer" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(queue.newRunnableConsumer(), "consumer" + i).start();
        }
    }
}
