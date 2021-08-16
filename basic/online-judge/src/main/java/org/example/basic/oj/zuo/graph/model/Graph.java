package org.example.basic.oj.zuo.graph.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图结构
 *
 * @author anyuan
 * @since 2021-08-16 08:55
 */
@Getter
public class Graph {
    private HashMap<Integer, Node> nodes;
    private HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
