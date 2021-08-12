package org.example.basic.oj.leetcode.Q98;

/**
 * @author anyuan
 * @since 2021-08-12 10:05
 */
class Solution_Better {
    static class TreeNode {
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
    
    public boolean isValidBST(TreeNode root) {
        return process(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean process(TreeNode current, long min, long max) {
        if (current == null) {
            return true;
        }
        if (current.val <= min || current.val >= max) {
            return false;
        }
        return process(current.left, min, current.val) && process(current.right, current.val, max);
    }

    public static void main(String[] args) {
        final Solution_Better solution = new Solution_Better();
        final TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(solution.isValidBST(root));
    }
}
