package org.example.foo.equals;

import cn.hutool.core.collection.CollUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.Banner;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.runner.Request.method;

@PrepareForTest(Integer.class)
@RunWith(PowerMockRunner.class)
public class Ais123 {

    @Before
    public void before() {
        // "value" is just a place to store an incrementing integer
        AtomicInteger value = new AtomicInteger(1);
//        replace(method(Integer.class, "intValue"))
//                .with((proxy, method, args) -> value.getAndIncrement());
    }

    @Test
    public void test() {
        Integer a = 1;
        if (a == 1 && a == 2 && a == 3) {
            System.out.println("Success");
        } else {
            Assert.fail("(a == 1 6& a == 2 && a == 3) != true,a = " + a.intValue());
        }
    }
}
