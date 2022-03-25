package org.example.basic.oj.leetcode.Q590;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @date 2022-03-12 23:17
 */
class Solution {
    /**
     * 后续遍历，LRD
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        final List<Integer> answer = new ArrayList<>();
        process(root, answer);
        return answer;
    }

    private void process(Node current, List<Integer> answer) {
        if (current == null) {
            return;
        }
        // LRD
        for (Node child : current.children) {
            process(child, answer);
        }
        answer.add(current.val);
    }


   private static class Node {
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
    };
}
