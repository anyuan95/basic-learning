package org.example.basic.oj.leetcode.Q655;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @date 2022-08-22 21:45
 */
class Solution {
    /**
     * 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
     * 矩阵的列数 n 应该等于 2height+1 - 1 。
     * 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
     * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2height-r-1] ，右子节点放置在 res[r+1][c+2height-r-1] 。
     * 继续这一过程，直到树中的所有节点都妥善放置。
     * 任意空单元格都应该包含空字符串 "" 。
     *
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        final int height = calculateHeight(root) - 1;
        final int n = (int) (Math.pow(2, height + 1) - 1);
        final String[][] array = new String[height + 1][n];
        for (String[] nums : array) {
            Arrays.fill(nums, "");
        }
        putNode(root, 0, (n - 1) / 2, height, array);
        final List<List<String>> answer = new ArrayList<>();
        for (String[] nums : array) {
            answer.add(Arrays.asList(nums));
        }
        return answer;
    }

    private void putNode(TreeNode root, int r, int c, int height, String[][] array) {
        if (null == root) {
            return;
        }
        array[r][c] = String.valueOf(root.val);
        if (null != root.left) {
            putNode(root.left, r + 1, c - (1 << (height - r - 1)), height, array);
        }
        if (null != root.right) {
            putNode(root.right, r + 1, c + (1 << (height - r - 1)), height, array);
        }
    }

    private int calculateHeight(TreeNode root) {
        if (null == root) {
            return 0;
        }
        if (null == root.left && null == root.right) {
            return 1;
        }
        return 1 + Math.max(calculateHeight(root.left), calculateHeight(root.right));
    }

    public static class TreeNode {
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
}
