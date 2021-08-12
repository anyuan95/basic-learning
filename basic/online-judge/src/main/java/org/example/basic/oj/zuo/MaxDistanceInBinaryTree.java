package org.example.basic.oj.zuo;

/**
 * 求一个二叉树中的节点最大距离。
 * 定义：
 * 距离 = 从节点1走到节点2的最少步数
 *
 * @author anyuan
 * @since 2021-08-12 16:26
 */
class MaxDistanceInBinaryTree {

    /**
     * 场景拆分：
     * 1.最大距离路径不经过头节点
     *   则最大距离是 左树的最大距离和右数的最大距离 之间最大的
     * 2.最大距离路径经过头结点
     *   则最大距离是 左树最深的节点深度+右树最深的节点深度±1
     * 最大值一定在上述1和2两种情况中
     *
     *
     * @param root
     * @return
     */
    public int maxDistanceInBinaryTree(TreeNode root) {
        return 0;
    }

}
