package org.example.basic.oj.zuo;

/**
 * 统计给定的二叉树中，所有是搜索二叉树的子树中，节点数量最多的一个。
 *
 * @author anyuan
 * @since 2021-08-12 16:47
 */
public class MaxSubBSTSize {

    /**
     * 思路：
     * 分为以下几种情况：
     * 1.当前节点x的左树中最大的搜索二叉树
     * 2.当前节点x的右树中最大的搜索二叉树
     * 3.当前节点是这棵最大的搜索二叉树的头节点（左树是搜索二叉树 && 右树是搜索二叉树 && 左树最大值<当前节点<右树最小值）
     *
     * @param root
     * @return
     */
    private int maxSubBSTSize(TreeNode root) {
        return 0;
    }
}
