package org.example.basic.oj.leetcode.Q117;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-08-22 18:55
 */
class Solution {
    class Node {
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
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // 当前行上一个节点
        Node currentLineLastNode = null;
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final Node temp = queue.poll();
                if (currentLineLastNode != null) {
                    currentLineLastNode.next = temp;
                }
                // 当前行最后一个，指向null
                if (i == size - 1) {
                    temp.next = null;
                    currentLineLastNode = null;
                } else {
                    currentLineLastNode = temp;
                }

                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return root;
    }

}
