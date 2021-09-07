package org.example.basic.oj.leetcode.Q117;

/**
 * @author anyuan
 * @since 2021-09-06 16:02
 */
class Solution_no_extra_space {
    // Definition for a Node.
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
        // 如果一个节点同时有左和右，那么左的next就是右
        // 如果一个节点只有左，那么左的next就是当前节点的所有兄弟节点中的最左子节点
        // 如果一个节点只有右，同上一种情况
        if (root.left != null) {
            if (root.right !=null) {
                root.left.next = root.right;
            } else {
                // 注意这里要从root.next开始找
                root.left.next = getBrosLeftestSon(root.next);
            }
        }
        if (root.right != null) {
            // 注意这里要从root.next开始找
            root.right.next = getBrosLeftestSon(root.next);
        }
        // 必须先填右树，因为左树中的元素找next时可能会用到右树的元素
        connect(root.right);
        connect(root.left);
        return root;
    }

    /**
     * 通过current，找到current及其兄弟节点中的最左子节点
     *
     * @param current
     * @return
     */
    private Node getBrosLeftestSon(Node current) {
        while (current !=null) {
            if (current.left != null) {
                return current.left;
            }
            if (current.right!=null) {
                return current.right;
            }
            current = current.next;
        }
        return null;
    }

}
