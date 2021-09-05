package org.example.basic.oj.leetcode.Q152;

/**
 * 求整数数组中连续子数组的最大乘积
 *
 * @author anyuan
 * @since 2021-08-31 13:53
 */
class Solution_0831 {

    /**
     * 重新自己想的方法，虽然代码比较多，但是比较好理解
     *
     * 在每个位置，都得到当前的cMax和cMin，供下个位置计算使用：
     * - 如果下个位置的值i>0，那么最大值就在cMax*i和i之间，最小值就在cMin*i和i之间；
     * - 如果下个位置的值i<0，那么最大值就在cMin*i和i之间，最小值就在cMax*i和i之间；
     * - 如果下个位置的值i==0，那么最大值最小值都重置为0；
     * 以此类推，在每次计算cMax和cMin完成后，比较cMax和max，记录当前整体的最大值
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int currentMax = nums[0], currentMin = nums[0], max = currentMax;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                // 如果是正数，则乘以最大值再和新的最大值比较，得到新的最大值
                currentMax = Math.max(currentMax * nums[i], nums[i]);
                currentMin = Math.min(currentMin * nums[i], nums[i]);
            } else if (nums[i] < 0) {
                int tempMax = currentMax;
                // 如果是负数，则乘以最小值再和最大值比较，得到新的最大值
                currentMax = Math.max(currentMin * nums[i], nums[i]);
                currentMin = Math.min(tempMax * nums[i], nums[i]);
            } else {
                currentMax = 0;
                currentMin = 0;
            }
            max = Math.max(currentMax, max);
        }
        return max;
    }

    public static void main(String[] args) {
        final Solution_0831 solution = new Solution_0831();
        System.out.println(solution.maxProduct(new int[]{-4, -3, -2}));
    }
}
