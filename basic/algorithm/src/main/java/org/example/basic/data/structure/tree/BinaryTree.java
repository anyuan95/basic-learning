package org.example.basic.data.structure.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-03-15 09:43
 */
public class BinaryTree<T> {

    public T data;
    public BinaryTree<T> left;
    public BinaryTree<T> right;
    public BinaryTree<T> parent;

    public BinaryTree() {
    }

    public BinaryTree(T data) {
        this.data = data;
    }

    public BinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * DLR
     *
     * @param binaryTree
     */
    public static void preOrderPrint(BinaryTree<?> binaryTree) {
        if (binaryTree == null) {
            return;
        }
        System.out.print(binaryTree.data + " -> ");
        preOrderPrint(binaryTree.left);
        preOrderPrint(binaryTree.right);
    }

    /**
     * LDR
     *
     * @param binaryTree
     */
    public static void middleOrderPrint(BinaryTree<?> binaryTree) {
        if (binaryTree == null) {
            return;
        }
        middleOrderPrint(binaryTree.left);
        System.out.print(binaryTree.data + " -> ");
        middleOrderPrint(binaryTree.right);
    }

    /**
     * LRD
     *
     * @param binaryTree
     */
    public static void postOrderPrint(BinaryTree<?> binaryTree) {
        if (binaryTree == null) {
            return;
        }
        postOrderPrint(binaryTree.left);
        postOrderPrint(binaryTree.right);
        System.out.print(binaryTree.data + " -> ");
    }

    /**
     * 层序
     * 队列
     *
     * @param binaryTree
     */
    public static void floorOrderPrint(BinaryTree<?> binaryTree) {
        Queue<BinaryTree<?>> queue = new ArrayDeque();
        queue.offer(binaryTree);
        while (!queue.isEmpty()) {
            final BinaryTree<?> tree = queue.poll();
            System.out.print(tree.data + " -> ");
            if (tree.left != null) {
                queue.offer(tree.left);
            }
            if (tree.right != null) {
                queue.offer(tree.right);
            }
        }
    }

    public static int height(BinaryTree<?> binaryTree) {
        if (binaryTree == null) {
            return 0;
        }
        return Math.max(height(binaryTree.left), height(binaryTree.right)) + 1;
    }

    /**
     * 将数组转化成二叉树
     * 按照数组下标，逐个进行处理：先找到当前遍历的节点的parent，然后为该parent设置与当前节点的关系
     *
     * @param array
     * @param <K>
     * @return
     */
    public static <K> BinaryTree<K> parse(K[] array) {
        final BinaryTree<K>[] treeArray = new BinaryTree[array.length];
        for (int i = 0; i < array.length; i++) {
            treeArray[i] = new BinaryTree<>(array[i]);
        }
        if (array.length == 1) {
            return new BinaryTree<>(array[0]);
        }
        for (int i = 1; i < array.length; i++) {
            int parentIndex = 0;
            if ((i - 1) % 2 == 0) {
                parentIndex = (i - 1) / 2;
                treeArray[i].parent = treeArray[parentIndex];
                treeArray[parentIndex].left = treeArray[i];
            }
            if ((i - 2) % 2 == 0) {
                parentIndex = (i - 2) / 2;
                treeArray[i].parent = treeArray[parentIndex];
                treeArray[parentIndex].right = treeArray[i];
            }
        }
        return treeArray[0];
    }

    public static void main(String[] args) {
        final BinaryTree tree = parse(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        preOrderPrint(tree);
        System.out.println();
        middleOrderPrint(tree);
        System.out.println();
        postOrderPrint(tree);
        System.out.println();
        floorOrderPrint(tree);
        System.out.println();

        System.out.println(height(parse(new Integer[]{0})));
        System.out.println(height(parse(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9})));
        System.out.println(height(parse(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14})));
        System.out.println(height(parse(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15})));
    }

}
