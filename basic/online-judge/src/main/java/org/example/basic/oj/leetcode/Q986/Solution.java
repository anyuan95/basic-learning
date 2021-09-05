package org.example.basic.oj.leetcode.Q986;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-02 17:50
 */
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0 || secondList.length == 0) {
            return new int[][]{};
        }

        List<int[]> answer = new ArrayList<>();
        int currentIndexA = 0, currentIndexB = 0, lengthA = firstList.length, lengthB = secondList.length;
        // 看当前A和当前B是否有交集，有交集则把交集加到结果中，然后将结束值=交集结束值的指针向后移动
        while (currentIndexA < lengthA && currentIndexB < lengthB) {
            // 如果两个集合有交集
            final int intersectionStart = Math.max(firstList[currentIndexA][0], secondList[currentIndexB][0]);
            final int intersectionEnd = Math.min(firstList[currentIndexA][1], secondList[currentIndexB][1]);
            if (intersectionStart <= intersectionEnd) {
                answer.add(new int[]{
                        Math.max(firstList[currentIndexA][0], secondList[currentIndexB][0]),
                        Math.min(firstList[currentIndexA][1], secondList[currentIndexB][1])
                });
            }
            // 两者之间结束的较早的数组指针向后移
            if (intersectionEnd == firstList[currentIndexA][1]) {
                currentIndexA++;
            }
            // 优化：原本此处是使用的else，即一次只会有A或B中的一个向右移动指针。
            // 考虑到这种情况：二者的右边界相同时。此时两个指针都应该向右移动。
            if (intersectionEnd == secondList[currentIndexB][1]) {
                currentIndexB++;
            }
        }
        return answer.toArray(new int[][]{});
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.intervalIntersection(
                new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}, {30, 40}, {45, 55}},
                new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}, {29, 56}})
        ));
    }

}
