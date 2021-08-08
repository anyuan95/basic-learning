package org.example.basic.oj.leetcode.Q457;

/**
 * 检查环形数组是否循环
 * <p>
 * 要求循环长度不能为1
 *
 * @author anyuan
 * @since 2021-08-07 23:32
 */
class Solution_ON_ON {

    /**
     * O(n)和O(n)，不符合要求
     * <p>
     * 暴力解法基础上添加了记录数组，避免了重复的检查，遍历数组中每个元素，从当前元素位置i往下走，判断是否有以下几种情况：
     * 1.是否正负变了，变了就false；
     * 2.是否回到了位置i，回到了就判断k是否大于1；
     * 3.是否已经遍历超过数组长度个值了，是就false；
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        // 记录数组，用于记录nums中每个位置是否已经被遍历过了
        // value记载当前位置是在遍历哪个位置的时候被遍历的
        int[] records = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (check(nums, records, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(int[] nums, int[] records, int index) {
        int length = nums.length, currentIndex = index, round = index + 1;
        if (records[index] != 0) {
            // 如果记录过，则说明该节点开始一定不满足要求（如果满足要求早就return true了）
            return false;
        }
        boolean positive = nums[index] > 0;
        while (true) {
            int nextIndex = (currentIndex + length + nums[currentIndex] % length) % length;
            if ((nums[nextIndex] > 0) != positive) {
                return false;
            }
            if (nextIndex == currentIndex) {
                // 自身循环 其实就是K>1的判断
                return false;
            }
            if (nextIndex == index) {
                // 如果回到起点了，说明是环了
                return true;
            }
            if (records[nextIndex] != 0) {
                // !=0说明遍历过了

                // ==说明是当前这次遍历的
                // !=说明之前遍历的
                return records[nextIndex] == round;
            } else {
                // ==0说明没遍历过，记成当前index遍历的
                records[nextIndex] = round;
            }
            currentIndex = nextIndex;
        }
    }

    public static void main(String[] args) {
        final Solution_ON_ON solution = new Solution_ON_ON();
        int[] nums = {-1, 2, 1, 2};
//        int[] nums = {1, 1, 2};
//        int[] nums = {2,-1,1,2,2};
//        int[] nums = {-1,2};
//        int[] nums = {-2, 1, -1, -2, -2};
//        int[] nums = {-1, -2, -3, -4, -5};
//        int[] nums = {2,2,2,2,2,4,7};
        System.out.println(solution.circularArrayLoop(nums));
    }
}
