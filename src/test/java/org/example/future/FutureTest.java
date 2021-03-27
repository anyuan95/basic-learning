package org.example.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author anyuan
 * @since 2021-03-25 15:29
 */
//@Slf4j
public class FutureTest {

    private static final Logger log = LoggerFactory.getLogger(FutureTest.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(LocalDateTime.now());
//        thenApplyExample();
//        thenApplyAsyncExample();
//        testThenApply2();
//        thenAcceptExample();
//        completeExceptionallyExample();
//        completeExceptionallyExample2();
        tryWait();
        System.out.println(LocalDateTime.now());
    }

    static void tryWait() {
        final ArrayList arrayList = new ArrayList();
        final CompletableFuture<Void> cf1 = CompletableFuture.runAsync(new Task(1, arrayList));
        final CompletableFuture<Void> cf2 = CompletableFuture.runAsync(new Task(1, arrayList));
        final CompletableFuture<Void> cf3 = CompletableFuture.runAsync(new Task(1, arrayList));
        final CompletableFuture<Void> allCf = CompletableFuture.allOf(cf1, cf2, cf3);
        try {
            allCf.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(arrayList);
    }

    private static class Task implements Runnable {

        Integer productId;
        List<Integer> prices;

        public Task(Integer productId, List<Integer> prices) {
            this.productId = productId;
            this.prices = prices;
        }

        @Override
        public void run() {
            int price = 0;
            try {
                Thread.sleep((long) (Math.random() * 8000));
                price = (int) (Math.random() * 4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
        }
    }

    static void completeExceptionallyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase);
//        CompletableFuture exceptionHandler = cf.handle((s, th) -> { return (th != null) ? "message upon cancel" : ""; });
        cf.completeExceptionally(new Exception("completed exceptionally"));
//        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        System.out.println(cf.isCompletedExceptionally());
//        try {
        cf.join();
//            fail("Should have thrown an exception");
//        } catch(CompletionException ex) { // just for testing
//            assertEquals("completed exceptionally", ex.getCause().getMessage());
//        }
//
//        assertEquals("message upon cancel", exceptionHandler.join());
    }

    static void completeExceptionallyExample2() throws ExecutionException, InterruptedException {
        CompletableFuture cf = CompletableFuture
                .completedFuture("message")
//                .supplyAsync(() -> "test")
                .thenApplyAsync(s -> s)
//                .thenApply(Integer::valueOf)
                ;
//        cf.handle((object, throwable) -> {
//            int i = 1/0;
//            System.out.println(object);
//            System.out.println(throwable);
//            return "yep";
//        });
        cf.completeExceptionally(new Exception("my exception"));
        System.out.println(cf.isCompletedExceptionally());
        System.out.println(cf.get());

//                .thenApplyAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
//        CompletableFuture exceptionHandler = cf.handle((s, th) -> {
//            return (th != null) ? "message upon cancel" : "";
//        });
//        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
//        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
//        try {
//            cf.join();
//            fail("Should have thrown an exception");
//        } catch (CompletionException ex) { // just for testing
//            assertEquals("completed exceptionally", ex.getCause().getMessage());
//        }
//
//        assertEquals("message upon cancel", exceptionHandler.join());
    }

    static void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s -> {
                    sleep();
                    result.append(s);
                });
        assertTrue("Result is empty", result.length() > 0);
        System.out.println(result);
    }

    private static void testThenApply2() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("1" + Thread.currentThread().getName());
            log.info("====future=====");
            sleep();
            return 2;
        });
        future.thenApply(res -> {
            System.out.println("2" + Thread.currentThread().getName());
            log.info("====future=====");
            sleep();
            return res + 1;
        }).thenApply(res -> {
            System.out.println("3" + Thread.currentThread().getName());
            log.info("====future=====");
            sleep();
            return res + 1;
        }).thenAccept(res -> System.out.println(res));

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("4" + Thread.currentThread().getName());
            log.info("====future2=====");
            sleep();
            return 2;
        });
        future2.thenApplyAsync(res -> {
            System.out.println("5" + Thread.currentThread().getName());
            log.info("====future2=====");
            sleep();
            return res + 1;
        }).thenApplyAsync(res -> {
            System.out.println("6" + Thread.currentThread().getName());
            log.info("====future2=====");
            sleep();
            return res + 1;
        }).thenAccept(res -> System.out.println(res));
        sleep();
        sleep();
        sleep();
        sleep();
        sleep();
        sleep();
    }

    static void sleep() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(50000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));
    }

    static void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }
}
