package org.example.basic.oj.leetcode.Q483;

/**
 * 求给定的数，在多少进制下能转换成所有位都为1.
 *
 * @author anyuan
 * @since 2021-08-11 10:00
 */
class Solution {

    /**
     * 实际上就是一个等比数列求和的倒推问题。
     * 难点在于如何尽量小地划分公比循环查找时的范围。
     * 然后在此范围上进行二分查找。
     * <p>
     * 秦九韶算法：减少查找值时的遍历次数
     * 等比数列求和公式及反推
     * 二项式定理
     * <p>
     * 总时间复杂度在O(logN*logN)
     *
     * @param n
     * @return
     */
    public String smallestGoodBase(String n) {
        final long number = Long.parseLong(n);
        // 根据等比数列求和公式可知，
        // Sn = a1(q^k-1)/(q-1)
        // 由于要求得的q要尽量小，所以k应尽量大
        // 由于q的最小值为2，所以k从log_2_n+1开始倒着遍历
        for (int k = ((int) (Math.log(number) / Math.log(2))) + 1; k >= 2; k--) {
            // 二分查找k
            // qmin=2, qmax=
            // 根据二项式定理可推导，n一定在(q^k和(q+1)^k)之间，
            // 故以此选择right位置
            long left = 2, right = (long) Math.pow(number, 1.0 / (k - 1)) + 1;
            while (left < right) {
                long mid = left + (right - left) / 2;
                long sum = 0;
                // 秦九韶算法
                // 正常情况下，高次多项式计算需要为n(n+1)/2次乘法和n次加法
                // 使用秦九韶算法，可以降低到n次乘法和n次加法
                for (int i = 0; i < k; i++) {
                    sum = sum * mid + 1;
                }
                if (sum == number) {
                    return String.valueOf(mid);
                } else if (sum < number) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // 暴力查找公比
            // n的取值范围是 [3, 10^18]
            // MAX(right) = (10^18)^(1.0/(2-1)) = 10^18
            // 必TLE
//            for (long q = left; q <= right; q++) {
//                if (((long) Math.pow(q, k) - 1) == number * (q - 1)) {
//                    // Sn * (q-1) == q^k-1
//                    return String.valueOf(q);
//                }
//            }
        }
        return String.valueOf(number - 1);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.smallestGoodBase("13"));
        System.out.println(solution.smallestGoodBase("4681"));
        System.out.println(solution.smallestGoodBase("1000000000000000000"));
    }
}
