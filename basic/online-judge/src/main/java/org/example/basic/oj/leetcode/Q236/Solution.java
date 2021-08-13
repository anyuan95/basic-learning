package org.example.basic.oj.leetcode.Q236;

/**
 * 给定树的头结点root，节点a，节点b，求a和b的最近公共祖先
 *
 * @author anyuan
 * @since 2021-08-13 14:27
 */
class Solution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 问题细分
     * 对于头结点root，有两种情况：
     * 1.root是a和b的最短公共祖先；
     * 1.1.a在左树，b在右树；或者相反
     * 1.2.x就是a，左树或右树中有b；
     * 1.3.x就是b，左树或右树中有a；
     * 2.root不是a和b的最短公共祖先；
     * 2.1.a和b的最短公共祖先在root左树上；
     * 2.2.~~~~~~~~~~~~~~~~在root右树上；
     * 2.3.a和b不都在这棵树上；
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).lowestCommonAncestor;
    }

    static class Info {
        boolean findA;
        boolean findB;
        TreeNode lowestCommonAncestor;

        public Info(boolean findA, boolean findB, TreeNode lowestCommonAncestor) {
            this.findA = findA;
            this.findB = findB;
            this.lowestCommonAncestor = lowestCommonAncestor;
        }
    }

    public Info process(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(root.left, a, b);
        Info rightInfo = process(root.right, a, b);
        boolean findA = leftInfo.findA || rightInfo.findA || root == a;
        boolean findB = leftInfo.findB || rightInfo.findB || root == b;
        TreeNode lowestCommonAncestor = null;
        if (leftInfo.lowestCommonAncestor != null) {
            lowestCommonAncestor = leftInfo.lowestCommonAncestor;
        } else if (rightInfo.lowestCommonAncestor != null) {
            lowestCommonAncestor = rightInfo.lowestCommonAncestor;
        } else if ((findA && root == b) || (findB && root == a) || (findA && findB)) {
            lowestCommonAncestor = root;
        }
        return new Info(findA, findB, lowestCommonAncestor);
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
        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node2.left = node7;
        node2.right = node4;
        node1.left = node0;
        node1.right = node8;
        TreeNode nodeX = new TreeNode(-1);

        System.out.println(solution.lowestCommonAncestor(node3, node2, nodeX).val);
    }

}
