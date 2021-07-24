package org.example.basic.java.feature8;

import cn.hutool.core.util.HexUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.basic.java.feature8.model.User;
import org.junit.Test;

import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Spliterator是在java 8引入的一个接口，它通常和stream一起使用，用来遍历和分割序列。
 *
 * @author anyuan
 * @since 2021-07-22 11:03
 */
@Slf4j
public class TestSpliterator {

    private String call(Spliterator<User> spliterator) {
        int current = 0;
        while (spliterator.tryAdvance(user -> user.setFirstName("user ".concat(user.getId().toString())))) {
            current++;
        }
        return Thread.currentThread().getName() + ":" + current;
    }

    @Test
    public void testTrySplit() {
        final AtomicLong al = new AtomicLong();
        final List<User> users = Stream.generate(() -> new User(al.getAndIncrement()))
                .limit(1000)
                .collect(Collectors.toList());
        final Spliterator<User> spliterator1 = users.spliterator();
        final Spliterator<User> spliterator2 = spliterator1.trySplit();
        final Spliterator<User> spliterator3 = spliterator2.trySplit();

        log.info("before tryAdvance: {}", spliterator1.estimateSize());
        final int characteristics = spliterator1.characteristics();
        log.info("Characteristics {}", HexUtil.toHex(characteristics));
        log.info(call(spliterator1));
        log.info(call(spliterator2));
        log.info(call(spliterator3));
        log.info("after tryAdvance {}", spliterator1.estimateSize());
        System.out.println(users);

        System.out.println(spliterator1.tryAdvance(user -> {
        }));

    }
}
