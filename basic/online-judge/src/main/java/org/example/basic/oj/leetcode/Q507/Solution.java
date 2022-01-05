package org.example.basic.oj.leetcode.Q507;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author anyuan
 * @date 2021-12-31 16:24
 */
class Solution {
    /**
     * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
     * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
     * <p>
     * 开始施法。
     * <p>
     * 如果n是偶数，那么有可能会有两种拆分方式：奇数*偶数、偶数*偶数
     * 反过来考虑，如果偶数n是完美数，那么n除了自身以外的所有因数的和还是n
     * 再把1去掉，就相当于除[1,n]这一对以外的所有因数的和为n-1
     * 又因为n-1一定是奇数，所以n除了[1,n]以外的因数中一定有奇数个奇数，所以偶数是可能成为完美数的
     * <p>
     * 如果n是奇数，那么它的所有因数就一定都是奇数。现在假定n有k个因数。
     * 如果要奇数n是完美数，那么就一定有1+(奇数)*(k-2)=n。
     * <p>
     * --------打断施法
     * 可能没有明显的特征，那么考虑记忆化搜索的方式。
     * 对于每个n，记录下它的最大因数和它的因数和。
     * 应该也不太行，因为这样会导致计算量增大。
     * <p>
     * 如果用归并的方式呢？也不行，因为会有重复值
     *
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        final int sqrt = (int) Math.sqrt(num);
        int factorSum = 1;
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                factorSum += i;
                factorSum += num / i;
            }
        }
        if (sqrt * sqrt == num) {
            factorSum -= sqrt;
        }
        return factorSum == num;
    }

    public static void main(String[] args) {
        // 12 = 1,2,3,4,6,
        // 6 = 1,2,3,
        // 28 = 1,2,4,7,14
        final Solution solution = new Solution();
        System.out.println(solution.checkPerfectNumber(28));
        System.out.println(solution.checkPerfectNumber(6));
        final List<Integer> pn = IntStream.range(1, Integer.MAX_VALUE)
                .filter(solution::checkPerfectNumber)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(pn);
    }
}
