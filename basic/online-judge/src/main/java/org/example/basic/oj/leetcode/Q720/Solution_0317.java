package org.example.basic.oj.leetcode.Q720;

import java.util.Arrays;

class Solution_0317 {
    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> (a + b).compareTo(b + a));
        final DictNode root = new DictNode();
        for (String word : words) {
            putWord(root, word);
        }
        dfs(root, 0);
        return longestWord;
    }

    int maxLength = 0;
    String longestWord = "";

    private void dfs(DictNode root, int depth) {
        if (root == null || (depth > 0 && !root.isEnd)) {
            return;
        }
        if (depth > maxLength) {
            maxLength = depth;
            longestWord = root.word;
        }
        for (DictNode child : root.children) {
            dfs(child, depth + 1);
        }
    }

    private void putWord(DictNode root, String word) {
        final char[] chars = word.toCharArray();
        for (char aChar : chars) {
            if (root.children[aChar - 'a'] == null) {
                root.children[aChar - 'a'] = new DictNode();
            }
            root = root.children[aChar - 'a'];
        }
        root.word = word;
        root.isEnd = true;
    }

    private static class DictNode {
        DictNode[] children = new DictNode[26];
        String word;
        boolean isEnd;
    }
}
