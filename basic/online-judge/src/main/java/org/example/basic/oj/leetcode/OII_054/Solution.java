package org.example.basic.oj.leetcode.OII_054;

import lombok.ToString;

import javax.swing.tree.TreeNode;
import java.util.Stack;

/**
 * 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 *
 * @author anyuan
 * @since 2021-08-12 21:59
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
     * 思路：反中序遍历+修改节点值
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int sum = 0;
        while (!stack.isEmpty() || current != null) {
            if (current == null) {
                current = stack.pop();
                sum += current.val;
                current.val = sum;
                // print
                current = current.left;
            } else {
                stack.push(current);
                current = current.right;
            }
        }
        return root;
    }

    int sum = 0;

    public TreeNode convertBST_(TreeNode root) {
        if (root != null) {
            convertBST_(root.right);
            sum += root.val;
            root.val = sum;
            convertBST_(root.left);
        }
        return root;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        node4.left = node1;
        node1.left = node0;
        node1.right = node2;
        node2.right = node3;
        node4.right = node6;
        node6.left = node5;
        node6.right = node7;
        node7.right = node8;
        System.out.println(solution.convertBST(node4));

    }

}
