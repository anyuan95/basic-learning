package org.example.basic.oj.leetcode.Q287;

/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * <p>
 * <p>
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *
 * @author anyuan
 * @since 2021-07-30 10:48
 */
class Solution {
    /**
     * 二进制思路
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int[] bitArray = new int[32];
        for (int num : nums) {
            int temp = num;
            for (int i = 0; i < 32; i++) {
                bitArray[i] += temp & 1;
                temp >>>= 1;
            }
        }
        int[] serialBitArray = new int[32];
        for (int i = 1; i < nums.length; i++) {
            int temp = i;
            for (int j = 0; j < 32; j++) {
                serialBitArray[j] += temp & 1;
                temp >>>= 1;
            }
        }
        int repeatedNumber = 0;
        for (int i = 31; i >= 0; i--) {
            repeatedNumber <<= 1;
            if (bitArray[i] > serialBitArray[i]) {
                repeatedNumber |= 1;
            }
        }
        return repeatedNumber;
    }

    /**
     * 思路：
     * 还是二进制位数组。
     * 但是需要证明：
     * （假定重复的数为ans，
     * 假定数组中二进制位第i位为1的共有x个，[1,n]中二进制位第i位为1的共有y个，
     * 则有：当且仅当x>y时，ans的二进制位中这一位为1）
     * 证明过程：
     * - 如果数组中ans出现了两次，则其余的数各出现了一次。
     *  则对于ans中每一个1位置，都有x-y=1；
     *  对于ans中每一个0位置，都有x==y；
     * - 如果数组中ans出现了超过两次，则其余的某些数出现不足一次。
     *  相当于使用ans替换了这些数，则有以下几种情况：
     *  - 如果被替换的数第i位为1，且ans的第i位为1，则
     *
     *
     *
     * TODO 未理解如何证明：有x>y时第i位为1的结论
     * @param nums
     * @return
     */
    public int findDuplicate_better(int[] nums) {
        int n = nums.length, ans = 0;
        int maxDigit = 31;
        while (((n - 1) >> maxDigit) == 0) {
            maxDigit--;
        }
        for (int digitIndex = 0; digitIndex <= maxDigit; digitIndex++) {
            int arrOneCount = 0, seqOneCount = 0;
            for (int arrIndex = 0; arrIndex < n; arrIndex++) {
                if (((nums[arrIndex] >> digitIndex) & 1) == 1) {
                    arrOneCount++;
                }
                if (((arrIndex >> digitIndex) & 1) == 1) {
                    seqOneCount++;
                }
            }
            if (arrOneCount > seqOneCount) {
                ans |= (1 << digitIndex);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 2};
        System.out.println(new Solution().findDuplicate(nums));
        System.out.println(new Solution().findDuplicate_better(nums));
    }
}
