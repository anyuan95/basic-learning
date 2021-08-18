package org.example.basic.oj.leetcode.Q968;

/**
 * @author anyuan
 * @since 2021-08-18 14:51
 */
public class Solution_dfs {

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

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (dfs(root) == 0) {
            ans++;
        }
        return ans;
    }

    private int ans = 0;

    /**
     * 还是使用3个标志位标识状态
     * 0-黑暗 1-照亮 2-灯
     *
     * 返回值是该节点的状态信息
     *
     * @param current
     * @return
     */
    private int dfs(TreeNode current) {
        if (current == null) {
            return 1;
        }
        int left = dfs(current.left), right = dfs(current.right);
        if (left == 0 || right == 0) {
            ans++;
            return 2;
        } else if (left == 2 || right == 2) {
            return 1;
        } else {
            return 0;
        }
    }

}
