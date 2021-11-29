package org.example.dateformat;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author anyuan
 * @date 2021-11-24 10:44
 */
public class TestDateFormat {

    @Test
    public void test1() {
        final Date date = new Date();
        System.out.println(DateUtil.format(date, "YYYY第ww周"));
        System.out.println(DateUtil.format(date, "MM/dd"));
        System.out.println(DateUtil.format(date, "YYYY-MM"));
        System.out.println(DateUtil.format(date, "YYYY'Q'q"));
    }

    @Test
    public void test2() {
        // NO q or Q
        final SimpleDateFormat format = new SimpleDateFormat("yyyyQq");
        System.out.println(format.format(new Date()));
    }

    @Test
    public void test3() {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy'Q'q");
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
    }

    @Test
    public void test4() {
        System.out.println(DateUtil.yearAndQuarter(new Date()));
    }

    @Test
    public void test5() {
        final Date date = new Date();
        System.out.println(DateUtil.year(date) + "Q" + DateUtil.quarter(date));
    }
}
