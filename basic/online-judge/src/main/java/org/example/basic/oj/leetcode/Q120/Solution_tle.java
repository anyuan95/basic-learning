package org.example.basic.oj.leetcode.Q120;

import java.util.Arrays;
import java.util.List;

/**
 * 给定三角形的二维数组，从顶点开始，向下直到最后一层，找到最小权值的路径，要求只能向下找[同列或下一列]的节点
 *
 * @author anyuan
 * @since 2021-08-25 09:04
 */
class Solution_tle {

    /**
     * 思路：
     * 最简单最暴力的方式就是再做一个和数组，每个节点都存当前节点的最小权重和
     * <p>
     * 题目要求空间复杂度O(n)，n=三角形的高度=三角形底边的长度
     * <p>
     * 实际上节点[i,j]的最小路径，就是Math.min([i-1,j-1], [i-1]) + value[i,j]
     * 偷懒的做法就是直接递归，不记忆化搜索就不会有O(n)空间复杂度了...
     * =.= 果然TLE
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, process(triangle, n - 1, i));
        }
        return answer;
    }

    private int process(List<List<Integer>> triangle, int i, int j) {
        // 比如，[3,3]节点向上找，找的是[2,2]和[2,3]的最大值，但是并没有[2,3]这个节点，这个节点越界了
        if (i < 0 || j < 0 || j == i + 1) {
            return Integer.MAX_VALUE;
        }
        if (i == 0) {
            return triangle.get(0).get(0);
        }
        // 当前节点的最小值取决于上一层同列的节点和上一列节点之间的最小值
        int lastRowLastColumn = process(triangle, i - 1, j - 1);
        int lastRowSameColumn = process(triangle, i - 1, j);
        return Math.min(lastRowLastColumn, lastRowSameColumn) + triangle.get(i).get(j);
    }


    public static void main(String[] args) {
        final Solution_tle solution = new Solution_tle();
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(2), Arrays.asList(3, 4), Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3)
        );
        System.out.println(solution.minimumTotal(triangle));
    }
}
