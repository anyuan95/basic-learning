package org.example.basic.oj.leetcode.Q117;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-09-06 15:50
 */
class Solution_bfs {
    // Definition for a Node.
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

    /**
     * 思路：实际上就是从根节点开始进行bfs，从queue中取出的每个节点都是上一个节点的next，队列中最后一个节点的next是null
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node currentDepthLastNode = null;
            final int currentDepthElementCount = queue.size();
            for (int i = 0; i < currentDepthElementCount; i++) {
                final Node current = queue.poll();
                if (currentDepthLastNode != null) {
                    currentDepthLastNode.next = current;
                }
                currentDepthLastNode = current;

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            // 处理完当前行的元素后，将最后一个元素的next置为null
            currentDepthLastNode.next = null;
        }
        return root;
    }

    public static void main(String[] args) {
        final Solution_bfs solution_bfs = new Solution_bfs();
        System.out.println(solution_bfs.connect(null));
    }
}
