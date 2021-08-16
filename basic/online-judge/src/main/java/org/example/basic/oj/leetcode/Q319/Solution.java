package org.example.basic.oj.leetcode.Q319;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-14 16:26
 */
class Solution {
    /**
     * 脑筋急转弯
     * <p>
     * 第i个灯泡在进行j轮操作后是亮的还是暗的？
     * - 如果是亮的，说明第i个灯泡在j轮中经过了奇数次操作；
     * - 如果是暗的，说明第i个灯泡在j轮中经过了偶数次操作；
     * <p>
     * 考虑第i个灯泡会在哪些轮被操作呢？
     * - 会在它的所有[因数]的轮各被操作一次。
     * <p>
     * 那么问题就转化成了：什么样的i有奇数个因数？
     * - 由于因数都是成对出现的，只有在i是完全平方数时，才会因为有一对因数相同，而有总计奇数个因数。
     * <p>
     * 所以，最终问题转化成了：从[1,i]中，有多少个完全平方数？
     * - 在[1,j]中，有根号i个完全平方数。
     * <p>
     * 故，结果为根号i向下取整。
     *
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    public int bulbSwitch_force(int n) {
        Boolean[] bulbs = new Boolean[n + 1];
        Arrays.fill(bulbs, false);
        // i是轮数
        for (int i = 1; i <= n; i++) {
            // j是位置
            for (int j = i; j <= n; j += i) {
                bulbs[j] = !bulbs[j];
            }
        }
        return (int) Arrays.stream(bulbs).filter(aBoolean -> aBoolean).count();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.bulbSwitch_force(3));

        for (int i = 0; i < 50; i++) {
//            System.out.println(i + "=" + solution.bulbSwitch_force(i));
            if (solution.bulbSwitch(i) != solution.bulbSwitch_force(i)) {
                System.out.println("nope at " + i);
                return;
            }
        }
        System.out.println("ok");
    }
}
