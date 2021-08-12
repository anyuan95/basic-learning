package org.example.basic.oj.leetcode.O55_II;

/**
 * 判断BalancedTree
 *
 * @author anyuan
 * @since 2021-08-12 11:15
 */
class Solution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class NodeInfo {
        int maxDepth;
        boolean isBalanced;

        public NodeInfo(int maxDepth, boolean isBalanced) {
            this.maxDepth = maxDepth;
            this.isBalanced = isBalanced;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    private NodeInfo process(TreeNode root) {
        if (root == null) {
            return new NodeInfo(0, true);
        }
        final NodeInfo leftInfo = process(root.left);
        final NodeInfo rightInfo = process(root.right);

        return new NodeInfo(Math.max(leftInfo.maxDepth, rightInfo.maxDepth) + 1,
                leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.maxDepth - rightInfo.maxDepth) <= 1);
    }

}
