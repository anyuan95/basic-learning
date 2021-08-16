package org.example.basic.oj.leetcode.Q210;

import java.util.*;

/**
 * @author anyuan
 * @since 2021-08-16 12:20
 */
class Solution {

    /**
     * 注意：
     * 1.题目没有说明给定的数据不会产生环，所以需要判断是否有环
     * 2.可能课程的限制规则不会覆盖所有课程
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] answer = new int[numCourses];
//        if (prerequisites == null || prerequisites.length == 0) {
//            for (int i = 0; i < numCourses; i++) {
//                answer[i] = i;
//            }
//            return answer;
//        }
        // 拓扑排序
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> realTimeInCountMap = new HashMap<>();

        final Graph graph = generateGraph(numCourses, prerequisites);
        for (Node node : graph.nodes.values()) {
            realTimeInCountMap.put(node, node.inCount);
            if (node.inCount == 0) {
                queue.add(node);
            }
        }
        int answerIndex = 0;
        while (!queue.isEmpty()) {
            final Node current = queue.poll();
            answer[answerIndex++] = current.value;
            for (Node next : current.nexts) {
                realTimeInCountMap.computeIfPresent(next, (node, integer) -> integer - 1);
                if (realTimeInCountMap.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        // 如果最终找到为0的节点数不等于节点总数，则说明图中有环存在，返回null
        return answerIndex == numCourses ? answer : new int[0];
    }

    /**
     * 由于题目中给定的prerequisites并不一定覆盖所有课程，所以先将所有课程转换成节点
     * [i,j]：要完成i，需要先完成j，所以是[to,from]
     *
     * @param prerequisites
     * @return
     */
    private Graph generateGraph(int nodeCount, int[][] prerequisites) {
        final Graph graph = new Graph();
        for (int i = 0; i < nodeCount; i++) {
            graph.nodes.put(i, new Node(i));
        }
        for (int[] prerequisite : prerequisites) {
            final int from = prerequisite[1];
            final int to = prerequisite[0];
            final Node fromNode = graph.nodes.computeIfAbsent(from, i -> new Node(from));
            final Node toNode = graph.nodes.computeIfAbsent(to, i -> new Node(to));
            fromNode.nexts.add(toNode);
            toNode.inCount++;
        }
        return graph;
    }

    static class Graph {
        HashMap<Integer, Node> nodes;

        public Graph() {
            this.nodes = new HashMap<>();
        }
    }

    static class Node {
        int value;
        int inCount;
        List<Node> nexts;

        public Node(int value) {
            this.value = value;
            this.inCount = 0;
            this.nexts = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        final int[][] prerequisites = {
//                {1, 0},
//                {2, 0},
//                {3, 1},
//                {3, 2},
//                {2, 3}
//        };
        final int[][] prerequisites = {
                {1, 0},
                {2, 0}
        };
        System.out.println(Arrays.toString(solution.findOrder(4, prerequisites)));
    }
}
