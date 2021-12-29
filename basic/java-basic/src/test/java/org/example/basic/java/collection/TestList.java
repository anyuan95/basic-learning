package org.example.basic.java.collection;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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

    @Test
    public void testContains() {
        final ArrayList<String> list = Lists.newArrayList("a1", "a2", "a2");
        final HashSet<String> set = Sets.newHashSet();
        set.add("a1");
        set.add("a2");

        System.out.println(list.containsAll(set));
        System.out.println(set.containsAll(list));


    }
}
