package org.example.basic.oj.zuo.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anyuan
 * @since 2021-08-14 20:23
 */
public class UnionFindSet<V> {

    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    private Map<V, Node<V>> nodes = new HashMap<>();
    private Map<Node<V>, Node<V>> parents = new HashMap<>();
    private Map<Node<V>, Integer> sizeMap = new HashMap<>();

    public UnionFindSet(List<V> values) {
        for (V value : values) {
            final Node<V> node = new Node<>(value);
            nodes.put(value, node);
            parents.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    /**
     * 查询指定节点m最顶级的祖先n。
     * 会将所有从m到n遍历过程中的节点，都改为n的直接子节点。
     *
     * @return
     */
    public Node<V> findFinalAncestor(V value) {
        List<Node<V>> ancestors = new ArrayList<>();
        Node<V> current = nodes.get(value);
        while (current != parents.get(current)) {
            ancestors.add(current);
            current = parents.get(current);
        }
        for (Node<V> ancestor : ancestors) {
            parents.put(ancestor, current);
        }
        return current;
    }

    /**
     * 判断两个value是否在同一个子集合中
     *
     * @param value1
     * @param value2
     * @return
     */
    public boolean isInSameSet(V value1, V value2) {
        return findFinalAncestor(value1) == findFinalAncestor(value2);
    }

    /**
     * 将两个集合进行合并
     * 最终的内容会放到二者所属子集合中的一个
     *
     * @param value1
     * @param value2
     */
    public void union(V value1, V value2) {
        // 先判断二者长短，然后将短的接到长的下面
        final Node<V> ancestor1 = findFinalAncestor(value1);
        final Node<V> ancestor2 = findFinalAncestor(value2);
        if (ancestor1 != ancestor2) {
            final Integer size1 = sizeMap.get(ancestor1);
            final Integer size2 = sizeMap.get(ancestor2);
            Node<V> shorterAncestor = size1 > size2 ? ancestor2 : ancestor1;
            Node<V> longerAncestor = shorterAncestor == ancestor1 ? ancestor2 : ancestor1;
            // 注意！！！ 这里一定要将短的接到长的下面。TODO 原因？
            parents.put(shorterAncestor, longerAncestor);
            sizeMap.put(longerAncestor, size1 + size2);
            sizeMap.remove(shorterAncestor);
        }
    }

}
