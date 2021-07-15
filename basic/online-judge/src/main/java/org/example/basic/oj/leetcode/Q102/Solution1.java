package org.example.basic.oj.leetcode.Q102;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：每层遍历2^n个元素，即使元素为空也正常放到list中。
 * P：未通过，应该是因为必须是一个完整的树？
 */
class Solution1 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        int maxDepth = height(root);
        for (int i = 0, k = 0; i < maxDepth; i++) {
            final ArrayList<Integer> currentList = new ArrayList<>();
            int currentFloorNodeCount = (int) Math.pow(2, i);
            for (int j = 0; j < currentFloorNodeCount && !nodeList.isEmpty(); j++) {
                final TreeNode node = nodeList.get(k);
                k++;
                if (node != null) {
                    nodeList.add(node.left);
                    nodeList.add(node.right);
                    currentList.add(node.val);
                }
            }
            result.add(currentList);
        }
        return result;
    }

    public static int height(TreeNode binaryTree) {
        if (binaryTree == null) {
            return 0;
        }
        return Math.max(height(binaryTree.left), height(binaryTree.right)) + 1;
    }

    public static void main(String[] args) {
//        final TreeNode tree = TreeNode.parse(new Integer[]{1, 2, null, 3, null, 4, null, 5});
//        System.out.println(new Solution1().levelOrder(tree));
    }
}