package org.example.basic.oj.leetcode.Q958;

/**
 * @author anyuan
 * @since 2021-08-12 21:32
 */
class Solution_recursive {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Info {
        int depth;
        boolean isCBT;
        boolean isFull;

        public Info(int depth, boolean isCBT, boolean isFull) {
            this.depth = depth;
            this.isCBT = isCBT;
            this.isFull = isFull;
        }
    }

    /**
     * 将问题拆分：
     * 什么样的树是完全二叉树？
     * 1.左右子树都是满二叉树，且高度相等；
     * 2.左子树是完全二叉树，右子树是满二叉树，且左比右高1；
     * 3.左子树是满二叉树，右子树是完全二叉树，且左右高度相等；
     *
     * @param root
     * @return
     */
    private boolean isCompleteTree(TreeNode root) {
        return process(root).isCBT;
    }

    private Info process(TreeNode current) {
        if (current == null) {
            return new Info(0, true, true);
        }
        final Info leftInfo = process(current.left);
        final Info rightInfo = process(current.right);
        // 满二叉树：左右子树必须都是满的，且高度相等
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.depth == rightInfo.depth;
        boolean isCBT = leftInfo.isFull && rightInfo.isFull && leftInfo.depth == rightInfo.depth
                || leftInfo.isFull && rightInfo.isCBT && leftInfo.depth == rightInfo.depth
                || leftInfo.isCBT && rightInfo.isFull && leftInfo.depth - rightInfo.depth == 1;
        int maxDepth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
        return new Info(maxDepth, isCBT, isFull);
    }
}
