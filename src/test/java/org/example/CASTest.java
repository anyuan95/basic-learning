package org.example;

/**
 * @author anyuan
 * @date 2020-12-23 14:55:35
 */
public class CASTest {

    private SimulatedCAS value;

    public CASTest(SimulatedCAS value) {
        this.value = value;
    }

    public int getValue() {
        return value.getValue();
    }

    public int increment() {
        int oldValue = value.getValue();
        while (value.compareAndSwap(oldValue, oldValue + 1) != oldValue)
            oldValue = value.getValue();
        return oldValue + 1;
    }

    public static void main(String[] args) {
        CASTest casTest = new CASTest(new SimulatedCAS(1));
        System.out.println(casTest.increment());
    }
}

class SimulatedCAS {
    private int value;

    public SimulatedCAS(int value) {
        this.value = value;
    }

    public synchronized int getValue() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (value == expectedValue)
            value = newValue;
        return oldValue;
    }
}