package org.example.basic.algorithm.tree;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.ToString;

import java.util.*;
import java.util.function.Function;

/**
 * 二叉树
 *
 * @author anyuan
 * @since 2021-08-11 13:57
 */
public class BinaryTree {

    @ToString
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode buildBinaryTree(List<Integer> list) {
        List<TreeNode> nodeList = new ArrayList<>();
        for (Integer value : list) {
            nodeList.add(value == null ? null : new TreeNode(value));
        }
        for (int i = 1; i < nodeList.size(); i++) {
            final int parentIndex = (i - 1) / 2;
            final TreeNode parentNode = nodeList.get(parentIndex);
            final TreeNode currentNode = nodeList.get(i);
            if (parentNode != null) {
                if ((i & 1) == 0) {
                    parentNode.right = currentNode;
                } else {
                    parentNode.left = currentNode;
                }
            }
        }
        return nodeList.get(0);
    }

    public static void preOrderTraversal_recursive(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        preOrderTraversal_recursive(root.left);
        preOrderTraversal_recursive(root.right);
    }

    /**
     * 先序（前序）
     * 根->左->右
     * <p>
     * 思路：
     * 从栈顶弹出一个元素作为当前节点，然后将当前节点的右节点和左节点入栈；
     * 直到栈为空。
     * 每次弹出栈时进行打印/记录。
     *
     * @param root
     */
    public static List<TreeNode> preOrderTraversal_loop(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            final TreeNode currentNode = stack.pop();
            list.add(currentNode);
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
        return list;
    }

