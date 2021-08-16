package org.example.basic.oj.zuo.graph;

import org.example.basic.oj.zuo.graph.model.Graph;
import org.example.basic.oj.zuo.graph.model.Node;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先遍历
 * <p>
 * 沿着某一条路走到底，直到遇到了遍历过的节点或是没有下一个节点为止。
 * 然后对当前节点的上一节点继续进行此操作。
 *
 * @author anyuan
 * @since 2021-08-16 10:21
 */
public class DFS {

    public void dfs1(Node start) {
        Stack<Node> stack = new Stack<>();
        HashSet<Node> searchedNodes = new HashSet<>();
        Node current = start;
        while (!stack.isEmpty() || current != null) {
            if (current == null) {
                current = stack.pop();
            } else {
                if (!searchedNodes.contains(current)) {
                    searchedNodes.add(current);
                    System.out.print((char) ('a' + current.getValue()) + "->");

                    final List<Node> nexts = current.getNexts();
                    for (int i = nexts.size() - 1; i >= 0; i--) {
                        // 倒着将nexts中的元素放压入栈中，以此保证顺序
                        if (!searchedNodes.contains(nexts.get(i))) {
                            stack.push(nexts.get(i));
                        }
                    }
                }
                current = null;
            }
        }
    }

    public void dfs2(Node start) {
        Stack<Node> stack = new Stack<>();
        HashSet<Node> searchedNodes = new HashSet<>();

        stack.push(start);
        searchedNodes.add(start);
        System.out.println((char) ('a' + start.getValue()) + "->");

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            List<Node> nexts = current.getNexts();
            for (Node next : nexts) {
                if (!searchedNodes.contains(next)) {
                    // 把当前节点重新放进去，等下次再使用
                    stack.push(current);
                    stack.push(next);
                    searchedNodes.add(next);
                    // 由于set中存的是所有已经输出过的元素，所以只要在向set中add值时输出就可以了
                    System.out.print((char) ('a' + next.getValue()) + "->");
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        final DFS dfs = new DFS();
        final int[][] matrix = new int[][]{
                {0, 0, 1},
                {0, 0, 2},
                {0, 0, 6},
                {0, 1, 4},
                {0, 1, 2},
                {0, 2, 3},
                {0, 4, 5},
                {0, 5, 2},
                {0, 3, 2}
        };
        final Graph graph = GraphGenerator.generateGraphFromAdjacencyList(matrix);
        final Node node = graph.getNodes().get(0);
        dfs.dfs1(node);
        System.out.println();
        dfs.dfs2(node);
    }
}
