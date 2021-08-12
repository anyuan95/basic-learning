package org.example.basic.oj.leetcode.O55_I;

/**
 * 求二叉树的最大深度
 *
 * @author anyuan
 * @since 2021-08-12 11:12
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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


}
