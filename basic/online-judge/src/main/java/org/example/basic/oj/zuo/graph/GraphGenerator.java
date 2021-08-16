package org.example.basic.oj.zuo.graph;

import org.example.basic.oj.zuo.graph.model.Edge;
import org.example.basic.oj.zuo.graph.model.Graph;
import org.example.basic.oj.zuo.graph.model.Node;

/**
 * 图的常见表示形式：
 * 1.邻接表法
 * 2.邻接矩阵法
 * 3......
 *
 * @author anyuan
 * @since 2021-08-16 09:14
 */
public class GraphGenerator {

    /**
     * 邻接表 -> 图对象
     *
     * @param matrix 邻接表。
     *               高度为边个数，宽度为3的数组：[weight, from节点上面的值，to节点上面的值]
     *               例如，metric中的一行[5 , 0 , 7]表示[边权重=5，边来自值为0的节点，边指向值为7的节点]
     * @return
     */
    public static Graph generateGraphFromAdjacencyList(int[][] matrix) {
        final Graph graph = new Graph();
        for (int[] metric : matrix) {
            final int weight = metric[0];
            final int from = metric[1];
            final int to = metric[2];
            final Node fromNode = graph.getNodes().computeIfAbsent(from, value -> new Node(from));
            final Node toNode = graph.getNodes().computeIfAbsent(to, value -> new Node(to));
            final Edge edge = new Edge(weight, fromNode, toNode);
            graph.getEdges().add(edge);
            fromNode.addEdge(edge);
            toNode.addEdge(edge);
        }
        return graph;
    }

    /**
     * 邻接矩阵 -> 图对象
     *
     * @param matrix 邻接矩阵，即通过二维数组m[i][j]进行表示。
     *               m[i][j]的值表示从i节点指向j节点的边的权重值。
     *               若为特殊值，则表示没有从i指向j的边。
     * @return
     */
    public static Graph generateGraphFromAdjacencyMatrix(int[][] matrix) {
        /* TODO */
        return null;
    }

    /**
     * @param args
     */

    public static void main(String[] args) {
        int[][] metrics = new int[][]{
                {5, 0, 7},
                {3, 0, 1}
        };
        final Graph graph = generateGraphFromAdjacencyList(metrics);
        System.out.println(graph);
    }
}
