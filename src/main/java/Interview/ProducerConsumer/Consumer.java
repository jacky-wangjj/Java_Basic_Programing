package main.java.Interview.ProducerConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * @author wangjj17@lenovo.com
 * @date 2019/6/29
 */
public class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                consume(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume(int n) {
        System.out.println("Thread " + Thread.currentThread().getId() + " Consume " + n);
    }
}