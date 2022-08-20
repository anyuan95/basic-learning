package org.example.basic.oj.leetcode.Q429;

import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        final Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        final List<List<Integer>> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            final int size = queue.size();
            final List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                final Node poll = queue.poll();
                list.add(poll.val);
                if (poll.children != null) {
                    queue.addAll(poll.children);
                }
            }
            answer.add(list);
        }
        return answer;
    }

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
}
