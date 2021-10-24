package org.example.basic.oj.leetcode.Q442;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @date 2021-10-18 15:34
 */
class Solution_bucket {
    /**
     * 思路：使用交换的桶排序方式
     * <p>
     * 遍历过程中将数值移动到他应该在的位置。如果二者相等，即对应索引位置已经是该位置代表的值了，那就不交换了。
     * 最后遍历一次整个数组，所有索引位置代表的值不是期望值的结果就都是出现大于一次的。
     * 不过和题目其实不完全一样，因为题目要求的是出现2次，而不是超过2次。
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        final int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] - 1 >= 0 && nums[i] - 1 < n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                answer.add(nums[i]);
            }
        }
        return answer;
    }

    private void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        final Solution_bucket solution = new Solution_bucket();
        System.out.println(solution.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
