package org.example.basic.oj.leetcode.Q337;

/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * <p>
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * <p>
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 * @author anyuan
 * @date 2022-03-08 11:01
 */
class Solution {

    /**
     * 本次问题环境改成了二叉树
     * 不能访问带有直接父子关系的两个节点
     * 那么就是有三种作为起点的情况：根节点，根的左子节点，根的右子节点
     * <p>
     * 题意看错了，不是隔层求最大价值路径
     * 而是只要不相连的，都可以拿
     *
     * @param root
     * @return
     */


    /**
     * 思路：
     * 由于是只能向下的二叉树，所以需要从较高的节点开始考虑
     * 还是列举所有可能性(假设现在在cur节点)：
     * 1.拿当前节点，和左右孙子节点
     * 2.拿左子节点，和右子节点
     */
    public int rob1(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        // 拿当前节点，和左右孙子节点
        int p1 = cur.val
                + (cur.left != null ? rob1(cur.left.left) + rob1(cur.left.right) : 0)
                + (cur.right != null ? rob1(cur.right.left) + rob1(cur.right.right) : 0);
        // 不拿当前节点，拿左右子节点
        int p2 = rob1(cur.left) + rob1(cur.right);
        return Math.max(p1, p2);
    }

    /**
     * 把rob1改成dp
     */
    public int rob2(TreeNode cur) {
        final int[] dfs = dfs(cur);
        return Math.max(dfs[0], dfs[1]);
    }

    /**
     * 返回一个数组，0位置表示不选择该节点时能获得的最大价值，1位置表示选择该节点时能获得的最大价值
     */
    private int[] dfs(TreeNode cur) {
        if (cur == null) {
            return new int[]{0,0};
        }
        final int[] left = dfs(cur.left);
        final int[] right = dfs(cur.right);

        // 拿当前节点，不拿左右子节点
        int p1 = cur.val + left[0] + right[0];
        // 不拿当前节点，拿左右子节点（也可以不拿）
        int p2 = Math.max(left[0], left[1]) + Math.max(right[0],right[1]);
        return new int[]{p2, p1};
    }


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
}
