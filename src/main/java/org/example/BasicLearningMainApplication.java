package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author anyuan
 * @date 2020-11-23 11:39:14
 */
@SpringBootApplication
public class BasicLearningMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicLearningMainApplication.class, args);
    }

    public void test() {
        System.gc();
    }
}
