package org.example.basic.oj.leetcode.Q117;

/**
 * @author anyuan
 * @since 2021-09-23 17:20
 */
class Solution_better_0923 {
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
        Node curr = root;
        // 情况拆分：
        // 1.如果当前节点有左有右，那左.next就是右
        // 2.如果当前节点有左无右，那就通过根节点找右子树上的最左节点

        // 3.只要当前节点有右，那就通过根节点找右子树上的最左节点

        if (curr.right != null) {
            curr.right.next = findNext(curr.next);
            if (curr.left != null) {
                curr.left.next = curr.right;
            }
        } else {
            if (curr.left != null) {
                curr.left.next = findNext(curr.next);
            }
        }
        // 相当于从右往左填，因为findNext的时候可能会用到右侧节点的next指针
        // 从右侧开始填
        connect(curr.right);
        connect(curr.left);
        return root;
    }

    private Node findNext(Node node) {
        while (node != null) {
            if (node.left != null) {
                return node.left;
            } else if (node.right != null) {
                return node.right;
            }
            node = node.next;
        }
        return null;
    }
}
