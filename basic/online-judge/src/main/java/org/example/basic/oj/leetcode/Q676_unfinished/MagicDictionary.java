package org.example.basic.oj.leetcode.Q676_unfinished;

/**
 * 思路：trie树
 *
 * 未通过
 */
class MagicDictionary {
    private TrieNode root = new TrieNode();

    public MagicDictionary() {

    }
    
    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            addWord(s);
        }
    }
    
    public boolean search(String searchWord) {
        TrieNode curr = root;
        final char[] chars = searchWord.toCharArray();
        boolean hasDiff = false;
        for (int i = 0; i < chars.length; i++) {
            final char c = chars[i];
            if (curr.children[c - 'a'] == null) {
                if (hasDiff) {
                    return false;
                }
                hasDiff = true;
                // 如果当前节点不同，那就尝试找接下来的所有节点
                // TODO 考虑当前就是最后一个节点的情况
                for (int k = 0; k < 26; k++) {
                    // 跳过当前节点
                    if (null != curr.children[k] && dfs(curr.children[k], chars, i + 1, chars.length - 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                // 如果相同，就继续往下找
                curr = curr.children[c - 'a'];
            }
        }
        return hasDiff;
    }

    private boolean dfs(TrieNode root, char[] chars, int start, int end) {
        TrieNode curr = root;
        for (int i = start; i <= end; i++) {
            final char c = chars[i];
            if (null == curr.children[c - 'a']) {
                // 这里就不能再有不同节点了
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return curr.isEnd;
    }

    private void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(magicDictionary.search("hello")); // 返回 False
        System.out.println(magicDictionary.search("hhllo")); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
        System.out.println(magicDictionary.search("hell")); // 返回 False
        System.out.println(magicDictionary.search("leetcoded")); // 返回 False
    }
}