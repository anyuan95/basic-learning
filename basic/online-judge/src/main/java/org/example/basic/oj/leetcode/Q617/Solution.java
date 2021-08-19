package org.example.basic.oj.leetcode.Q617;

/**
 * 合并二叉树（的值）
 *
 * @author anyuan
 * @since 2021-08-19 16:32
 */
class Solution {
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

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // 1为空return了，2为空return了，那就剩1和2都不为空了
        TreeNode current = new TreeNode(root1.val + root2.val);
        current.left = mergeTrees(root1.left, root2.left);
        current.right = mergeTrees(root1.right, root2.right);
        return current;
    }
}
