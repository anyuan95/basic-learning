package org.example.basic.oj.leetcode.Q559;

import java.util.List;

/**
 * @author anyuan
 * @date 2021-11-21 23:24
 */
class Solution {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        } else if (root.children == null || root.children.isEmpty()) {
            return 1;
        } else {
            int maxDepth = 0;
            for (Node child : root.children) {
                maxDepth = Math.max(maxDepth, maxDepth(child) + 1);
            }
            return maxDepth;
        }
    }
}
