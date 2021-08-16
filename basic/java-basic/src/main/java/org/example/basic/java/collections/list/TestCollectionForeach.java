package org.example.basic.java.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author anyuan
 * @since 2021-08-16 11:20
 */
public class TestCollectionForeach {

    static class MyArrayList<E> extends ArrayList<E> {
        @Override
        public Iterator<E> iterator() {
            System.out.println("iterator!");
            return super.iterator();
        }
    }

    public static void main(String[] args) {
        final MyArrayList<Object> list = new MyArrayList<>();
        list.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
        for (Object o : list) {
            // 会调用一次MyArrayList的iterator方法
            // 然后应该调用的是iterator的next方法？ --不确定，未验证
//            System.out.println(o);
        }
    }
}
