package org.example.basic.oj.zuo.tree;

import javax.sound.sampled.DataLine.Info;

/**
 * 查找给定二叉树中最大的一棵BST的头节点。
 *
 * @author anyuan
 * @since 2021-08-12 22:31
 */
class MaxSubBST {

    static class Info {
        int minValue;
        int maxValue;
        int maxBSTSize;
        TreeNode maxBSTHead;

        public Info(int minValue, int maxValue, int maxBSTSize, TreeNode maxBSTHead) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.maxBSTSize = maxBSTSize;
            this.maxBSTHead = maxBSTHead;
        }
    }

    private TreeNode maxSubBST(TreeNode root) {
        return process(root).maxBSTHead;
    }

    /**
     * 问题拆分：
     * 什么样的树是BST？
     * 1.左树是BST，右树是BST，左树最大值<当前值<右树最小值
     *
     * @param current
     * @return
     */
    private Info process(TreeNode current) {
        if (current == null) {
            return null;
        }
        final Info leftInfo = process(current.left);
        final Info rightInfo = process(current.right);
        int minValue = current.val;
        int maxValue = current.val;
        int maxBSTSize = 1;
        TreeNode maxBSTHead = null;

        if (leftInfo != null) {
            minValue = Math.min(minValue, leftInfo.minValue);
            maxValue = Math.max(maxValue, leftInfo.maxValue);
            maxBSTSize = leftInfo.maxBSTSize;
            maxBSTHead = leftInfo.maxBSTHead;
        }

        if (rightInfo != null) {
            minValue = Math.min(minValue, rightInfo.minValue);
            maxValue = Math.max(maxValue, rightInfo.maxValue);
            if (maxBSTSize < rightInfo.maxBSTSize) {
                maxBSTSize = rightInfo.maxBSTSize;
                maxBSTHead = rightInfo.maxBSTHead;
            }
        }

        if ((leftInfo == null || (leftInfo.maxBSTHead == current.left && leftInfo.maxValue < current.val))
                && (rightInfo == null || (rightInfo.maxBSTHead == current.right && rightInfo.minValue > current.val))) {
            maxBSTHead = current;
            maxBSTSize = (leftInfo == null ? 0 : leftInfo.maxBSTSize) + (rightInfo == null ? 0 : rightInfo.maxBSTSize) + 1;
        }
        return new Info(minValue, maxValue, maxBSTSize, maxBSTHead);
    }


}
