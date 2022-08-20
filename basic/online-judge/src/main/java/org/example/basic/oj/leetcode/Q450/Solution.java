package org.example.basic.oj.leetcode.Q450;

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            // 考虑当前根节点删除的情况，如果当前节点只有左子树或者只有右子树，那就返回这个子树
            // 否则（既有左子树又有右子树），有两种方式，一种是从左子树中找到最右的子节点作为根节点，或者也可以在右子树中找到最左的子节点作为根节点
            // 把原本根节点的右子树全部放在左子树最右节点的右子树（或者反过来）(因为能够保证原本根节点的右子树上的所有值一定都大于原本根节点的左子树上的最大值）
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode t = root.left;
            while (t.right != null) t = t.right;
            t.right = root.right;
            return root.left;
        } else if (root.val < key) root.right = deleteNode(root.right, key);
        else root.left = deleteNode(root.left, key);
        return root;
    }

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
}
