package org.example.disruptor.non_lambda;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author anyuan
 * @since 2021-05-24 11:25
 */
public class MyEventProducer {

    private final RingBuffer<MyEvent> ringBuffer;

    private static final EventTranslatorOneArg<MyEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<MyEvent, ByteBuffer>() {
        @Override
        public void translateTo(MyEvent event, long sequence, ByteBuffer arg0) {
            event.setValue(arg0.getLong(0));
        }
    };

    public MyEventProducer(RingBuffer<MyEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer buffer) {
        ringBuffer.publishEvent(TRANSLATOR, buffer);
    }

}
