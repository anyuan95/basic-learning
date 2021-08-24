package org.example.basic.oj.leetcode.Q787_unfinished;

import java.util.*;

/**
 * 求从A到B最便宜的航班总价，要求航班次数在k次以内
 *
 * TODO 没完成，以为是简单的dijkstra，但最基础的dijkstra会导致丢失可能性。
 * 看题解，使用SPFA更好。
 *
 * @author anyuan
 * @since 2021-08-24 14:09
 */
class Solution_dijkstra_unfinished {

    /**
     * 思路：
     * 做一个反向的图，即把原来图中的next改成previous，然后从B节点开始，沿着所有previous往下走，走k次，返回这时到A的最小权重
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 构造图
        // 倒过来，flights里的from和to倒置
        HashMap<Integer, Node> nodeMap = new HashMap<>();
        for (int[] flight : flights) {
            final int fromNo = flight[1];
            final int toNo = flight[0];
            final int price = flight[2];
            final Node fromNode = nodeMap.computeIfAbsent(fromNo, integer -> new Node(fromNo));
            final Node toNode = nodeMap.computeIfAbsent(toNo, integer -> new Node(toNo));
            final Edge edge = new Edge(fromNode, toNode, price);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
        }
        // 从B点开始到其他所有城市的最小花费
        HashMap<Node, Integer> minCosts = new HashMap<>();
        HashSet<Node> selectedNodes = new HashSet<>();
        minCosts.put(nodeMap.get(dst), 0);

        // 先从dst开始，然后每次从当前最小的开始
        Node currentNode = nodeMap.get(dst);
        for (int i = 0; i < k + 1; i++) {
            for (Edge edge : currentNode.edges) {
                Node toNode = edge.to;
                if (!minCosts.containsKey(toNode)) {
                    minCosts.put(toNode, minCosts.get(currentNode) + edge.weight);
                } else {
                    minCosts.put(toNode, Math.min(minCosts.get(toNode), minCosts.get(currentNode) + edge.weight));
                }
            }
            // 找到最小cost的node
            selectedNodes.add(currentNode);
            currentNode = getMinUnselectedNode(minCosts, selectedNodes);

            if (currentNode == null) {
                // 返回null说明所有节点都已经填充完成了
                return -1;
            }
            if (currentNode.no == src) {
                return minCosts.get(currentNode);
            }
        }
        return minCosts.getOrDefault(nodeMap.get(src), -1);
    }

    private Node getMinUnselectedNode(HashMap<Node, Integer> minCosts, HashSet<Node> selectedNodes) {
        return minCosts.entrySet().stream()
                .filter(entry -> !selectedNodes.contains(entry.getKey()))
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    static class Node {
        int no;
        List<Node> nexts;
        List<Edge> edges;

        public Node(int no) {
            this.no = no;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    '}';
        }
    }

    static class Edge {
        int weight;
        Node from;
        Node to;

        public Edge(Node from, Node to, int weight) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }

    public static void main(String[] args) {
        final Solution_dijkstra_unfinished solution = new Solution_dijkstra_unfinished();
        int[][] flights = new int[][]{
//                {4,1,1},{1,2,3},{0,3,2},{0,4,10},{3,1,1},{1,4,3}
//                {1,0,5}
                {0,3,7},{4,5,3},{6,4,8},{2,0,10},{6,5,6},{1,2,2},{2,5,9},{2,6,8},{3,6,3},{4,0,10},{4,6,8},{5,2,6},{1,4,3},{4,1,6},{0,5,10},{3,1,5},{4,3,1},{5,4,10},{0,1,6}
        };
//        System.out.println(solution.findCheapestPrice(5, flights, 2, 1, 1));
//        System.out.println(solution.findCheapestPrice(2, flights, 0, 1, 1));
        System.out.println(solution.findCheapestPrice(7, flights, 2, 4, 1));
    }

}
