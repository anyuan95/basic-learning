package org.example.juc.future;

/**
 * @author anyuan
 * @since 2021-07-01 17:45
 */
public class AnyThrow {

    private AnyThrow() {
    }

    public static void throwUnchecked(Throwable throwable) {
        AnyThrow.throwAny(throwable);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void throwAny(Throwable throwable) throws E {
        throw (E) throwable;
    }
}
