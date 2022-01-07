package org.example.basic.oj.leetcode.Q1609;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-12-25 23:33
 */
class Solution {

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

    public boolean isEvenOddTree(TreeNode root) {
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 第一层记作1
        int currentDepth = 0;
        while (!queue.isEmpty()) {
            final int size = queue.size();
            int currentDepthLastNodeValue = currentDepth % 2 == 0 ? -1 : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                final TreeNode poll = queue.poll();
                if (currentDepth % 2 == 0 && (poll.val <= currentDepthLastNodeValue || poll.val % 2 == 0)
                        || currentDepth % 2 == 1 && (poll.val >= currentDepthLastNodeValue || poll.val % 2 == 1)) {
                    return false;
                }
                // 否则记一下当前层上一个节点的值
                currentDepthLastNodeValue = poll.val;
                // 并把子节点加进队列
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            currentDepth++;
        }
        return true;
    }
}
