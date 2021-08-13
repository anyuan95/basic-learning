package org.example.basic.oj.leetcode.Q112;

/**
 * 给定二叉树头节点，给定target，求是否有[根节点到某叶子结点的值的和等于target]
 *
 * @author anyuan
 * @since 2021-08-13 11:16
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
     * 递归向左右子树分别搜索
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return process(root, targetSum);
    }

    /**
     * 查找当前节点的子树中是否有满足和为目标值的
     */
    public boolean process(TreeNode current, int targetSum) {
        if (current == null) {
            return false;
        }
        if (current.val == targetSum && current.left == null && current.right == null) {
            return true;
        }
        return process(current.left, targetSum - current.val)
                || process(current.right, targetSum - current.val);
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        TreeNode root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11, new TreeNode(7), new TreeNode(2)),
                        null),
                new TreeNode(8,
                        new TreeNode(13),
                        new TreeNode(4, null, new TreeNode(1))));
        System.out.println(solution.hasPathSum(root, 22));
    }

}
