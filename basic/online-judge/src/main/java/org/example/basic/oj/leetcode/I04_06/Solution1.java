package org.example.basic.oj.leetcode.I04_06;

class Solution1 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (null == root) {
            return null;
        }
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        TreeNode answer = inorderSuccessor(root.left, p);
        return null == answer ? root : answer;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
