package org.example.disruptor.non_lambda;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.openjdk.jol.info.ClassLayout;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

/**
 * @author anyuan
 * @since 2021-05-24 11:17
 */
public class TestDisruptor {

    public static void main(String[] args) {
        final MyEventFactory eventFactory = new MyEventFactory();
        int ringBufferSize = 1 << 10;
        final Disruptor<MyEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, Executors.defaultThreadFactory());
        final MyEventHandler1 eventHandler1 = new MyEventHandler1();
        final MyEventHandler2 eventHandler2 = new MyEventHandler2();
        disruptor.handleEventsWith(eventHandler1, eventHandler2);
        final RingBuffer<MyEvent> ringBuffer = disruptor.start();
        final MyEventProducer eventProducer = new MyEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for (int i = 100; i > 0; i--) {
            bb.putLong(0, i);
            eventProducer.onData(bb);
        }
        disruptor.shutdown();
    }
}
