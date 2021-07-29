package org.example.basic.oj.leetcode.Q671;

/**
 * @author anyuan
 * @since 2021-07-27 16:04
 */
class Solution {

    private int answer;

    public int findSecondMinimumValue(TreeNode root) {
        answer = -1;
        dfs(root, root.val);
        return answer;
    }

    private void dfs(TreeNode root, int minValue) {
        if (root == null) {
            return;
        }
        if (answer != -1 && root.val >= answer) {
            return;
        }
        if (root.val > minValue) {
            answer = root.val;
        }
        dfs(root.left, minValue);
        dfs(root.right, minValue);
    }

}

