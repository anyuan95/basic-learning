package org.example.basic.invoke;

/**
 * @author anyuan
 * @since 2021-06-07 16:10
 */
public class TestDL {

    private TestDL() {
    }

    private static TestDL TDL;

    public synchronized static TestDL ins() {
        if (TDL == null) {
            TDL = new TestDL();
        }
        return TDL;
    }

}
