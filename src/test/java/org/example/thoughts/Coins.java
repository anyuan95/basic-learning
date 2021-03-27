package org.example.thoughts;

/**
 * FIXME failed
 *
 * @author anyuan
 * @date 2020-12-25 16:26:36
 */
public class Coins {

    public static void main(String[] args) {
//        long money = new Random().nextLong() % 100;
        long money = 6;
        System.out.println(calculateWays(money));
    }

    /**
     * 1,2,5,10
     *
     * @param totalMoney
     * @return
     */
    private static long calculateWays(long totalMoney) {
        if (totalMoney == 0L) {
            return 0L;
        } else if (totalMoney == 1L) {
            return 1;
        } else if (totalMoney == 2L) {
            return 2;
//        } else if (totalMoney == 5L) {
//            return 4;
//        } else if (totalMoney == 10L) {
//            return 9;
        } else {
            return calculateWays(totalMoney - 1)
                    + calculateWays(totalMoney - 2)
//                    + calculateWays(totalMoney - 5)
//                    + calculateWays(totalMoney - 10)
                    ;
        }
    }

}
