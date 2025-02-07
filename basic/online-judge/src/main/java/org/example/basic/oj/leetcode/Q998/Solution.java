package org.example.basic.oj.leetcode.Q998;

/**
 * @author anyuan
 * @date 2022-08-30 22:34
 */
class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        // 题目分析：如果val大于root的值，则将val作为新的根节点，将root作为val节点的左子节点
        // 如果val小于root的值，则将val放在root的右子树中
        if (null == root) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            return new TreeNode(val, root, null);
        } else {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }
    }

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
}
