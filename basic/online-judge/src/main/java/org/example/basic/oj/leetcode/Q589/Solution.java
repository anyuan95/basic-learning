package org.example.basic.oj.leetcode.Q589;

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * 前序遍历
     * DLR
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        final List<Integer> answer = new ArrayList<>();
        dfs(root, answer);
        return answer;
    }

    private void dfs(Node cur, List<Integer> answer) {
        if (null == cur) {
            return;
        }
        answer.add(cur.val);
        if (cur.children != null && !cur.children.isEmpty()) {
            for (Node child : cur.children) {
                dfs(child, answer);
            }
        }
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
