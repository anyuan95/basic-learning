package org.example.basic.oj.zuo.graph;

import org.example.basic.oj.zuo.graph.model.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 图的宽度优先遍历
 * <p>
 * 宽度优先遍历：以离初状态的距离为序进行遍历。
 *
 * @author anyuan
 * @since 2021-08-16 09:55
 */
public class BFS {

    public void bfs(Node startNode) {
        if (startNode == null) {
            return;
        }
        HashSet<Node> searchedNodes = new HashSet<Node>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(startNode);
        while (!queue.isEmpty()) {
            final Node current = queue.remove();
            searchedNodes.add(current);
            System.out.print(current.getValue() + "->");
            queue.addAll(current.getNexts().stream().filter(node -> !searchedNodes.contains(node)).collect(Collectors.toList()));
        }
    }


}
