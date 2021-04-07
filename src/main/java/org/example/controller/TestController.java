package org.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author anyuan
 * @since 2021-04-02 15:06
 */
@RequestMapping("test")
@RestController
public class TestController {

    @GetMapping(path = "tcc", consumes = "application/tcc")
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "yes, created")
    public String tcc() {
        return "yes, it is tcc";
    }

    @GetMapping("mono")
    public Mono<String> mono() {
        return Mono.just("Welcome to reactive world ~");
    }

    @GetMapping(value = "flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux() {
        Flux<String> result = Flux
                .fromStream(IntStream.range(1, 5).mapToObj(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    return "flux data--" + i;
                }));
        return result;
    }

}