    /**
     * 中序遍历
     * 左->根->右
     * <p>
     * 与前序后续不太一样，
     * 中序需要先[将所有左子树压入栈，直到遇到null，再弹出栈顶元素，取栈顶元素的右子树]，再进行前边[]的操作。
     * 在从栈中弹出元素时进行输出/打印。
     *
     * @param root
     * @return
     */
    public static List<TreeNode> inOrderTraversal_loop(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                list.add(current);
                current = current.right;
            }
        }
        return list;
    }

    /**
     * 后序遍历
     * 左->右->根
     * <p>
     * 实际上与前序遍历类似，将前序遍历中右节点与左节点的入栈顺序进行调整，就能够得到根-右-左的顺序。
     * 此时，在从栈中弹出元素时，不再直接打印，而是存储到另一个栈中。
     * 等待遍历完成后，将临时栈中的所有元素弹出，就能够得到 左-右-根 的顺序了。
     *
     * @param root
     * @return
     */
    public static List<TreeNode> postOrderTraversal_loop(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Stack<TreeNode> helper = new Stack<>();
        List<TreeNode> list = new ArrayList<>();

        while (!stack.isEmpty()) {
            final TreeNode current = stack.pop();
            helper.push(current);
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        while (!helper.isEmpty()) {
            list.add(helper.pop());
        }
        return list;
    }

    /**
     * 后序遍历（使用一个栈）
     * <p>
     * TODO 有空再实现
     *
     * @param root
     * @return
     */
    public static List<TreeNode> postOrderTraversal_loop_singleStack(TreeNode root) {
        return null;
    }

    /**
     * 层序遍历
     * 每层从左到右输出
     *
     * @param root
     * @return
     */
    public static List<TreeNode> floorOrderTraversal_loop(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            final TreeNode current = queue.remove();
            list.add(current);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return list;
    }

    public static final String TREE_SERIALIZE_NULL_NODE = "#";
    public static final String TREE_SERIALIZE_DELIMITER = ",";


    /**
     * 注意，
     * 树的序列化无法通过中序方式实现。
     * 因为中序方式产生的序列化是有歧义的。
     *
     */

    /**
     * 序列化
     * 先序方式（前序）
     *
     * @param root
     * @return
     */
    public static String serializeBinaryTree_preOrder(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            final TreeNode current = stack.pop();
            if (current == null) {
                builder.append(TREE_SERIALIZE_NULL_NODE);
            } else {
                builder.append(current.value);
                stack.push(current.right);
                stack.push(current.left);
            }
            builder.append(TREE_SERIALIZE_DELIMITER);
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * 反序列化
     * 先序遍历
     *
     * @param content
     * @return
     */
    public static TreeNode deserializeBinaryTree_preOrder(String content) {
//        final List<TreeNode> nodes = StrUtil.split(content, TREE_SERIALIZE_DELIMITER)
//                .stream()
//                .map(s -> TREE_SERIALIZE_NULL_NODE.equals(s) ? null : new TreeNode(Integer.parseInt(s)))
//                .collect(Collectors.toList());
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(nodes.get(0));
//        TreeNode lastNode = nodes.get(0);
//        boolean left = true, lastNullLastLastNotNull = false;
//        for (int i = 1; i < nodes.size(); i++) {
//            final TreeNode temp = nodes.get(i);
//
//            if (left) {
//                lastNode.left = temp;
//            }
//            // 如果上一个和当前都是空，弹出栈顶的元素
//            if (lastNullLastLastNotNull && temp == null) {
//                lastNode = stack.pop();
//                lastNullLastLastNotNull = false;
//            } else {
//                if (temp != null) {
//                    stack.push(temp);
//                }
//            }
//
//            // 标识上一个节点是否是空
//            lastNullLastLastNotNull = temp == null;
//
//        }
        return null;
    }

    public static TreeNode deserializeBinaryTree_preOrder_recursive(String content) {
        if (StrUtil.isBlank(content)) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        StrUtil.split(content, TREE_SERIALIZE_DELIMITER)
                .stream()
                .map(s -> TREE_SERIALIZE_NULL_NODE.equals(s) ? null : new TreeNode(Integer.parseInt(s)))
                .forEach(queue::add);
        return deserialize(queue);
    }

    private static TreeNode deserialize(Queue<TreeNode> queue) {
        final TreeNode current = queue.remove();
        if (current == null) {
            return null;
        }
        current.left = deserialize(queue);
        current.right = deserialize(queue);
        return current;
    }

    /**
     * 序列化
     * 层序遍历
     *
     * @param root
     * @return
     */
    public static Queue<Integer> serializeBinaryTree_floorOrder(TreeNode root) {
        Queue<Integer> queue = new LinkedList<>();
        Queue<TreeNode> helper = new LinkedList<>();
        helper.add(root);
        while (!helper.isEmpty()) {
            final TreeNode current = helper.remove();
            queue.add(current == null ? null : current.value);
            if (current != null) {
                helper.add(current.left);
                helper.add(current.right);
            }
        }
        return queue;
    }

    public static TreeNode deserializeBinaryTree_floorOrder_recursive(Queue<Integer> queue) {
        /* 空判断 */

        Function<Queue<Integer>, TreeNode> nodeGenerator = integerQueue -> {
            final Integer value = integerQueue.remove();
            return value == null ? null : new TreeNode(value);
        };

        Queue<TreeNode> helper = new LinkedList<>();
        final TreeNode head = nodeGenerator.apply(queue);
        helper.add(head);
        while (!helper.isEmpty()) {
            final TreeNode current = helper.remove();
            current.left = nodeGenerator.apply(queue);
            if (current.left != null) {
                helper.add(current.left);
            }
            current.right = nodeGenerator.apply(queue);
            if (current.right != null) {
                helper.add(current.right);
            }
        }
        return head;
    }


    public static void main(String[] args) {
//        final TreeNode bt = BinaryTree.buildBinaryTree(Lists.newArrayList(
//                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
//        ));
//        BinaryTree.preOrderTraversal_recursive(bt);
//        System.out.println(BinaryTree.preOrderTraversal_loop(bt).stream()
//                .map(treeNode -> String.valueOf(treeNode.value))
//                .collect(Collectors.joining(",", "[", "]")));
//        System.out.println(BinaryTree.inOrderTraversal_loop(bt).stream()
//                .map(treeNode -> String.valueOf(treeNode.value))
//                .collect(Collectors.joining(",", "[", "]")));
//        System.out.println(BinaryTree.postOrderTraversal_loop(bt).stream()
//                .map(treeNode -> String.valueOf(treeNode.value))
//                .collect(Collectors.joining(",", "[", "]")));
//
//        System.out.println(BinaryTree.floorOrderTraversal_loop(bt).stream()
//                .map(treeNode -> String.valueOf(treeNode.value))
//                .collect(Collectors.joining(",", "[", "]")));

//        final String preOrderSerial = BinaryTree.serializeBinaryTree_preOrder(bt);
//        System.out.println(preOrderSerial);
//        final TreeNode node = BinaryTree.deserializeBinaryTree_preOrder_recursive(preOrderSerial);
//        System.out.println(BinaryTree.floorOrderTraversal_loop(node).stream()
//                .map(treeNode -> String.valueOf(treeNode.value))
//                .collect(Collectors.joining(",", "[", "]")));

        final TreeNode bt2 = BinaryTree.buildBinaryTree(Lists.newArrayList(
                1, 2, 3, null, 4, 5, null, null, null, null, 6, null, null
        ));
        final Queue<Integer> queue = BinaryTree.serializeBinaryTree_floorOrder(bt2);
        System.out.println(queue);
        final TreeNode head = BinaryTree.deserializeBinaryTree_floorOrder_recursive(queue);
        System.out.println(BinaryTree.serializeBinaryTree_floorOrder(head));

    }

}
