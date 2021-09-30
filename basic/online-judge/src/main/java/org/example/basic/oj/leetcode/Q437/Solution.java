package org.example.basic.oj.leetcode.Q437;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-28 22:03
 */
class Solution {
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

    private int answer;

    /**
     * 路径总和，求二叉树中节点值之和等于target的路径数量
     * <p>
     * 思路：前缀和 + dfs + 回溯
     * 类似前缀和的方式
     * 先做一个遍历次数比较多的方式：从根节点开始，向下寻找路径，将路径中计算得到的所有前缀和都记录到一个list中（在使用完后再删掉）；
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        // 初始化答案
        answer = 0;

        // 记录从根节点到当前节点路径上每个节点[到根节点的前缀和]。
        // 用当前节点的前缀和逐个减去列表中的所有前缀和，发现有值等于target的节点，就将结果记录数加一。
        List<Integer> prefixSums = new ArrayList<>();
        // 注意这里需要加一个前缀和0。因为有可能从根节点开始（或者只有根节点自己时）满足条件。
        prefixSums.add(0);
        dfs(prefixSums, root, targetSum);
        return answer;
    }

    private void dfs(List<Integer> prefixSums, TreeNode current, Integer targetSum) {
        // 处理当前节点，然后再处理左子节点和右子节点
        if (current == null) {
            return;
        }
        // 路径中之前节点的前缀和就是prefixSums中最后一个值
        // 通过上一个节点的前缀和，加上当前节点的值，计算得到当前节点的前缀和
        final Integer currentPrefixSum = prefixSums.get(prefixSums.size() - 1) + current.val;
        // 还是可以倒过来算的，还是为了减少计算次数
        final int targetPrefixSum = currentPrefixSum - targetSum;

        // 只要计算所有以当前节点结束的序列和即可
        for (Integer prefixSum : prefixSums) {
            if (prefixSum == targetPrefixSum) {
                answer++;
            }
        }
        // 然后把当前节点加进前缀和列表中
        prefixSums.add(prefixSums.get(prefixSums.size() - 1) + current.val);
        // 然后继续处理左节点和右节点
        dfs(prefixSums, current.left, targetSum);
        dfs(prefixSums, current.right, targetSum);
        // 当前节点的左右子节点都处理完了，那当前节点的前缀和后面就不会再用到了，删掉
        // TODO 处理完左右子节点之后，当前前缀和列表中最后一个值应该就是当前节点的前缀和了       吧？
        prefixSums.remove(prefixSums.size() - 1);
    }
}
