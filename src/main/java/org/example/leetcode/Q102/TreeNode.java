package org.example.leetcode.Q102;


public class TreeNode {
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

    public static <K> TreeNode parse(int[] array) {
        final TreeNode[] treeArray = new TreeNode[array.length];
        for (int i = 0; i < array.length; i++) {
            treeArray[i] = new TreeNode(array[i]);
        }
        if (array.length == 1) {
            return new TreeNode(array[0]);
        }
        for (int i = 1; i < array.length; i++) {
            int parentIndex = 0;
            if ((i - 1) % 2 == 0) {
                parentIndex = (i - 1) / 2;
                treeArray[parentIndex].left = treeArray[i];
            }
            if ((i - 2) % 2 == 0) {
                parentIndex = (i - 2) / 2;
                treeArray[parentIndex].right = treeArray[i];
            }
        }
        return treeArray[0];
    }    
}