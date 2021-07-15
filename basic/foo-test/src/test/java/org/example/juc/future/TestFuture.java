package org.example.juc.future;

import org.example.juc.SleepHelper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author anyuan
 * @since 2021-05-21 11:25
 */
public class TestFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final Callable<String> c = () -> {
            SleepHelper.sleepSeconds(3);
            return "finished";
        };
//        final FutureTask<String> futureTask = new FutureTask<>(c);

        final ExecutorService executorService = Executors.newFixedThreadPool(5);
//        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<String> future = executorService.submit(c);
        System.out.println(future.get());

        executorService.shutdown();
    }
}
