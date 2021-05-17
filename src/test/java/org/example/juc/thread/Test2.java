package org.example.juc.thread;

public class Test2 {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                }
            }
        });
        thread.setDaemon(true);
        thread.run();
        System.out.println("end");
        Thread.sleep(5000);
    }
}
