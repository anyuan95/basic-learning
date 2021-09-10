package org.example.basic.oj.leetcode.Q46_need_better;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 全排列
 *
 * FIXME 7 ms,击败了7.26% 的Java用户 ？ ？？ ？？？
 *
 * @author anyuan
 * @since 2021-08-13 17:13
 */
class Solution_swap {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, nums, 0);
        return answer;
    }

    private void process(List<List<Integer>> answer, int[] nums, int currentIndex) {
        // 这里本来currentIndex == nums.length 也可以的，但是减去1能少走一次递归。因为只剩一个值没处理了，处不处理都一样了。
        if (currentIndex == nums.length - 1) {
            answer.add(IntStream.of(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = currentIndex; i < nums.length; i++) {
            swap(nums, currentIndex, i);
            // 第一次写的时候这里搞错了，以为要遍历i。实际上需要遍历的是current+1，因为现在是已经确定好current位置的值了，该填current+1位置的值了。
            process(answer, nums, currentIndex + 1);
            swap(nums, currentIndex, i);
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) return;
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

    public static void main(String[] args) {
        final Solution_swap solution = new Solution_swap();
        System.out.println(solution.permute(new int[]{1, 2, 3, 4}));
    }
}
