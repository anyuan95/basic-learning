package org.example.basic.oj.leetcode.Q797;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出图中从A到B所有可能的路径
 *
 * @author anyuan
 * @since 2021-08-25 10:30
 */
class Solution_dfs {

    /**
     * 要求从0节点走到n-1节点
     * <p>
     * 感觉应该是倒着的广度优先遍历？
     * 从目标节点开始倒着找，找到0为止
     * 先试一下正着的
     *
     * @param graph graph[i]表示从i节点出发能直接到达的所有节点
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> answer = new ArrayList<>();
        final List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(answer, graph, path, 0, graph.length - 1);
        return answer;
    }

    /**
     * dfs+回溯（恢复现场）
     *
     * @param answer
     * @param graph
     * @param path
     * @param currentNode
     * @param targetNode
     */
    private void dfs(List<List<Integer>> answer, int[][] graph, List<Integer> path, int currentNode, int targetNode) {
        if (currentNode == targetNode) {
            answer.add(new ArrayList<>(path));
            return;
        }
        for (int nextNode : graph[currentNode]) {
            path.add(nextNode);
            dfs(answer, graph, path, nextNode, targetNode);
            path.remove((Object) nextNode);
        }
    }

    public static void main(String[] args) {

    }
}
