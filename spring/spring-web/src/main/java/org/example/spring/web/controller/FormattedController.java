package org.example.spring.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author anyuan
 * @date 2022-04-06 14:09
 */
@Slf4j
@RequestMapping("/formatted")
@RestController
public class FormattedController extends BaseController {

    @GetMapping("/test")
    public String test(Date date) {
        log.info("date = [{}]", date);
        return "ok";
    }

}
