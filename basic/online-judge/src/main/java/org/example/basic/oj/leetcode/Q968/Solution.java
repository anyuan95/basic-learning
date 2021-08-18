package org.example.basic.oj.leetcode.Q968;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 监控二叉树
 *
 * @author anyuan
 * @since 2021-08-18 14:02
 */
class Solution {
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
        return postOrder(root);
    }

    /**
     * 思路：
     * 后序遍历，然后逐个节点收集其子节点的信息，决定当前节点的处理方式
     *
     * @param root
     * @return
     */
    private int postOrder(TreeNode root) {
        // ...
        if (root == null) {
            return 0;
        }
        // 有一种情况，就是只有一个节点，那么它就是叶子节点，但是也需要一盏灯
        if (root.left == null && root.right == null) {
            return 1;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        Stack<TreeNode> helper = new Stack<>();

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            helper.push(current);
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        List<TreeNode> preOrderNodeList = new ArrayList<>();
        while (!helper.isEmpty()) {
            preOrderNodeList.add(helper.pop());
        }

        // 0:黑暗 1:被照亮 2:装了灯

        int totalCameraNeed = 0;
        // 现在answer中的顺序就是后序遍历的顺序
        for (TreeNode current : preOrderNodeList) {
            if (current.left != null || current.right != null) {
                // 叶子节点本来就是0，不用管
                // 收集左右子节点的信息（需求）
                if ((current.left != null && current.left.val == 0) || (current.right != null && current.right.val == 0)) {
                    // 如果子节点有暗的(0)，那当前节点需要装一盏灯(2)，然后照亮他们(1)
                    current.val = 2;
                    totalCameraNeed++;
                    // 应该不需要再调整子节点的状态了，因为用不到了
                } else if ((current.left != null && current.left.val == 2) || (current.right != null && current.right.val == 2)) {
                    // 如果子节点装了灯(2)，那当前节点就被照亮了(1)
                    current.val = 1;
                } else {
                    // 子节点中既没有暗的，也没有灯，则当前节点不用管
                    // do nothing
                }
            }
        }
        if (root.val == 0) {
            // 因为所有的点灯操作都是交给父节点进行的，而根节点并没有将数据交给父节点处理，所以根节点需要独立判断一次状态
            totalCameraNeed++;
        }
        return totalCameraNeed;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final TreeNode root = new TreeNode( 0, null, new TreeNode( 0, null, new TreeNode( 0, null, new TreeNode( 0))));
        System.out.println(solution.minCameraCover(root));
    }

}
