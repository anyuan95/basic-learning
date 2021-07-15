package org.example.juc.future;

import org.example.juc.SleepHelper;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * @author anyuan
 * @since 2021-07-07 08:56
 */
public class TestComplexCompletableFuture {


    public static int do1() {
        SleepHelper.sleepSeconds(5);
        System.out.println(1);
        return 1;
    }

    public static int do2() {
        SleepHelper.sleepSeconds(10);
        AnyThrow.throwUnchecked(new Exception());
        System.out.println(2);
        return 2;
    }

    public static int do3() {
        SleepHelper.sleepSeconds(15);
        System.out.println(3);
        return 3;
    }

    @Test
    public void test1() throws InterruptedException {
        System.out.println(LocalDateTime.now());
        final CompletableFuture<Void> future = CompletableFuture.allOf(
                CompletableFuture.supplyAsync(TestComplexCompletableFuture::do1),
                CompletableFuture.supplyAsync(TestComplexCompletableFuture::do2),
                CompletableFuture.supplyAsync(TestComplexCompletableFuture::do3));
        future.exceptionally(throwable -> {
            System.out.println(throwable);
            System.out.println(LocalDateTime.now());
            return null;
        }).handleAsync((unused, throwable) -> {
            System.out.println("do handleAsync");
            if (throwable != null) {
                System.out.println("something error");
            }
            return null;
        });
        Thread.sleep(100000L);


    }

    @Test
    public void test2() {
        System.out.println(LocalDateTime.now());
        final CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            do2();
            return null;
        });
//        future.thenApply(o -> {
//            System.out.println("apply");
//            return null;
//        });
        future.handle((o, throwable) -> {
            System.out.println(LocalDateTime.now());
            System.out.println("handle");
            System.out.println(Thread.currentThread().getName());
            return null;
        });
        SleepHelper.sleepSeconds(100);
    }

    @Test
    public void multiHandleAsyncFromOneCompletableFuture() {
        final CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            do1();
            return null;
        });
        future.handleAsync((o, throwable) -> {
            System.out.println("start part 1 " + Thread.currentThread().getName());
            SleepHelper.sleepSeconds(5);
            System.out.println("finish part 1");
            return null;
        });
        future.handleAsync((o, throwable) -> {
            System.out.println("start part 2 " + Thread.currentThread().getName());
            SleepHelper.sleepSeconds(5);
            System.out.println("finish part 2");
            return null;
        });


        System.out.println("main thread go to sleep");
        SleepHelper.sleepSeconds(30);
    }

    @Test
    public void multiHandleFromOneCompletableFuture() {
        final CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            do1();
            return null;
        });
        future.handle((o, throwable) -> {
            System.out.println("start part 1 " + Thread.currentThread().getName());
            SleepHelper.sleepSeconds(5);
            System.out.println("finish part 1");
            return null;
        });
        future.handle((o, throwable) -> {
            System.out.println("start part 2 " + Thread.currentThread().getName());
            SleepHelper.sleepSeconds(5);
            System.out.println("finish part 2");
            return null;
        });
        future.handle((o, throwable) -> {
            System.out.println("start part 3 " + Thread.currentThread().getName());
            SleepHelper.sleepSeconds(5);
            System.out.println("finish part 3");
            return null;
        });

        System.out.println("main thread go to sleep");
        SleepHelper.sleepSeconds(30);
    }

    @Test
    public void mixedOperations() {
        final CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            do1();
            return null;
        });
        future.handle((o, throwable) -> {
            System.out.println("start handle part 1 " + Thread.currentThread().getName());
            SleepHelper.sleepSeconds(5);
            System.out.println("finish handle part 1");
            return null;
        });
        future.handle((o, throwable) -> {
            System.out.println("start handle part 2 " + Thread.currentThread().getName());
            SleepHelper.sleepSeconds(5);
            System.out.println("finish handle part 2");
            return null;
        });
        future.handleAsync((o, throwable) -> {
            System.out.println("start handleAsync part 1 " + Thread.currentThread().getName());
            SleepHelper.sleepSeconds(5);
            System.out.println("finish handleAsync part 1");
            return null;
        });
        future.handleAsync((o, throwable) -> {
            System.out.println("start handleAsync part 2 " + Thread.currentThread().getName());
            SleepHelper.sleepSeconds(5);
            System.out.println("finish handleAsync part 2");
            return null;
        });

        System.out.println("main thread go to sleep");
        SleepHelper.sleepSeconds(30);
    }
}
