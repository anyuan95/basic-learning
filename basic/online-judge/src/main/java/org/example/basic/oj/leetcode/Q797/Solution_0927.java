package org.example.basic.oj.leetcode.Q797;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-27 17:09
 */
class Solution_0927 {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> answer = new ArrayList<>();
        final ArrayList<Integer> currentAnswer = new ArrayList<>();
        currentAnswer.add(0);
        dfs(graph, 0, answer, currentAnswer);
        return answer;
    }

    private void dfs(int[][] graph, int currentNode, List<List<Integer>> answer, List<Integer> currentAnswer) {
        if (currentNode == graph.length - 1) {
            answer.add(new ArrayList<>(currentAnswer));
            return;
        }
        for (int targetNode : graph[currentNode]) {
            currentAnswer.add(targetNode);
            dfs(graph, targetNode, answer, currentAnswer);
            currentAnswer.remove(currentAnswer.size() - 1);
        }
    }
}
