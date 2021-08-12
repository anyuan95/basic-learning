package org.example.basic.oj.leetcode.Q863;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 获取二叉树中所有距离给定节点为K的节点（的值）
 *
 * @author anyuan
 * @since 2021-08-12 13:41
 */
class Solution {

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

    /**
     * 思路：问题规模拆分
     * 分成两种情况：
     * 1.给定节点的子树中查找距离当前节点为k的节点
     * 2.距离当前节点为i的祖先节点中查找距离该节点为k-i的节点（注意不要重复计算）
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (k == 0) {
            return Collections.singletonList(target.val);
        }
        List<Integer> answer = new ArrayList<>();
        HashSet<TreeNode> traversedNodes = new HashSet<>();

        // 先找当前节点的子树
        seekTargetDistanceSubNodes(target, k, traversedNodes, answer);
        traversedNodes.add(target);

        // 找到当前节点的所有祖先节点
        List<TreeNode> ancestors = new ArrayList<>();
        // 这个结果列表是倒序的，下标越小，离目标节点越近
        isAncestor(root, target, ancestors);

        // 这里必须从最近的父节点开始往上找，才能避免距离计算错误
        // 只要遍历k级祖先节点就行了
        for (int i = 0; i < Math.min(k, ancestors.size()); i++) {
            TreeNode ithAncestor = ancestors.get(i);
            seekTargetDistanceSubNodes(ithAncestor, k - i - 1, traversedNodes, answer);
            traversedNodes.add(ithAncestor);
        }
        return answer;
    }

    private boolean isAncestor(TreeNode current, TreeNode target, List<TreeNode> ancestors) {
        if (current == null) {
            return false;
        }
        if (current == target) {
            return true;
        }
        if (isAncestor(current.left, target, ancestors) || isAncestor(current.right, target, ancestors)) {
            ancestors.add(current);
            return true;
        }
        return false;
    }

    private void seekTargetDistanceSubNodes(TreeNode current, int x, HashSet<TreeNode> traversedNodes, List<Integer> answer) {
        if (traversedNodes.contains(current) || x < 0 || current == null) {
            // 如果当前节点已经遍历过了，返回
            // 如果距离已经遍历到<0了，返回
            // 如果遍历到null了，返回
            return;
        }
        if (x == 0) {
            // 如果要找距离当前节点为0的节点
            // ？那不就是当前节点吗
            traversedNodes.add(current);
            answer.add(current.val);
            return;
        }
        // 找当前节点的所有距离为x的子节点，可以等价于找当前节点的左右子节点的距离为x-1的节点
        seekTargetDistanceSubNodes(current.left, x - 1, traversedNodes, answer);
        seekTargetDistanceSubNodes(current.right, x - 1, traversedNodes, answer);
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
//        [3,5,1,6,2,0,8,null,null,7,4]
        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node2.left = node7;
        node2.right = node4;
        node1.left = node0;
        node1.right = node8;
        TreeNode root = node3, target = node2;
        int k = 3;
        System.out.println(solution.distanceK(root, target, k));
    }

}
