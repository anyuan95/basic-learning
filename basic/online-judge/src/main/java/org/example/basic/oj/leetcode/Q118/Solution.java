package org.example.basic.oj.leetcode.Q118;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-08-10 14:47
 */
class Solution {
    /**
     * 暴力
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        // 第几行
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>(i + 1);
            // 每一行第一个都为1
            list.add(1);
            if (i != 0) {
                // 当前行第几个位置
                // 只要写一半就行，因为是对称的
                final int middle = (i + 1) / 2;
                int j = 1;
                final List<Integer> tempList = triangle.get(i - 1);
                while (j < middle) {
                    list.add(tempList.get(j - 1) + tempList.get(j));
                    j++;
                }
                if ((i & 1) == 0) {
                    // 如果是奇数行，再加一个值
                    list.add(tempList.get(j - 1) + tempList.get(j));
                }
                while (j > 0) {
                    list.add(list.get(j - 1));
                    j--;
                }
            }
            triangle.add(list);
        }
        return triangle;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.generate(10));
    }
}
