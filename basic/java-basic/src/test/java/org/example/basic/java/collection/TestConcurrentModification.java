package org.example.basic.java.collection;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-08 09:16
 */
public class TestConcurrentModification {

    @Test
    public void testAddInForiArrayList() {
        final List<Integer> list = Lists.<Integer>newArrayList(1,2,3,4,5,6,7,8,9,0);
        for (int i = 0; i < list.size(); i++) {
            if (i == 5) {
                list.add(10);
            }
        }
        System.out.println(list);
    }

    @Test
    public void testAddAtIndexInForiArrayList() {
        final List<Integer> list = Lists.<Integer>newArrayList(1,2,3,4,5,6,7,8,9,0);
        for (int i = 0; i < list.size(); i++) {
            if (i == 5) {
                list.add(i, 10);
                i++;
            }
            System.out.print(list.get(i));
        }
        System.out.println(list);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testAddInForEachArrayList() {
        final List<Integer> list = Lists.<Integer>newArrayList(1,2,3,4,5,6,7,8,9,0);
        for (Integer value : list) {
            if (value == 5) {
                list.add(10);
            }
        }
        System.out.println(list);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testAddAtIndexInForEachArrayList() {
        final List<Integer> list = Lists.<Integer>newArrayList(1,2,3,4,5,6,7,8,9,0);
        for (Integer value : list) {
            if (value == 5) {
                list.add(list.indexOf(value), 10);
            }
        }
        System.out.println(list);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testAddAtIndexInIteratorArrayList() {
        final List<Integer> list = Lists.<Integer>newArrayList(1,2,3,4,5,6,7,8,9,0);
        final Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            final Integer next = iterator.next();
            if (next == 5) {
//                list.add(list.indexOf(next), 10);
                list.add( 10);
            }
        }
        System.out.println(list);
    }

}
