package org.example.basic.java.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-18 15:50
 */
public class TestList {

    @Test
    public void testList1() {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<Integer> list2 = new ArrayList<>();
        list1.stream().filter(integer -> integer == 1).forEach(list2::add);
    }
}
