package org.example.spring.testing.testng.common.exception;

/**
 * 异常抛出工具类
 *
 * @author anyuan
 * @date 2022-02-16 22:45
 */
public class AnyThrow {

    public static void throwUnchecked(Throwable e) {
        AnyThrow.throwAny(e);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void throwAny(Throwable e) throws E {
        throw (E) e;
    }
}