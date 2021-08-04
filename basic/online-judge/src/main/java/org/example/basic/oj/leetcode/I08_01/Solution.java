package org.example.basic.oj.leetcode.I08_01;

/**
 * 有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。
 * 实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007
 *
 * @author anyuan
 * @since 2021-08-03 15:16
 */
public class Solution {

    /**
     * 加一个数组用来打表
     *
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        long[] records = new long[Math.max(4, n + 1)];
        records[0] = 0;
        records[1] = 1;
        records[2] = 2;
        records[3] = 4;
        for (int totalSteps = 4; totalSteps <= n; totalSteps++) {
            records[totalSteps] = (records[totalSteps - 1] + records[totalSteps - 2] + records[totalSteps - 3]) % 1000000007;
        }
        return (int) (records[n] % 1000000007);
    }

    // TLE 超时
    public int count(int totalSteps) {
        if (totalSteps < 1) {
            return 0;
        } else if (totalSteps == 1) {
            return 1;
        } else if (totalSteps == 2) {
            return 2;
        } else if (totalSteps == 3) {
            return 4;
        }
        return count(totalSteps - 1) + count(totalSteps - 2) + count(totalSteps - 3);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.waysToStep(61));
    }

}
