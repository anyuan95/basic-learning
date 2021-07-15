package org.example.basic.strictfp_;

/**
 * @author anyuan
 * @since 2021-05-26 11:16
 */
public strictfp class TestStrictfp {

    public static void main(String[] args) {
        float aFloat = 0.6710339f;
        double aDouble = 0.04150553411984792d;
        double sum = aFloat + aDouble;
        float quotient = (float)(aFloat / aDouble);
        System.out.println("float: " + aFloat);
        System.out.println("double: " + aDouble);
        System.out.println("sum: " + sum);
        System.out.println("quotient: " + quotient);
    }
}
