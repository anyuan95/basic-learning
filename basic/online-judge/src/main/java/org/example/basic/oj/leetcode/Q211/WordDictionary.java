package org.example.basic.oj.leetcode.Q211;

import java.util.Dictionary;

/**
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。
 * word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 * <p>
 * <p>
 * 思路：trie树+dfs
 */
class WordDictionary {

    private static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEnd = false;
        }

        public void insert(String word) {
            TrieNode curr = this;
            final char[] chars = word.toCharArray();
            for (char aChar : chars) {
                if (curr.children[aChar - 'a'] == null) {
                    curr.children[aChar - 'a'] = new TrieNode();
                }
                curr = curr.children[aChar - 'a'];
            }
            curr.isEnd = true;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        root.insert(word);
    }

    // fs
    public boolean search(String word) {
        final char[] chars = word.toCharArray();
        return dfs(root, chars, 0);
    }

    private boolean dfs(TrieNode root, char[] chars, int charIndex) {
        if (charIndex == chars.length) {
            return root.isEnd;
        }
        if (chars[charIndex] == '.') {
            for (int i = 0; i < root.children.length; i++) {
                if (root.children[i] != null && dfs(root.children[i], chars, charIndex + 1)) {
                    return true;
                }
            }
        } else {
            // a-z
            if (root.children[chars[charIndex] - 'a'] == null) {
                return false;
            }
            return dfs(root.children[chars[charIndex] - 'a'], chars, charIndex + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        final WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");
        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));
    }
}