package org.example.basic.oj.leetcode.Q110;

/**
 * 判断给定的树是否是平衡二叉树
 * <p>
 * 定义：最高子树和最低子树高度差不超过1
 *
 * @author anyuan
 * @since 2021-08-12 09:16
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
        int maxHeight;
        boolean isBalanced;

        public NodeInfo(int maxHeight, boolean isBalanced) {
            this.maxHeight = maxHeight;
            this.isBalanced = isBalanced;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    private NodeInfo process(TreeNode head) {
        if (head == null) {
            return new NodeInfo(0, true);
        }
        final NodeInfo leftInfo = process(head.left);
        final NodeInfo rightInfo = process(head.right);
        NodeInfo currentNodeInfo = new NodeInfo(0, true);
        // 判断当前节点是否是平衡二叉树，条件：
        // 1.左子树或者右子树不是平衡二叉树，则当前节点也不是平衡二叉树；
        // 2.左子树和右子树都是平衡二叉树，则判断当前节点左子树和右子树的最大高度差是否超过1；
        if (!leftInfo.isBalanced || !rightInfo.isBalanced
                || Math.abs(leftInfo.maxHeight - rightInfo.maxHeight) > 1) {
            currentNodeInfo.isBalanced = false;
        }
        // 当前节点的最大高度 = 子树的最大高度 + 1
        currentNodeInfo.maxHeight = Math.max(leftInfo.maxHeight, rightInfo.maxHeight) + 1;
        return currentNodeInfo;
    }


}
