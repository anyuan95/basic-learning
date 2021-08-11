package org.example.basic.oj.leetcode.Q431$;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 将N叉树编码为二叉树
 * <p>
 * 思路：
 * 将当前节点的所有子节点都放在其左子树的右边界上
 *
 * @author anyuan
 * @since 2021-08-11 21:08
 */
class Solution {
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            final TreeNode head = new TreeNode(root.val);
            head.left = _encode(root.children);
            return head;
        }

        public TreeNode _encode(List<Node> children) {
            if (children == null || children.isEmpty()) {
                return null;
            }
            TreeNode head = null;
            TreeNode current = null;
            for (Node child : children) {
                final TreeNode treeNode = new TreeNode(child.val);
                if (head == null) {
                    head = treeNode;
                } else {
                    current.right = treeNode;
                }
                treeNode.left = _encode(child.children);
                current = treeNode;
            }
            return head;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, _decode(root));
        }

        public List<Node> _decode(TreeNode root) {
            List<Node> nodes = new ArrayList<>();
            while (root != null) {
                nodes.add(new Node(root.val, _decode(root.left)));
                root = root.right;
            }
            return nodes;
        }

    }

}
