package org.example.collection.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @since 2021-05-20 11:15
 */
public class TestDelayQueue {

    static class MyDelayTask implements Delayed {
        String name;
        long runningTime;

        public MyDelayTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final long now = System.currentTimeMillis();
        final MyDelayTask t1 = new MyDelayTask("t1", now + 1000);
        final MyDelayTask t2 = new MyDelayTask("t2", now + 2000);
        final MyDelayTask t3 = new MyDelayTask("t3", now + 3000);

        DelayQueue<MyDelayTask> delayQueue = new DelayQueue<>();
        delayQueue.put(t1);
        delayQueue.put(t2);
        delayQueue.put(t3);

        System.out.println(delayQueue);

        for (int i = 0; i < 5; i++) {
            System.out.println(delayQueue.take().name);
        }
    }
}
