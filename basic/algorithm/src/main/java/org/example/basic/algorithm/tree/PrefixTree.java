package org.example.basic.algorithm.tree;

/**
 * 前缀树
 * TrieTree
 *
 * @author anyuan
 * @since 2021-08-06 10:55
 */
public class PrefixTree {

    public static class Node {
        int pass;
        int end;
        Node[] nextNodes;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nextNodes = new Node[26];
        }
    }

    private Node root;

    public PrefixTree() {
        this.root = new Node();
    }

    public PrefixTree(String[] contents) {
        /* TODO */
    }

    public void insert(String content) {
        final char[] chars = content.toCharArray();
        Node currentNode = root;
        for (char aChar : chars) {
            currentNode = currentNode.nextNodes[aChar - 'a'];
            if (currentNode == null) {
                currentNode = new Node();
                currentNode.nextNodes[aChar - 'a'] = currentNode;
            }
            currentNode.pass++;
        }
        currentNode.end++;
    }

    public boolean delete(String content) {
        /* 需要先进行检查，检查确认存在之后才进行删除。
         * 否则可能出现删除了一部分之后发现并不存在（无法回退）的情况。*/
        if (count(content) < 0) {
            return false;
        }
        final char[] chars = content.toCharArray();
        Node currentNode = root;
        currentNode.pass--;
        for (char aChar : chars) {
            currentNode = currentNode.nextNodes[aChar - 'a'];
            if (currentNode == null || currentNode.pass == 0) {
                return false;
            }
            currentNode.pass--;
            // 如果当前节点下面只有这一个字符序列，则后边的内容都不用遍历了。直接交给GC清理。
            // 循环提前结束
            if (currentNode.pass == 0) {
                currentNode.nextNodes[aChar - 'a'] = null;
                return true;
            }
        }
        currentNode.end--;
        return true;
    }

    public int count(String content) {
        final char[] chars = content.toCharArray();
        Node currentNode = root;
        for (char aChar : chars) {
            currentNode = currentNode.nextNodes[aChar - 'a'];
            if (currentNode == null) {
                return -1;
            }
        }
        return currentNode.end;
    }

    public int countPrefix(String prefix) {
        final char[] chars = prefix.toCharArray();
        Node currentNode = root;
        for (char aChar : chars) {
            currentNode = currentNode.nextNodes[aChar - 'a'];
            if (currentNode == null) {
                return -1;
            }
        }
        return currentNode.pass;
    }


}
