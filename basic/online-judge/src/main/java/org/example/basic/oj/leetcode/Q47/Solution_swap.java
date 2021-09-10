package org.example.basic.oj.leetcode.Q47;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-09 22:30
 */
class Solution_swap {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 使用这种实现方式甚至不需要排序
//        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, nums, 0);
        return answer;
    }

    private void process(List<List<Integer>> answer, int[] nums, int currentIndex) {
        if (currentIndex == nums.length) {
            final List<Integer> temp = new ArrayList<>();
            for (int num : nums) {
                temp.add(num);
            }
            answer.add(temp);
            return;
        }
        for (int i = currentIndex; i < nums.length; i++) {
            // 这里不能直接判断
//            if (currentIndex != i && nums[currentIndex] == nums[i]) {
//                continue;
//            }
            // 如果cur~i中有与i重复的值，则不交换
            if (isRepeat(nums, currentIndex, i)) {
                continue;
            }
            swap(nums, currentIndex, i);
            process(answer, nums, currentIndex + 1);
            swap(nums, currentIndex, i);
        }
    }

    /**
     * 判断要交换的两个数（curr，i）之间，是否有与i相等的元素。
     * 如果有与i相等的元素，则说明curr再与i交换后得到的序列都会是重复的。
     *
     * 更简单的理解方式：如果cur与i交换，则说明原来cur位置的值会变成i的值；
     * 如果从cur到i之间有与i位置相同的值，则如果这次还进行交换的话，这个值又会出现在cur位置。
     * 而cur位置为Vi的所有情况已经都遍历过了。
     *
     * @param nums
     * @param startIndex
     * @param endIndex
     * @return
     */
    private boolean isRepeat(int[] nums, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (nums[i] == nums[endIndex]) {
                return true;
            }
        }
        return false;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) return;
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

    public static void main(String[] args) {
        final Solution_swap solution = new Solution_swap();
        System.out.println(solution.permuteUnique(new int[]{1, 2, 3, 4}));
        System.out.println(solution.permuteUnique(new int[]{1, 2, 3, 3}));
    }
}
