package org.example.basic.oj.leetcode.Q662_unfinished;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-08-11 21:28
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

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode currentEnd = root;
        TreeNode nextEnd = null;
        int maxWidth = 0;
        int nextLineFirstNonNullNumberIndex = 0, nextLineLastNonNullNumberIndex = 0;
        int currentIndex = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final TreeNode current = queue.remove();
            if (current.left != null) {
                queue.add(current.left);
                nextEnd = current.left;
                if (nextLineFirstNonNullNumberIndex == 0) {
                    nextLineFirstNonNullNumberIndex = 2 * currentIndex + 1;
                }
            }
            if (current.right != null) {
                queue.add(current.right);
                nextEnd = current.right;
                if (nextLineFirstNonNullNumberIndex == 0) {
                    nextLineFirstNonNullNumberIndex = 2 * currentIndex + 2;
                }
                nextLineLastNonNullNumberIndex = 2 * currentIndex + 2;
            }

            if (current == currentEnd) {
                currentEnd = nextEnd;
                maxWidth = Math.max(nextLineLastNonNullNumberIndex - nextLineFirstNonNullNumberIndex + 1, maxWidth);
                nextLineFirstNonNullNumberIndex = 0;
                nextLineLastNonNullNumberIndex = 0;
            }
            currentIndex++;
        }
        return maxWidth;
    }

    public static void main(String[] args) {
//        [1,3,2,5,3,null,9]
        final TreeNode treeNode = new TreeNode(1, new TreeNode(3, new TreeNode(5), new TreeNode(3)), new TreeNode(2, null, new TreeNode(9)));
        final Solution solution = new Solution();
        System.out.println(solution.widthOfBinaryTree(treeNode));
    }
}
