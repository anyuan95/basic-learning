package org.example.basic.oj.leetcode.Q677;

class MapSum {
    private TrieNode root;

    public MapSum() {
        this.root = new TrieNode();
    }

    public void insert(String key, int val) {
        // 先找到当前的key对应的值，ON
        final TrieNode node = get(key);
        final int oldVal = node == null ? 0 : node.value;
        // 然后变往后找边减掉value，并加上val
        final char[] chars = key.toCharArray();
        TrieNode curr = root;
        for (char aChar : chars) {
            if (curr.children[aChar - 'a'] == null) {
                curr.children[aChar - 'a'] = new TrieNode();
            }
            curr = curr.children[aChar - 'a'];
            curr.sum = curr.sum - oldVal + val;
        }
        curr.value = val;
    }

    private TrieNode get(String key) {
        final char[] chars = key.toCharArray();
        TrieNode curr = root;
        for (char aChar : chars) {
            if (curr.children[aChar - 'a'] == null) {
                return null;
            }
            curr = curr.children[aChar - 'a'];
        }
        return curr;
    }

    public int sum(String prefix) {
        final TrieNode node = get(prefix);
        return node == null ? 0 : node.sum;
    }

    static class TrieNode {
        private TrieNode[] children = new TrieNode[26];
        private int sum;
        private int value;
    }

    public static void main(String[] args) {
        final MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }
}
