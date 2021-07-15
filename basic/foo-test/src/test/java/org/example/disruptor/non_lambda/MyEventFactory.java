package org.example.disruptor.non_lambda;

import com.lmax.disruptor.EventFactory;

/**
 * @author anyuan
 * @since 2021-05-24 11:18
 */
public class MyEventFactory implements EventFactory<MyEvent> {

    @Override
    public MyEvent newInstance() {
        return new MyEvent();
    }
}
