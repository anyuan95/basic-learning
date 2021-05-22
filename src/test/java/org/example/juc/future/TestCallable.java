package org.example.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author anyuan
 * @since 2021-05-21 14:23
 */
public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final FutureTask<String> task = new FutureTask<>(() -> "hello");
        final Thread thread = new Thread(task);
        thread.start();
        System.out.println(task.get());

    }
}
