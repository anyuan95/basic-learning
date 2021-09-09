package org.example.basic.java.collection;

import org.junit.Test;

import java.util.BitSet;

/**
 * @author anyuan
 * @since 2021-09-08 16:45
 */
public class TestBitSet {

    @Test
    public void testNew() {
        final BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(2);
        bitSet.set(3);
        bitSet.set(Integer.MAX_VALUE);
        System.out.println(bitSet);
    }

    @Test
    public void testNewNoSticky() {
        final BitSet bitSet = new BitSet(1);
        bitSet.set(1);
        bitSet.set(2);
        bitSet.set(3);
        bitSet.set(Integer.MAX_VALUE);
        System.out.println(bitSet);
    }
}
