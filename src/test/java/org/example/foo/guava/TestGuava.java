package org.example.foo.guava;

import com.google.common.collect.HashBasedTable;
import org.junit.Test;

/**
 * @author anyuan
 * @since 2021-04-19 16:20
 */
public class TestGuava {

    @Test
    public void testHashBasedTable() {
        final HashBasedTable<Integer, Integer, String> hashBasedTable = HashBasedTable.<Integer, Integer, String>create();
        hashBasedTable.put(1,1,"1-1");
        hashBasedTable.put(1,2,"1-2x");
        hashBasedTable.put(1,2,"1-2");
        System.out.println(hashBasedTable);
        System.out.println(hashBasedTable.row(1));
        System.out.println(hashBasedTable.column(2));
        System.out.println(hashBasedTable.get(1, 2));
    }
}
