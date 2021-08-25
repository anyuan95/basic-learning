package org.example.basic.oj.leetcode.Q797;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-08-25 11:04
 */
public class Solution_bfs {

    /**
     * 思路：bfs
     * <p>
     * 但是bfs其实不太好，因为要记录路径上的所有节点
     *
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> answer = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0));
        while (!queue.isEmpty()) {
            final Node currentNode = queue.poll();
            if (currentNode.index == graph.length - 1) {
                answer.add(currentNode.path);
                continue;
            }
            final int[] nexts = graph[currentNode.index];
            for (int next : nexts) {
                queue.offer(new Node(next, currentNode.path));
            }
        }
        return answer;
    }

    /**
     * 做一个node对象，记录路径上走过的所有节点
     * 加一个构造器，将上一个节点经过的所有节点都复制进来，然后再加上当前节点，组成[从0节点到当前节点的路径]
     */
    static class Node {
        int index;
        List<Integer> path;

        public Node(int index) {
            this.index = index;
            this.path = new ArrayList<>();
            this.path.add(index);
        }

        public Node(int index, List<Integer> path) {
            this.index = index;
            this.path = new ArrayList<>(path);
            this.path.add(index);
        }
    }
}
