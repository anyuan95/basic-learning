package org.example.basic.oj.leetcode.Q662;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int widthOfBinaryTree(TreeNode root) {
        final LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        int answer = 1;
        queue.add(new Pair<>(root, 1));
        while (!queue.isEmpty()) {
            final int size = queue.size();
            // 计算最大值
            answer = Math.max(answer, queue.get(size - 1).getValue() - queue.get(0).getValue() + 1);
            // 取第1个和第size个元素的值做计算
            for (int i = 0; i < size; i++) {
                final Pair<TreeNode, Integer> poll = queue.poll();
                final TreeNode node = poll.getKey();
                if (node.left != null) {
                    queue.add(new Pair<>(node.left, poll.getValue() * 2));
                }
                if (node.right != null) {
                    queue.add(new Pair<>(node.right, poll.getValue() * 2 + 1));
                }
            }
        }
        return answer;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
