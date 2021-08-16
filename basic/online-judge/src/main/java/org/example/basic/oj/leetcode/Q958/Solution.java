package org.example.basic.oj.leetcode.Q958;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 验证完全二叉树
 *
 * @author anyuan
 * @since 2021-08-12 21:21
 */
class Solution {
    public class TreeNode {
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
     * 遍历所有节点，要求满足以下两个条件：
     * 1.如果一个节点的左子树为空，但右子树不为空，那么一定不是完全二叉树；
     * 2.如果遇到了一个节点不同时含有左右孩子，那么剩下的节点必须不含有左右孩子
     *
     * @param root
     * @return
     */
    private boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean foundNotCBTNode = false;
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (current.left == null && current.right != null) {
                return false;
            }
            // 如果已经找到过不含完整左右子树的节点，那么后续节点必须都是叶子节点，即左右子树必须都不能存在
            if (foundNotCBTNode && (current.left != null || current.right != null)) {
                return false;
            }
            if (current.left == null || current.right == null) {
                foundNotCBTNode = true;
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return true;
    }
}
