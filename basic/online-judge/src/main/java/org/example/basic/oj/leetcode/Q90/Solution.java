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
     * 下标确定：
     * 假如nums中前面元素生成的子集为A，当前遍历到一个新值m，则本次应该新生成的子集为[A+m]，总的子集集合为[A,A+m]；
     * 假设现在又遇到一个m值，那么实际上现在只有A+m的部分再加上当前的m，得到的结果才会是不重复的部分。
     * 所以可以整理得到，
     * - 当本次遍历到的元素和上一个位置的元素相同时，本次复制的开始位置应该是上一次复制时的开始位置。
     * - 当本次遍历到的元素和上一个位置的元素不同时，本次复制的开始位置就是0。
     *
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(new ArrayList<>());
        final int n = nums.length;
        // 记录上一个元素新增到answer中的子集数量
        int lastLen = 0;
        // 当前元素从answer中复制的开始位置
        int start = 0;
        for (int i = 0; i < n; i++) {
            final int size = answer.size();
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 出现重复之后，调整从answer中拷贝list开始处理的索引位置
                start = size - lastLen;
            } else {
                start = 0;
            }
            for (int j = start; j < size; j++) {
                List<Integer> temp = new ArrayList<>(answer.get(j));
                temp.add(nums[i]);
                answer.add(temp);
            }
            // 记录当前元素新生成的answer数量
            lastLen = answer.size() - size;
        }
        return answer;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(solution.subsetsWithDup(new int[]{1, 1, 2, 2}));
        System.out.println(solution.subsetsWithDup(new int[]{4, 4, 4, 1, 4}));
    }
}
