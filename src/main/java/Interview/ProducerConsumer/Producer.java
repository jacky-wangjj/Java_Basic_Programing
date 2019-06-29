package main.java.Interview.ProducerConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * @author wangjj17@lenovo.com
 * @date 2019/6/29
 */
public class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                queue.put(produce());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int produce() {
        int n = (int) (Math.random() * 1000);
        System.out.println("Thread " + Thread.currentThread().getId() + " Producer " + n);
        return n;
    }
}