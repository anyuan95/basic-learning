package org.example.basic.oj.leetcode.Q117;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-09-23 17:08
 */
class Solution_bfs_0923 {
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

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        // 先用bfs做基础版
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        // 带层数的bfs
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                // 每次遍历设置当前节点的next
                curr.next = i == size - 1 ? null : queue.peek();

                // 把当前节点的children都加到队列中
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
        }
        return root;
    }
}
