package com.wkk.consumerproducer;

import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Time: 2020/7/26下午9:44
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class Model_BlockingQueue implements Model {
    private BlockingQueue<Task> blockingQueue;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public Model_BlockingQueue(int cap){
        this.blockingQueue = new ArrayBlockingQueue<>(cap);
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer implements Runnable, Consumer{

        @Override
        public void consumer() {
            try {
                Task task = blockingQueue.take();
                Thread.sleep((long) (500 + Math.random() * 5000));
                System.out.println("consume: " + task.getTask());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ProducerImpl extends AbstractProducer implements Runnable, Producer{

        @Override
        public void produce() {

            try {
                Thread.sleep((long) (50 + Math.random() * 5000));
                Task task = new Task();
                task.setTask(atomicInteger.incrementAndGet());
                System.out.println("produce: " + task.getTask());
                blockingQueue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Model model = new Model_BlockingQueue(5);
        for (int i = 0; i < 5; i++) {
            new Thread(model.newRunnableProducer()).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }

    }


}
