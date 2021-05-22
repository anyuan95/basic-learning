package org.example.juc.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @since 2021-05-21 15:16
 */
public class TestCompletableFuture {

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    /*private static double priceOfAmazon() {
        delay();
        throw new RuntimeException("product not exist!");
    }*/

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }

    public static void main(String[] args) {
        final CompletableFuture<Void> all = CompletableFuture.allOf(CompletableFuture.supplyAsync(TestCompletableFuture::priceOfJD),
                CompletableFuture.supplyAsync(TestCompletableFuture::priceOfTB),
                CompletableFuture.supplyAsync(TestCompletableFuture::priceOfTM));

//        all.thenApply()

    }
}
