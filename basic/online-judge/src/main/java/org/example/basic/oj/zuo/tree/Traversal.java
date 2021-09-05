package org.example.basic.oj.zuo.tree;

import java.util.*;

/**
 * @author anyuan
 * @since 2021-09-03 16:58
 */
class Traversal {

    /**
     * 前序：根-左-右
     * <p>
     * 弹出一个元素，这个元素作为根元素放到结果列表中；
     * 如果这个元素有右孩子，则把右孩子压入栈；
     * 如果这个元素有左孩子，则把左孩子压入栈；
     * <p>
     * 这种压入顺序保证了，下一次循环时先拿到的元素会是（当前元素的）左元素
     *
     * @param root
     * @return
     */
    private List<TreeNode> preOrder(TreeNode root) {
        List<TreeNode> answer = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            final TreeNode temp = stack.pop();
            answer.add(temp);
            if (temp.right != null) {
                answer.add(temp.right);
            }
            if (temp.left != null) {
                answer.add(temp.left);
            }
        }
        return answer;
    }

    /**
     * 中序：左-中-右
     * <p>
     * （从root节点开始）处理当前节点：
     * 1.如果不为空就放到队列中，然后处理左；
     * 2.如果为空就弹出来一个，加到结果中，然后处理右；
     *
     * @param root
     * @return
     */
    private List<TreeNode> inOrder(TreeNode root) {
        List<TreeNode> answer = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                final TreeNode temp = stack.pop();
                answer.add(temp);
                current = temp.right;
            }
        }
        return answer;
    }

    /**
     * 后序：左-右-中
     * <p>
     * 实际上就是调整前序的顺序（能够得到一个'中-右-左'的顺序），然后翻转过来就是左右中了
     *
     * @param root
     * @return
     */
    private List<TreeNode> postOrder(TreeNode root) {
        List<TreeNode> answer = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        // 用helper接住stack每次弹出来的元素
        Stack<TreeNode> helper = new Stack<>();
        while (!stack.isEmpty()) {
            final TreeNode temp = stack.pop();
            helper.push(temp);
            if (temp.left != null) {
                stack.push(temp.left);
            }
            if (temp.right != null) {
                stack.push(temp.right);
            }
        }
        while (!helper.isEmpty()) {
            answer.add(helper.pop());
        }
        return answer;
    }


    /**
     * 层序遍历
     * <p>
     * 队列
     *
     * @param root
     * @return
     */
    private List<TreeNode> floorOrder(TreeNode root) {
        List<TreeNode> answer = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final TreeNode temp = queue.poll();
            answer.add(temp);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        return answer;
    }
}
