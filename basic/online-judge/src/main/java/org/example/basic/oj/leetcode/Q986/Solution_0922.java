package org.example.basic.oj.leetcode.Q986;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-22 17:20
 */
class Solution_0922 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0 || secondList.length == 0) {
            return new int[][]{};
        }

        List<int[]> answerList = new ArrayList<>();

        int firstIndex = 0, secondIndex = 0;
        while (firstIndex < firstList.length && secondIndex < secondList.length) {
            final int intersectionStart = Math.max(firstList[firstIndex][0], secondList[secondIndex][0]);
            final int intersectionEnd = Math.min(firstList[firstIndex][1], secondList[secondIndex][1]);

            // 两个集合有交集的充分必要条件：一个的结束大于另一个的开始
            if (intersectionStart <= intersectionEnd) {
                answerList.add(new int[]{intersectionStart, intersectionEnd});
            }
            // 开始决定谁往后移
            // 谁结束的早谁往后移
            if (intersectionEnd == firstList[firstIndex][1]) {
                // f结束得早，f就往后移
                firstIndex++;
            }
            // 如果二者结束位置一样，则都往后移
            if (intersectionEnd == secondList[secondIndex][1]) {
                secondIndex++;
            }
        }
        return answerList.toArray(new int[][]{});
    }
}
