package org.example.basic.oj.leetcode.Q1995;

/**
 * 4 <= nums.length <= 50
 * 1 <= nums[i] <= 100
 *
 * @author anyuan
 * @date 2021-12-29 14:58
 */
class Solution {
    /**
     * n^3的解法，通过移动c的范围，动态记录d可能的数量进行比较
     *
     * @param nums
     * @return
     */
    public int countQuadruplets_n3(int[] nums) {
        // a+b ∈ [2,200]
        // d-c ∈ [-99,99]
        int count = 0;
        int[] dMaybe = new int[101];
        final int n = nums.length;
        // 至少要留出两个位置给c和d
        for (int c = n - 2; c > 1; c--) {
            // 记录d可能的每种值的数量
            dMaybe[nums[c + 1]]++;
            // 然后去遍历a和b
            for (int a = 0; a < c - 1; a++) {
                for (int b = a + 1; b < c; b++) {
                    final int aAndbAndc = nums[a] + nums[b] + nums[c];
                    if (aAndbAndc >= 1 && aAndbAndc <= 100) {
                        count += dMaybe[aAndbAndc];
                    }
                }
            }
        }
        return count;
    }

    /**
     * n^2的解法，
     * 逆序枚举b，b移动的过程中就会出现新的可用的c，
     * 遍历d可能的位置，将d-c的值进行计算。
     * 计算完成后以得到的数组为数据来源，再遍历a，数组中所有a+b的值都是可选的四元组。
     *
     * @param nums
     * @return
     */
    public int countQuadruplets_n2(int[] nums) {
        final int n = nums.length;
        int count = 0;
        // 这种做法需要记录d-c，
        // d-c ∈ [-99,99]
        // a+b ∈ [2,200]
        // 若要二者相等，实际上可选的范围是[2,99]，所以其实需要保存的范围只有2-99
        int[] dMinusC = new int[100];
        for (int b = n - 3; b > 0; b--) {
            // 找出所有可能的d-c
            for (int d = n - 1; d > b + 1; d--) {
                int _dMinusC = nums[d] - nums[b + 1];
                if (_dMinusC >= 2 && _dMinusC <= 99) {
                    dMinusC[_dMinusC]++;
                }
            }
            // 遍历看哪些a+b可以
            for (int a = 0; a < b; a++) {
                final int _aAndB = nums[a] + nums[b];
                if (_aAndB >= 2 && _aAndB <= 100) {
                    count += dMinusC[_aAndB];
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.countQuadruplets_n2(new int[]{1, 1, 1, 3, 5}));
    }
}
