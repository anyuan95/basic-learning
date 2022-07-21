package org.example.basic.oj.leetcode.Q814;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

    public TreeNode pruneTree(TreeNode root) {
        if (root.left != null) {
            root.left = pruneTree(root.left);
        }
        if (root.right != null) {
            root.right = pruneTree(root.right);
        }
        if (root.val == 0 && root.left == null && root.right == null) {
            root = null;
        }
        return root;
    }

//    public TreeNode pruneTree(TreeNode root) {
//        if (root.left != null) {
//            if (root.left.val == 0 && root.left.left == null && root.left.right == null) {
//                root.left = null;
//            } else {
//                root.left = pruneTree(root.left);
//            }
//        }
//        if (root.right != null) {
//            if (root.right.val == 0 && root.right.left == null && root.right.right == null) {
//                root.right = null;
//            } else {
//                root.right = pruneTree(root.right);
//            }
//        }
//        if (root.left == null && root.right == null) {
//            root = null;
//        }
//        return root;
//    }

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