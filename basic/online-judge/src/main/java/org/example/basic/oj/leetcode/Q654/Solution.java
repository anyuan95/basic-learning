package org.example.basic.oj.leetcode.Q654;

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        final int maxIndex = findMaxIndex(nums, start, end);
        final TreeNode root = new TreeNode(nums[maxIndex]);
        if (maxIndex > start) {
            root.left = buildTree(nums, start, maxIndex - 1);
        }
        if (maxIndex < end) {
            root.right = buildTree(nums, maxIndex + 1, end);
        }
        return root;
    }

    private int findMaxIndex(int[] nums, int start, int end) {
        int maxIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5}));
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
