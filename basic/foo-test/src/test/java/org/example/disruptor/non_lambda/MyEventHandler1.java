package org.example.disruptor.non_lambda;

import com.lmax.disruptor.EventHandler;

/**
 * @author anyuan
 * @since 2021-05-24 11:21
 */
public class MyEventHandler1 implements EventHandler<MyEvent> {

    @Override
    public void onEvent(MyEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("1: event: " + event.getValue() + ", sequence: " + sequence + ", endOfBatch: " + endOfBatch);
    }
}
