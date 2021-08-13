package org.example.basic.oj.leetcode.Q230;

import java.util.Stack;

/**
 * 查找给定的BST中第k小的节点
 * k从1开始计数
 * <p>
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
 *
 * @author anyuan
 * @since 2021-08-13 14:05
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

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.isEmpty() || current != null) {
            if (current == null) {
                current = stack.pop();
                k--;
                if (k == 0) {
                    return current.val;
                }
                current = current.right;
            } else {
                stack.push(current);
                current = current.left;
            }
        }
        return -1;
    }
}
