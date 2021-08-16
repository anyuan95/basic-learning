package org.example.basic.oj.zuo.graph.mst;

import org.example.basic.oj.zuo.graph.model.Edge;
import org.example.basic.oj.zuo.graph.model.Graph;
import org.example.basic.oj.zuo.graph.model.Node;

import java.util.*;

/**
 * 克鲁斯卡尔算法（Kruskal）
 * <p>
 * 在无向图中，找到能够连通所有节点的路径长度总和最小值，或者返回路径集合。（构成连通图的路径长度和）（最小生成树的权值和）
 *
 * @author anyuan
 * @since 2021-08-16 16:43
 */
public class Kruskal {

    /**
     * 使用并查集方式解决
     * <p>
     * 将所有的边长度按照从小到大排序，逐个进行处理：
     * - 如果加入当前边后会出现环，则不要；
     * - 如果加入当前变后不会出现环，则添加当前边；
     * 直到构成连通图为止。
     *
     * @return
     */
    private Set<Edge> kruskalMST(Graph graph) {
        // 将图的所有边根据权重放到小顶堆中
        PriorityQueue<Edge> minEdgeHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        minEdgeHeap.addAll(graph.getEdges());
        // 每次从小顶堆中取出长度最短的边，将边两侧的节点所在的并查集进行合并
        final UnionFindSet unionFindSet = new UnionFindSet(graph.getNodes().values());
        Set<Edge> answer = new HashSet<>();
        /* FIXME 条件可能需要调整。如果题目给的图是一个[所有节点都可连通]的图，则通过并查集中集合数量==1即可判断；
            但如果题目提供的图是一个森林，则可能需要将所有边遍历完（才能得到多个相互之间无关联的连通图） */
        while (unionFindSet.size != 1 && minEdgeHeap.size() != 0) {
            final Edge weightSmallestEdge = minEdgeHeap.poll();
            final Node from = weightSmallestEdge.getFrom();
            final Node to = weightSmallestEdge.getTo();
            if (!unionFindSet.isSameSet(from, to)) {
                unionFindSet.union(from, to);
                answer.add(weightSmallestEdge);
            }
        }
        return answer;
    }

    static class UnionFindSet {
        private HashMap<Node, Node> parents;
        private HashMap<Node, Integer> sizes;
        private Node[] helper;
        private int size;

        public boolean isSameSet(Node node1, Node node2) {
            return findAncestor(node1) == findAncestor(node2);
        }

        public UnionFindSet(Collection<? extends Node> nodes) {
            parents = new HashMap<>();
            sizes = new HashMap<>();
            helper = new Node[nodes.size()];
            size = 0;
            for (Node node : nodes) {
                parents.put(node, node);
                sizes.put(node, 1);
                size++;
            }
        }

        public Node findAncestor(Node node) {
            int helperIndex = 0;
            while (node != parents.get(node)) {
                helper[helperIndex++] = node;
                node = parents.get(node);
            }
            while (--helperIndex >= 0) {
                parents.put(helper[helperIndex], node);
            }
            return node;
        }

        public void union(Node node1, Node node2) {
            final Node ancestor1 = findAncestor(node1);
            final Node ancestor2 = findAncestor(node2);
            if (ancestor1 != ancestor2) {
                final Integer size1 = sizes.get(ancestor1);
                final Integer size2 = sizes.get(ancestor2);
                Node longer = size1 > size2 ? ancestor1 : ancestor2;
                Node shorter = longer == ancestor1 ? ancestor2 : ancestor1;
                parents.put(shorter, longer);
                sizes.put(longer, size1 + size2);
                sizes.remove(shorter);
                size--;
            }
        }
    }
}
