package org.example.basic.oj.leetcode.Q98;

/**
 * 验证给定的树是否是二叉搜索树
 *
 * @author anyuan
 * @since 2021-08-12 09:28
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

    static class NodeInfo {
        int minValue;
        int maxValue;
        boolean isBST;

        public NodeInfo(int minValue, int maxValue, boolean isBST) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.isBST = isBST;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }

    private NodeInfo process(TreeNode root) {
        if (root == null) {
            return new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
        final NodeInfo leftInfo = process(root.left);
        final NodeInfo rightInfo = process(root.right);
        final NodeInfo currentInfo = new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        // 两种情况下，当前树不是BST：
        // 1.子树不是BST；
        // 2.当前三角不符合左<中<右，即中<=左或中>=右
        if (!leftInfo.isBST || !rightInfo.isBST) {
            currentInfo.isBST = false;
        } else if ((root.left != null && root.val <= leftInfo.maxValue)
                || (root.right != null && root.val >= rightInfo.minValue)) {
            currentInfo.isBST = false;
        }

        currentInfo.maxValue = Math.max(Math.max(leftInfo.maxValue, rightInfo.maxValue), root.val);
        currentInfo.minValue = Math.min(Math.min(leftInfo.minValue, rightInfo.minValue), root.val);
        return currentInfo;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(solution.isValidBST(root));
    }
}
