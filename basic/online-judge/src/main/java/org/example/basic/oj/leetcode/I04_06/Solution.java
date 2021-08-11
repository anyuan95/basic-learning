package org.example.basic.oj.leetcode.I04_06;

import lombok.ToString;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-08-11 22:13
 */
class Solution {
    @ToString
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            // 如果有右子树，那么后继节点就是右树上最左的一个节点
            TreeNode current = p.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        } else {
            // 如果没有右子树的话，
            // 如果当前节点是父节点的左子树，那么父节点就是后继节点
            // 如果当前节点是父节点的右子树，那么就一直向上找，直到当前节点是父节点的左子树为止，那么这个父节点就是后继节点
            // 由于是BST，所以用二分找到当前节点的所有祖先节点

            List<TreeNode> list = new ArrayList<>();
            TreeNode current = root;
            while (current != null) {
                list.add(current);
                if (current == p) {
                    break;
                }
                if (current.val < p.val) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            }

            for (int i = list.size() - 1; i > 0; i--) {
                current = list.get(i);
                // 如果祖先中，第i个祖先是其父亲的子节点
                final TreeNode parent = list.get(i-1);
                if (parent.left == current) {
                    return parent;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final TreeNode node1 = new TreeNode(1);
        TreeNode root = new TreeNode(2, node1, new TreeNode(3));
        System.out.println(solution.inorderSuccessor(root, node1));
    }
}
