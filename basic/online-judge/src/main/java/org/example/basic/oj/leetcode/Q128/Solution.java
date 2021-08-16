package org.example.basic.oj.leetcode.Q128;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-08-14 21:27
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final UnionFindSet unionFindSet = new UnionFindSet(nums);
        return unionFindSet.sizeMap.values().stream().mapToInt(value -> value).max().getAsInt();
    }

    static class UnionFindSet {
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashMap<Node, Node> parents = new HashMap<>();
        HashMap<Node, Integer> sizeMap = new HashMap<>();

        public UnionFindSet(int[] values) {
            for (int value : values) {
                // 过滤重复节点
                if (!nodes.containsKey(value)) {
                    final Node current = new Node(value);
                    nodes.put(value, current);
                    parents.put(current, current);
                    sizeMap.put(current, 1);
                    if (nodes.containsKey(value - 1)) {
                        final Node smaller = nodes.get(value - 1);
                        union(current, smaller);
                    }
                    if (nodes.containsKey(value + 1)) {
                        final Node bigger = nodes.get(value + 1);
                        union(current, bigger);
                    }
                }
            }
        }

        public void union(Node node1, Node node2) {
            final Node ancestor1 = findAncestor(node1);
            final Node ancestor2 = findAncestor(node2);
            if (ancestor1 != ancestor2) {
                final Integer size1 = sizeMap.get(ancestor1);
                final Integer size2 = sizeMap.get(ancestor2);
                Node longer = size1 > size2 ? ancestor1 : ancestor2;
                Node shorter = longer == ancestor1 ? ancestor2 : ancestor1;
                parents.put(shorter, longer);
                sizeMap.put(longer, size1 + size2);
                sizeMap.remove(shorter);
            }
        }

        public Node findAncestor(Node node) {
            List<Node> ancestors = new ArrayList<>();
            Node current = node;
            while (current != parents.get(current)) {
                ancestors.add(current);
                current = parents.get(current);
            }
            for (Node ancestor : ancestors) {
                parents.put(ancestor, current);
            }
            return current;
        }
    }

    static class Node {
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
