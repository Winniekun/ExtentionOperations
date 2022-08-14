package com.wkk.consumerproducer.total;

import com.wkk.consumerproducer.Task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Time: 2020/7/26下午10:04
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class TotalImpl implements Model {
    private BlockingQueue<Task> blockingQueue;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public TotalImpl(int cap) {
        blockingQueue = new ArrayBlockingQueue<>(cap);
    }

    @Override
    public Runnable newRunnableProducer() {
        return new Producer();
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new Consumer();
    }

    private class Producer extends AbstractProducer implements Runnable, com.wkk.consumerproducer.total.Producer {

        @Override
        public void produce() {
            try {
                Thread.sleep((long) (50 + Math.random() * 5000));
                Task task = new Task();
                task.setTask(atomicInteger.incrementAndGet());
                System.out.println("Produce: " + task.getTask());
                blockingQueue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Consumer extends AbstractConsumer implements Runnable, com.wkk.consumerproducer.total.Consumer {


        @Override
        public void consumer() {
            try {
                Task task = blockingQueue.take();
                Thread.sleep((long) (500 + Math.random() * 5000));
                System.out.println("Consumer: " + task.getTask());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Model model = new TotalImpl(5);
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableProducer()).start();
        }

        for (int i = 0; i < 6; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }

    }


}

interface Model {
    Runnable newRunnableProducer();

    Runnable newRunnableConsumer();
}


interface Producer {
    void produce();
}

interface Consumer {
    void consumer();
}

abstract class AbstractProducer implements Runnable, Producer {

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

abstract class AbstractConsumer implements Runnable, Consumer {

    @Override
    public void run() {
        while (true) {
            try {
                consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
