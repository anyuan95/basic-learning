package org.example.basic.oj.leetcode.Q212;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import org.example.basic.oj.zuo.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-16 22:44
 */
class Solution_trie {

    /**
     * 加上Trie，去掉一点重复的搜索
     *
     * 思路：把所有要找的词加到trie树里。从数组[0,0]位置开始，逐个进行dfs，与trie中的节点做对比。
     *
     * 72 ms
     *
     * TODO 或许把trie中处理完的单词剪掉会更好，这样能保证越往后遍历，trie越小
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> answer = new ArrayList<>();
        final TrieNode trieNode = new TrieNode();
        for (String word : words) {
            trieNode.insert(word);
        }

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                process(board, trieNode, answer, x, y);
            }
        }
        return answer;
    }

    private void process(char[][] board, TrieNode trieNode, List<String> answer, int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] > 'z') {
            return;
        }
        TrieNode child = trieNode.children[board[x][y] - 'a'];
        if (child == null) {
            return;
        } else if (child.isEnd) {
            // 如果当前节点是一个词的终点了，那进行记录，然后调成false避免重复
            child.isEnd = false;
            answer.add(child.word);
        }

        board[x][y] += 256;
        // 上下左右
        process(board, child, answer, x - 1, y);
        process(board, child, answer, x + 1, y);
        process(board, child, answer, x, y - 1);
        process(board, child, answer, x, y + 1);
        board[x][y] -= 256;
    }

    /**
     * 可以再做优化，把isEnd属性去掉。因为只有isEnd=true的节点，word才不会为空。
     */
    private static class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        String word;

        public TrieNode() {
            this.isEnd = false;
            this.children = new TrieNode[26];
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            final char[] chars = word.toCharArray();
            TrieNode current = this;
            for (char aChar : chars) {
                if (current.children[aChar - 'a'] == null) {
                    current.children[aChar - 'a'] = new TrieNode();
                }
                current = current.children[aChar - 'a'];
            }
            current.isEnd = true;
            current.word = word;
        }
    }
}
