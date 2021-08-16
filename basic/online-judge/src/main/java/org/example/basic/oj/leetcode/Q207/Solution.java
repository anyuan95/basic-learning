package org.example.basic.oj.leetcode.Q207;

import java.util.*;

/**
 * 课程表
 * 实际上就是判断图中：是否有环
 *
 * @author anyuan
 * @since 2021-08-16 14:33
 */
class Solution {

    /**
     * 思路：拓扑排序
     * <p>
     * 广度优先遍历
     * <p>
     * 自定义图对象
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int canFinishNodeCount = 0;
        List<Node> nodes = generateNodes(numCourses, prerequisites);
        HashMap<Node, Integer> indegreeMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        for (Node node : nodes) {
            indegreeMap.put(node, node.indegree);
            if (node.indegree == 0) {
                canFinishNodeCount++;
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            final Node current = queue.remove();
            for (Node next : current.nexts) {
                indegreeMap.computeIfPresent(next, (node, integer) -> integer - 1);
                if (indegreeMap.get(next) == 0) {
                    canFinishNodeCount++;
                    queue.add(next);
                }
            }
        }
        return canFinishNodeCount == numCourses;
    }

    private List<Node> generateNodes(int nodeCount, int[][] prerequisites) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        for (int i = 0; i < nodeCount; i++) {
            nodes.put(i, new Node(i));
        }
        for (int[] prerequisite : prerequisites) {
            final int from = prerequisite[1];
            final int to = prerequisite[0];
            final Node fromNode = nodes.computeIfAbsent(from, i -> new Node(from));
            final Node toNode = nodes.computeIfAbsent(to, i -> new Node(to));
            fromNode.nexts.add(toNode);
            toNode.indegree++;

        }
        return new ArrayList<>(nodes.values());
    }

    static class Node {
        int value;
        int indegree;
        List<Node> nexts;

        public Node(int value) {
            this.value = value;
            this.indegree = 0;
            this.nexts = new ArrayList<>();
        }
    }
}
