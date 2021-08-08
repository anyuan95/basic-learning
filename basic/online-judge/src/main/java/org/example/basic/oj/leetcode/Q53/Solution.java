package org.example.basic.oj.leetcode.Q53;

/**
 * @author anyuan
 * @since 2021-08-08 13:14
 */
class Solution {
    public int maxSubArray(int[] nums) {
        final int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int maxSubSum = nums[0], minPreSum = Math.min(0, nums[0]), minPreSumIndex = 0;
        int[] preSum = new int[length];
        preSum[0] = nums[0];
        for (int i = 1; i < length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
            // 用新得到的最大值减去前面（不包含当前）的最小值
            maxSubSum = Math.max(preSum[i] - minPreSum, maxSubSum);
            minPreSum = Math.min(preSum[i], minPreSum);
        }
        return maxSubSum;
    }

    /**
     * 分治，
     * 将数组从中间分开。则最大子线段和一定在以下三种情况中：
     * 1.在左侧
     * 2.在右侧
     * 3.跨越了中间点
     *
     *
     * @param nums
     * @return
     */
    public int maxSubArray_divide(int[] nums) {
        return getStatus(nums, 0, nums.length - 1).mSum;
    }

    private Status getStatus(int[] nums, int left, int right) {
        if (left == right) {
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }
        int middle = left + ((right - left) >> 1);
        Status leftStatus = getStatus(nums, left, middle);
        Status rightStatus = getStatus(nums, middle + 1, right);
        return combine(leftStatus, rightStatus);
    }

    private Status combine(Status leftStatus, Status rightStatus) {
        int iSum = leftStatus.iSum + rightStatus.iSum;
        int lSum = Math.max(leftStatus.lSum, leftStatus.iSum + rightStatus.lSum);
        int rSum = Math.max(rightStatus.rSum, leftStatus.rSum + rightStatus.iSum);
        int mSum = Math.max(Math.max(leftStatus.mSum, rightStatus.mSum), leftStatus.rSum + rightStatus.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    /**
     * 提供一个status记录当前区间的状态
     */
    static class Status {
        // 从l为左端点的最大值
        int lSum;
        // 以r为右端点的最大值
        int rSum;
        // 当前区间内的最大值
        int mSum;
        // 当前区间的区间和
        int iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
//        int[] nums = {5, 4, -1, 7, 8};
//        int[] nums = {-2, -1};
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution.maxSubArray_divide(nums));
    }
}
