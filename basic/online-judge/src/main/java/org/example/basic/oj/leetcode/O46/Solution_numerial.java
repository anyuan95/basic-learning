package org.example.basic.oj.leetcode.O46;

/**
 * @author anyuan
 * @since 2021-09-27 11:08
 */
class Solution_numerial {
    /**
     * 这个实现方式直接在原有的num上进行处理了。
     * 总体上的思路还是和Solution一样的。
     * 因为从Solution可以得知，正向和反向计算都能得到一样的正确的结果。所以直接使用num进行处理，每次从尾部进行取余。
     *
     * 又优化了一次，不再一位一位的切了，改为两位两位的切。处理完一个位置就把两个格的滑块向前移一位。
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        int last = 1, last2 = 1;
        while (num != 0) {
            int last2Digits = num % 100;
            int curr = last;
            if (last2Digits >= 10 && last2Digits <= 25) {
                curr += last2;
            }
            num /= 10;

            last2 = last;
            last = curr;
        }
        return last;
    }

    public static void main(String[] args) {
        final Solution_numerial solution = new Solution_numerial();
        System.out.println(solution.translateNum(12258));
    }
}
