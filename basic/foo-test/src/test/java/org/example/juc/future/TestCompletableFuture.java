package org.example.juc.future;

import org.example.juc.SleepHelper;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @since 2021-05-21 15:16
 */
public class TestCompletableFuture {

    private static double priceOfTM() {
//        delay();
        SleepHelper.sleepSeconds(5);
        return 1.00;
    }

    private static boolean tryBuyFromTM() {
//        delay();
        SleepHelper.sleepSeconds(5);
        return true;
    }

    private static double priceOfTB() {
//        delay();
        SleepHelper.sleepSeconds(5);
        return 2.00;
    }

    private static boolean tryBuyFromTB() {
//        delay();
        SleepHelper.sleepSeconds(5);
        return false;
    }

    private static double priceOfJD() {
//        delay();
        SleepHelper.sleepSeconds(5);
        throw new RuntimeException();
//        return 3.00;
    }

    private static boolean tryBuyFromJD() {
        delay();
        return true;
    }

    private static String getProductName() {
//        delay();
        SleepHelper.sleepSeconds(5);
//        throw new RuntimeException();
        return "food";
    }

    /*private static double priceOfAmazon() {
        delay();
        throw new RuntimeException("product not exist!");
    }*/

    private static void delay() {
        int time = new Random().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }

    public static void main(String[] args) throws InterruptedException {
        final CompletableFuture<Void> all = CompletableFuture.allOf(CompletableFuture.supplyAsync(TestCompletableFuture::priceOfJD)
                        .handle((aDouble, throwable) -> {TestCompletableFuture.getProductName();AnyThrow.throwUnchecked(throwable); return null;}),
                CompletableFuture.supplyAsync(TestCompletableFuture::priceOfTB),
                CompletableFuture.supplyAsync(TestCompletableFuture::priceOfTM));
//        all.thenApply()
        all.handle((unused, throwable) -> {
            System.out.println("yes");
            System.out.println(throwable);
            return null;
        });

        Thread.sleep(100000L);
    }
}
