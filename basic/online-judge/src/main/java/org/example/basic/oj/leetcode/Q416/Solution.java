package org.example.basic.oj.leetcode.Q416;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 将给定数组分割成两个等和子集
 * <p>
 * 过程：
 * 1.纯暴力递归，TLE；
 * 2.在暴力递归基础上添加缓存（记忆化搜索），AC，双5%；
 * 3.考虑在暴力递归基础上做出dp表，但变量currentSum范围不可控，放弃；
 * 4.按照0-1背包问题变种处理。暴力的0-1背包时间复杂度和1一模一样，TLE；
 * 5.从4中整理规则，对0-1背包做出dp表。由于题目给定的范围:nums.length ∈ [1,200]，nums[i] ∈ [1,100]，rest范围在[1,20000]中，所以可以认为可控...Orz，AC，49%+16%；
 *
 * @author anyuan
 * @since 2021-08-18 09:06
 */
class Solution {
    public boolean canPartition1(int[] nums) {
        // 如果能分解成两个等和子集，则说明存在两个不相交的子集，两个子集各自的和都是sum/2
        final int sum = IntStream.of(nums).sum();
        // 如果一个正整数集合能够分解成两个等和集合，则该集合的所有项的值总和一定是2N（N为正整数），否则一定不能。
        if ((sum & 1) == 1) return false;
        // 调整，将影响状态的变量数量降低到2个或以下
        return process1(nums, sum / 2, 0, 0);
    }

    private Map<String, Boolean> cache = new HashMap<>();

    /**
     * 将问题改为：求nums数组中，是否有和为aimSum的子集
     *
     * @param nums         数组
     * @param aimSum       目标和
     * @param currentIndex 当前下标
     * @param currentSum   当前和
     * @return
     */
    private boolean process1(int[] nums, int aimSum, int currentIndex, int currentSum) {
        if (currentSum == aimSum) {
            return true;
        } else if (currentSum > aimSum) {
            return false;
        }
        if (currentIndex == nums.length) {
            return false;
        }

        String key = currentIndex + "_" + currentSum;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        // 情况1：不选择currentIndex的值
        boolean answer = process1(nums, aimSum, currentIndex + 1, currentSum);
        // 情况2：选择了currentIndex的值
        // 如果当前和加新值超过目标，则不计算
//        if (currentSum + nums[currentIndex] < aimSum) {
        answer = answer || process1(nums, aimSum, currentIndex + 1, currentSum + nums[currentIndex]);
//        }
        cache.put(key, answer);
        return answer;
    }


    /**
     * 问题转换，变成0-1背包问题：
     * 给定容量为sum/2的背包，给定物品体积nums，求是否能刚好装满
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        // 如果能分解成两个等和子集，则说明存在两个不相交的子集，两个子集各自的和都是sum/2
        final int sum = IntStream.of(nums).sum();
        // 如果一个正整数集合能够分解成两个等和集合，则该集合的所有项的值总和一定是2N（N为正整数），否则一定不能。
        if ((sum & 1) == 1) return false;

        return knapsack(nums, 0, sum / 2);
    }

    /**
     * @param w            物品重量数组
     * @param currentIndex 当前索引
     * @param rest         背包剩余空间
     * @return
     */
    private boolean knapsack(int[] w, int currentIndex, int rest) {
        if (currentIndex == w.length) {
            return rest == 0;
        }
        // 不拿当前物品
        boolean answer = knapsack(w, currentIndex + 1, rest);
        // 拿当前物品
        if (w[currentIndex] <= rest) {
            answer = answer || knapsack(w, currentIndex + 1, rest - w[currentIndex]);
        }
        return answer;
    }

    /**
     * 在knapsack基础上直接生成dp表，i=currentIndex∈[0,w.length]，j=rest∈[0,sum/2]
     * 整理规则：
     * 1.i==w.length时，值为rest==0。即:dp[w.length]中，只有dp[w.length][0] = 0；
     * 2.dp[i][j] = dp[i+1][j] || (w[i] <= j && dp[i+1][j-w[i]])
     *
     * @param nums
     * @return
     */
    public boolean canPartition3(int[] nums) {
        // 如果能分解成两个等和子集，则说明存在两个不相交的子集，两个子集各自的和都是sum/2
        final int sum = IntStream.of(nums).sum();
        // 如果一个正整数集合能够分解成两个等和集合，则该集合的所有项的值总和一定是2N（N为正整数），否则一定不能。
        if ((sum & 1) == 1) return false;

        final int indexes = nums.length;
        final int rests = sum / 2;
        boolean[][] dp = new boolean[indexes + 1][rests + 1];
        dp[indexes][0] = true;
        // boolean[]默认值就是false

        for (int index = indexes - 1; index >= 0; index--) {
            for (int rest = 0; rest <= rests; rest++) {
                dp[index][rest] = dp[index + 1][rest] || (nums[index] <= rest && dp[index + 1][rest - nums[index]]);
            }
        }
        return dp[0][rests];
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.canPartition3(new int[]{1, 5, 11, 5}));
        System.out.println(solution.canPartition3(new int[]{1, 2, 3, 5}));
        System.out.println(solution.canPartition3(new int[]{1, 2, 3, 6}));
        System.out.println(solution.canPartition3(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97}));
    }


}
