package org.example.spring.web.flux;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TestWebFlux {

    @Test
    public void testMonoJust() {
        final Mono<String> mono = Mono.just("hello");
        mono.subscribe(log::info);
    }

    @Test
    public void testMonoDelay() {
        log.info("start");
        final Mono<String> mono = Mono.delay(Duration.ofSeconds(5))
                .thenReturn("world");
        mono.subscribe(log::info);
        ThreadUtil.safeSleep(10000);
    }

    @Test
    public void testFluxJust() {
        final Flux<String> flux = Flux.just("hello", "world");
        flux.subscribe(log::info);
    }

    @Test
    public void testFluxInterval() {
        final Flux<Long> flux = Flux.interval(Duration.ofSeconds(1));
        flux.subscribe(s -> log.info("{}", s));
        ThreadUtil.safeSleep(10000);
    }

    @Test
    public void testFluxRange() {
        final Flux<Integer> flux = Flux.range(0, 10);
        flux.subscribe(s -> log.info("{}", s));
    }

    @Test
    public void testBackPressure() {
        Flux<Integer> flux = Flux.range(1, 10);

        flux.subscribe(new Subscriber<Integer>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1); // 每次请求一个元素
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Received: " + integer);
                ThreadUtil.safeSleep(1000);
                subscription.request(1); // 处理完后再请求下一个
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("All items processed");
            }
        });
    }

    @Test
    public void testFluxError() {
        Flux<String> flux = Flux.just("a", "b", "c")
                .concatWith(Flux.just("d", "e"))
                .concatWith(Flux.error(new RuntimeException("Error occurred")))
                .concatWithValues("f", "g")
                .doOnError(throwable -> log.error("Error: {}", throwable.getMessage()))
                .onErrorReturn("default");
        flux.subscribe(System.out::println);
    }

    @Test
    public void testReshape() {
        // 假设我们有一组用户ID
        List<Integer> userIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 创建Flux流
        Flux<Integer> userIdFlux = Flux.fromIterable(userIds);

        // 将用户ID进行分批处理，假设每次批量处理3个
        userIdFlux
                .buffer(3) // 每3个元素打包成一个List
                .flatMap(userBatch -> {
                    System.out.println("Processing batch: " + userBatch);
                    // 对每一批用户ID发起并行请求，返回一个Mono<List<User>>
                    return Flux.fromIterable(userBatch)
//                            .flatMap(userId -> fetchUserById(userId)) // 模拟异步获取用户数据，组内无序
                            .concatMap(userId -> fetchUserById(userId)) // 模拟异步获取用户数据，组内有序
                            .collectList(); // 将Flux<User>转换为Mono<List<User>>
                })
                .doOnNext(users -> {
                    // 对获取到的用户数据进行处理
                    System.out.println("Received users: " + users);
                })
                .subscribe();
        ThreadUtil.safeSleep(10000);
    }

    // 模拟通过ID获取用户信息的异步请求
    private static Mono<String> fetchUserById(Integer userId) {
        return Mono.just("User-" + userId) // 假设每个用户的数据就是 "User-X"
                .delayElement(Duration.ofMillis(500)); // 模拟异步请求延迟
    }

}
