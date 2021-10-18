package org.example.basic.oj.leetcode.Q230;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author anyuan
 * @date 2021-10-17 17:31
 */
class Solution_pq {
    private static class TreeNode {
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
        final PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            final TreeNode currentNode = q.poll();
            if (pq.size() < k) {
                // 如果当前拿到的值不足k个，那就直接放进去
                pq.add(currentNode.val);
            } else if (currentNode.val < pq.peek()) {
                // 否则就是已经够k个了，那就看当前节点的值是不是小于队列里的最大值
                // 如果小于，就弹出来，把小的放进去
                pq.poll();
                pq.add(currentNode.val);
            }
            if (currentNode.left != null) {
                q.add(currentNode.left);
            }
            if (currentNode.right != null) {
                q.add(currentNode.right);
            }
        }
        return pq.peek();
    }
}
