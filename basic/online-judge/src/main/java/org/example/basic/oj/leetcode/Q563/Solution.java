package org.example.basic.oj.leetcode.Q563;

/**
 * @author anyuan
 * @date 2021-11-18 22:01
 */
class Solution {

    public static class TreeNode {
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

    int tilt = 0;

    public int findTilt(TreeNode root) {
        sum(root);
        return tilt;
    }

    /**
     * 求当前节点所有子节点值的和
     *
     * @param node
     * @return
     */
    private int sum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        final int left = sum(node.left);
        final int right = sum(node.right);
        tilt += Math.abs(left - right);
        return node.val + left + right;
    }
}
