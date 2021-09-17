package org.example.basic.oj.leetcode.Q153;

/**
 * @author anyuan
 * @since 2021-09-02 15:54
 */
class Solution_better {
    public int findMin(int[] nums) {
        int n = nums.length, left = 0, right = n - 1, middle;
        while (left < right) {
            middle = left + ((right - left) >> 1);
            // 枚举所有可能出现的情况：
            // 右>中>左 -> 最小值是左，收缩右边界；收缩过程中中值有可能是最小值；
            // 左>右>中 -> 最小值在(左，中]，收缩右边界；收缩过程中中值有可能是最小值；
            // 中>左>右 -> 最小值在(中，右]，收缩左边界；收缩过程中中值一定不是最小值；
            // 由上述枚举可得，只要出现 右>中 的情况，就可以收缩右边界

            // 只要比较middle和right
            if (nums[middle] < nums[right]) {
                // 如果middle < right，则说明最小值在[left, middle]之间
                right = middle;
            } else if (nums[middle] > nums[right]) {
                // 如果middle > right，则说明最小值在[middle+1, right]之间
                left = middle + 1;
            }
        }
        // 直到left和right指针相碰，此时二者指向的就是最小值
        return nums[left];
    }

    public static void main(String[] args) {
        final Solution_better solution = new Solution_better();
        System.out.println(solution.findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(solution.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(solution.findMin(new int[]{11, 13, 15, 17}));
        System.out.println(solution.findMin(new int[]{2, 1}));
    }
}
