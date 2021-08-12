package org.example.basic.oj.leetcode.Q783;

/**
 * 求BST两个节点之间的最小差值
 *
 * @author anyuan
 * @since 2021-08-12 10:36
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


    /**
     * 思路：
     * 题目要求求相差最小的两个数。
     * 对于每个节点来说，与其相关的最小差一定是与比它小的最大值的差或与比它大的最小值的差。
     * 由于是BST，所以比每个根节点小的最大值一定是在其左子树中的最大值；
     * 同理，比每个根节点大的最小值一定是在其右子树中的最小值。
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        return process(root).minDiff;
    }

    static class NodeInfo {
        int minDiff;
        int minValue;
        int maxValue;

        public NodeInfo(int minDiff, int minValue, int maxValue) {
            this.minDiff = minDiff;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    private NodeInfo process(TreeNode current) {
        if (current == null) {
            return null;
        }
        final NodeInfo leftInfo = process(current.left);
        final NodeInfo rightInfo = process(current.right);
        // 最小差一定在以下值中：
        // 左子树的最小差、右子树的最小差、当前值-左子树最大值、右子树最小值-当前值
        // 因为是要取最小值，所以初始值用MAX_VALUE
        int minDiff = Integer.MAX_VALUE;
        if (leftInfo != null) {
            minDiff = min(minDiff, leftInfo.minDiff, current.val - leftInfo.maxValue);
        }
        if (rightInfo != null) {
            minDiff = min(minDiff, rightInfo.minDiff, rightInfo.minValue - current.val);
        }
        int minValue = leftInfo != null ? leftInfo.minValue : current.val;
        int maxValue = rightInfo != null ? rightInfo.maxValue : current.val;
        return new NodeInfo(minDiff, minValue, maxValue);
    }

    private int min(int... values) {
        int min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] < min) {
                min = values[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(6));
        System.out.println(solution.minDiffInBST(root));
    }
}
