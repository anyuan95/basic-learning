package org.example.juc.phaser;

import java.util.concurrent.Phaser;

/**
 * @author anyuan
 * @since 2021-05-17 13:49
 */
public class TestPhaser {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser() {
            /**
             * @param phase
             * @param registeredParties
             * @return Returns: true if this phaser should terminate
             */
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("Phase: " + phase + ", RegisteredParties: " + registeredParties);
                return false;
//                return super.onAdvance(phase, registeredParties);
            }
        };
        phaser.bulkRegister(3);

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    phaser.arriveAndAwaitAdvance();
                }
            }).start();
        }


    }
}
