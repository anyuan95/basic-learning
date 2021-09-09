package org.example.basic.oj.leetcode.Q90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-09 16:46
 */
class Solution {
    /**
     * 思路：
     * 1.首先从Q78基本实现方式出发，通过不断地从answer中取出list，追加当前元素的方式实现Q78
     * 2.将数组排序；
     * 3.通过整理数据可知，重复的元素都会在特定的部分出现，如下：
     * 假设元素列(nums)为：[1, 2, 2]，下面进行模拟
     * 遍历完第一个元素--->[[], [1]]
     * 遍历完第二个元素--->[[], [1], [2], [1, 2]]
     * 遍历完第三个元素--->[[], [1], [2], [1, 2], **[2]**, **[1, 2]**, [2, 2], [1, 2, 2]]。
     * 对于重复的元素2，在第2次遍历到元素2时，实际上只有由[[], [1]]+第一个2得到的部分，再加上第二个2得到的部分不会重复
     * 所以实际上只需要重新拼装这一部分即可。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(new ArrayList<>());
        final int n = nums.length;
        int lastSize = 0;
        for (int i = 0; i < n; i++) {
            final int size = answer.size();
            int j = 0;
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 出现重复之后，调整从answer中拷贝list开始处理的索引位置
                j = size - lastSize;
            } else {
                // 没出现重复元素的时候，更新出现重复元素之前的size
                lastSize = size;
            }
            while (j < size) {
                List<Integer> temp = new ArrayList<>(answer.get(j));
                temp.add(nums[i]);
                answer.add(temp);
                j++;
            }
        }
        return answer;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(solution.subsetsWithDup(new int[]{4, 4, 4, 1, 4}));
    }
}
