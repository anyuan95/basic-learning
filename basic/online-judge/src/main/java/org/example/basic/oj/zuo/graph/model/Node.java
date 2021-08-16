package org.example.basic.oj.zuo.graph.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 图中的点结构
 *
 * @author anyuan
 * @since 2021-08-16 08:56
 */
@Getter
public class Node {

    private int value;
    /**
     * 出度
     */
    private int inCount;
    /**
     * 入度
     */
    private int outCount;

    /**
     * 当前节点直接指向的节点
     */
    private List<Node> nexts;
    /**
     * 所有从当前节点出发的边
     */
    private List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.inCount = 0;
        this.outCount = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        if (edge.getTo() == this) {
            this.inCount++;
        }
        if (edge.getFrom() == this) {
            this.edges.add(edge);
            this.nexts.add(edge.getTo());
            this.outCount++;
        }
    }
}
