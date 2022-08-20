package org.example.basic.oj.leetcode.Q1305;

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * 反复查找两棵树
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
       final List<Integer> answer = new ArrayList<>();
       final List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        final int m = list1.size(), n = list2.size();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (list1.get(i) < list2.get(j)) {
                answer.add(list1.get(i));
                i++;
            } else {
                answer.add(list2.get(j));
                j++;
            }
        }
        while (i < m) {
            answer.add(list1.get(i));
            i++;
        }
        while (j < n) {
            answer.add(list2.get(j));
            j++;
        }
        return answer;
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (null == root) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
