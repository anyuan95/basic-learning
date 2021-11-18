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
        } else if (node.left == null && node.right == null) {
            return node.val;
        } else if (node.left == null) {
            final int rSum = sum(node.right);
            tilt += rSum;
            return rSum + node.val;
        } else if (node.right == null) {
            final int lSum = sum(node.left);
            tilt += lSum;
            return lSum + node.val;
        } else {
            final int lSum = sum(node.left);
            final int rSum = sum(node.right);
            tilt += Math.abs(lSum - rSum);
            return lSum + rSum + node.val;
        }
    }
}
