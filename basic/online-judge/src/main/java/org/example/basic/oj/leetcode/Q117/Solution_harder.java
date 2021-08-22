package org.example.basic.oj.leetcode.Q117;

/**
 * 与Q116类似，区别在于不再是完全二叉树。
 * <p>
 * 添加了限制：你只能使用常量级额外空间。
 *
 * @author anyuan
 * @since 2021-08-22 18:57
 */
class Solution_harder {
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
            return root;
        }
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                root.left.next = getNext(root.next);
            }
        }
        if (root.right != null) {
            root.right.next = getNext(root.next);
        }
        /**
         * 这里必须从右往左填充，因为左侧的子树填充时可能会用到右侧子树的信息
         */
        connect(root.right);
        connect(root.left);
        return root;
    }

    /**
     * 实际上就是找到当前节点及当前节点右侧的所有节点中，最左的子树节点（也有可能左树为空，那最左的子树节点就是右树节点了）
     *
     * @param current x.root.next
     * @return
     */
    private Node getNext(Node current) {
        while (current != null) {
            if (current.left != null) {
                return current.left;
            } else if (current.right != null) {
                return current.right;
            }
            current = current.next;
        }
        return null;
    }
}
