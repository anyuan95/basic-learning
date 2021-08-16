package org.example.basic.oj.zuo.graph.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 图中的线段结构
 *
 * @author anyuan
 * @since 2021-08-16 08:56
 */
@Getter
public class Edge {
    /**
     * 边长（权重）
     */
    private int weight;
    private Node from;
    private Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
