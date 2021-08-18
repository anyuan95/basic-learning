package org.example.basic.oj.leetcode.Q720;

/**
 * 字典中最长的单词
 *
 * @author anyuan
 * @since 2021-08-18 15:25
 */
class Solution {

    /**
     * 思路：做一棵trie树，为了方便，每个节点记录[是否是单词结尾]和[当前单词]
     * 把所有字符串插入到树中，然后用dfs去找出最深的、且路径上节点都[是单词结尾]的一个节点，返回该节点中[当前单词]
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        // 做一棵字典树
        final TrieNode trieNode = new TrieNode();
        for (String word : words) {
            trieNode.insert(word);
        }
        findMaxLengthNode(trieNode, 0);
        return longestWord;
    }

    int maxLength = 0;
    String longestWord = "";

    private void findMaxLengthNode(TrieNode trie, int depth) {
        if (trie == null || (depth > 0 && !trie.isEnd)) {
            return;
        }
        if (depth > maxLength) {
            maxLength = depth;
            longestWord = trie.word;
        }
        for (TrieNode child : trie.children) {
            if (child != null) {
                findMaxLengthNode(child, depth + 1);
            }
        }
    }

    static class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        String word;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            final char[] chars = word.toCharArray();
            TrieNode currentNode = this;
            for (char aChar : chars) {
                if (currentNode.children[aChar - 'a'] == null) {
                    currentNode.children[aChar - 'a'] = new TrieNode();
                }
                currentNode = currentNode.children[aChar - 'a'];
            }
            currentNode.isEnd = true;
            currentNode.word = word;
        }
    }
}
