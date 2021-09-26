package org.example.basic.oj.leetcode.Q572;

/**
 * @author anyuan
 * @since 2021-09-23 17:43
 */
class Solution_0923 {
    private static class TreeNode {
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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return equals(root, subRoot) || (root != null && (isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)));
    }

    private boolean equals(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        } else if (tree1 == null || tree2 == null) {
            return false;
        }
        // neither null
        if (tree1.val != tree2.val) {
            return false;
        }
        return equals(tree1.left, tree2.left) && equals(tree1.right, tree2.right);
    }
}
