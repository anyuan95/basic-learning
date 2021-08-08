package org.example.basic.oj.leetcode.Q457;

/**
 * 检查环形数组是否循环
 * <p>
 * 要求循环长度不能为1
 *
 * @author anyuan
 * @since 2021-08-07 23:32
 */
class Solution_ON2_O1 {

    /**
     * O(n^2)，不符合要求
     * <p>
     * 暴力解法，遍历数组中每个元素，从当前元素位置i往下走，判断是否有以下几种情况：
     * 1.是否正负变了，变了就false；
     * 2.是否回到了位置i，回到了就判断k是否大于1；
     * 3.是否已经遍历超过数组长度个值了，是就false；
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (check(nums, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(int[] nums, int index) {
        int k = 0, length = nums.length, currentIndex = index;
        boolean positive = nums[index] > 0;
        while (true) {
            int nextIndex = 0;
            if (nums[currentIndex] > 0) {
                nextIndex = (currentIndex + nums[currentIndex]) % length;
            } else {
                nextIndex = (currentIndex + length + nums[currentIndex] % length) % length;
            }
            if ((nums[nextIndex] > 0) != positive) {
                return false;
            }
            k++;
            if (nextIndex == index) {
                return k > 1;
            }
            if (k > length) {
                return false;
            }
            currentIndex = nextIndex;
        }
    }

    public static void main(String[] args) {
        final Solution_ON2_O1 solution = new Solution_ON2_O1();
//        int[] nums = {2,-1,1,2,2};
//        int[] nums = {-1,2};
        int[] nums = {-2, 1, -1, -2, -2};
        System.out.println(solution.circularArrayLoop(nums));
    }
}
