package org.example.basic.oj.leetcode.Q2049;

import java.util.*;

class Solution {
    /**
     * 先从最暴力的开始思考
     * 求出每个节点的左右子树数量
     * <p>
     * 拆掉每个节点后，最多将树分成三份
     *
     * @param parents
     * @return
     */
    public int countHighestScoreNodes(int[] parents) {
        final int n = parents.length;
        // 先处理原始数据结构，转成map<list>
        final Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(parents[i], new ArrayList<>());
            map.get(parents[i]).add(i);
        }

        int[] left = new int[n];
        int[] right = new int[n];
        dfs(map, 0, left, right);

        long highestScore = 1;
        Map<Long, Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 考虑去掉点i后会有几种情况：变成2份或者3份
            int leftSide = left[i];
            int rightSide = right[i];
            int parentSide = n - 1 - leftSide - rightSide;
            long temp = 1L;
            if (leftSide != 0) {
                temp *= leftSide;
            }
            if (rightSide != 0) {
                temp *= rightSide;
            }
            if (parentSide != 0) {
                temp *= parentSide;
            }
            countMap.put(temp, countMap.getOrDefault(temp, 0) + 1);
            highestScore = Math.max(highestScore, temp);
        }
        return countMap.get(highestScore);
    }

    private int dfs(Map<Integer, List<Integer>> map, int index, int[] left, int[] right) {
        int answer = 1;
        if (!map.containsKey(index)) {
            return answer;
        }
        final List<Integer> children = map.get(index);
        left[index] = dfs(map, children.get(0), left, right);
        if (children.size() == 2) {
            right[index] = dfs(map, children.get(1), left, right);
        }
        answer += left[index] + right[index];
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.countHighestScoreNodes(new int[]{-1, 2, 0, 2, 0}));
        System.out.println(solution.countHighestScoreNodes(new int[]{-1, 2, 0}));
        System.out.println(solution.countHighestScoreNodes(new int[]{-1, 3, 3, 5, 7, 6, 0, 0}));
        System.out.println(solution.countHighestScoreNodes(new int[]{-1, 0, 3, 0, 3, 1}));
    }

}
