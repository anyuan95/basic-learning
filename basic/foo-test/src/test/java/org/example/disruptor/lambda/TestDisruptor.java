package org.example.disruptor.lambda;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ExceptionHandlerWrapper;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

/**
 * @author anyuan
 * @since 2021-05-24 11:17
 */
public class TestDisruptor {

    public static void main(String[] args) {
        int ringBufferSize = 1 << 10;

        // Construct the Disruptor
        final Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(MyEvent::new, ringBufferSize, Executors.defaultThreadFactory(),
                ProducerType.SINGLE, new BlockingWaitStrategy());

        // Connect the handler
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
            System.out.println("1: event: " + event.getValue() + ", sequence: " + sequence + ", endOfBatch: " + endOfBatch);
            if (event.getValue() == 50L) {
                throw new Exception("excccception");
            }
        });
//        disruptor.setDefaultExceptionHandler(new ExceptionHandlerWrapper());
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<MyEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, MyEvent event) {
                System.out.println("handleEventException");
                ex.printStackTrace();
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                System.out.println("handleOnStartException");
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                System.out.println("handleOnShutdownException");
            }
        });

        // Start the Disruptor, starts all threads running
        // Get the ring buffer from the Disruptor to be used for publishing.
        final RingBuffer<MyEvent> ringBuffer = disruptor.start();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (int i = 100; i > 0; i--) {
            bb.putLong(0, i);
            ringBuffer.publishEvent((event, sequence, arg0) -> event.setValue(arg0.getLong(0)), bb);
        }
        disruptor.shutdown();
    }
}
