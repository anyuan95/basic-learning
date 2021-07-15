package org.example.iterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author anyuan
 * @since 2021-05-11 15:32
 */
public class TestIterator {

    public static void main(String[] args) {
        final LinkedList_ linkedList_ = new LinkedList_();
        linkedList_.add(1);
        linkedList_.add(2);
        System.out.println(linkedList_);

        for (Object o : linkedList_) {
            System.out.println(o);
        }

        System.out.println(linkedList_.remove(1));
        System.out.println(linkedList_);
    }


    private void test(final String a) {

    }

}
