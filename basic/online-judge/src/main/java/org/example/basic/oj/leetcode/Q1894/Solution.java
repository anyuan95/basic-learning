package org.example.basic.oj.leetcode.Q1894;

/**
 * @author anyuan
 * @since 2021-09-10 09:17
 */
class Solution {

    /**
     * 本题有一个优化的做法，可以通过前缀和+二分的方式
     * 可以认为是用空间换时间
     *
     * 理论上使用前缀和+二分方式速度更快。
     * 因为实际上当前方法求sum的时间复杂度也是O(N)，而在查找过程中二分方式只需要O(logN)，但当前方式最差情况下需要O(N)。
     * 通过将计算得到的前缀和存放到原数组中的方式，使空间复杂度降为O(1)，
     * 只不过需要注意数组越界的情况，即前缀和只需要计算到K就行。因为大于k的内容不需要计算，不可能是要求的结果。
     *
     *
     * @param chalk
     * @param k
     * @return
     */
    public int chalkReplacer(int[] chalk, int k) {
        if (chalk.length == 1) {
            return 0;
        }
        final int n = chalk.length;
        long chalkSum = 0;
        for (int i : chalk) {
            chalkSum += i;
        }
        // 这里不要用stream的asLongStream方式，巨慢
//        long chalkSum = Arrays.stream(chalk).asLongStream().sum();
        // 没必要计算前缀和，因为只需要求一个去掉总和后剩下的内容逐个递减就可以了
        int lastRoundRestChalks = (int) (k % chalkSum);
        for (int i = 0; i < n; i++) {
            lastRoundRestChalks -= chalk[i];
            if (lastRoundRestChalks < 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.chalkReplacer(new int[]{5,1,5}, 22));
//        System.out.println(solution.chalkReplacer(new int[]{3, 4, 1, 2}, 25));
        System.out.println(solution.chalkReplacer(new int[]{1}, 1000000));

    }
}
