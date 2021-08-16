package org.example.basic.oj.zuo.graph.mst;

import org.example.basic.oj.zuo.graph.model.Edge;
import org.example.basic.oj.zuo.graph.model.Node;

import java.util.HashMap;

/**
 * 狄杰斯特拉算法
 * Dijkstra
 * <p>
 * 给定一个有向图，指定其中一个节点，求该节点到所有其他节点的最短距离。
 * <p>
 * 算法限制：要求不能有权重和为负数的环。（因为会导致沿着该环无限走，会使总权重无限小）
 *
 * @author anyuan
 * @since 2021-08-16 17:28
 */
public class Dijkstra {

    public static HashMap<Node, Integer> dijkstra(Node from, int size) {
        HashMap<Node, Integer> answer = new HashMap<>();
        final NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(from, 0);

        while (!nodeHeap.isEmpty()) {
            final MinDistance minDistance = nodeHeap.pop();
            final Node node = minDistance.node;
            final int distance = minDistance.distance;

            for (Edge edge : node.getEdges()) {
                final Node toNode = edge.getTo();
                nodeHeap.addOrUpdateOrIgnore(toNode, edge.getWeight() + distance);
                answer.put(toNode, distance);
            }
        }
        return answer;
    }

    static class NodeHeap {
        private Node[] nodes;
        private HashMap<Node, Integer> nodeIndexMap;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            this.size = 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (nodeIndexMap.containsKey(node) && nodeIndexMap.get(node) != -1) {
                // 存在，更新，上浮（只会不变或减小，所以一定上浮）
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(nodeIndexMap.get(node));
            }
            if (!nodeIndexMap.containsKey(node)) {
                // 不存在，新增，上浮
                nodes[size] = node;
                nodeIndexMap.put(node, size);
                distanceMap.put(node, distance);
                heapInsert(size);
                size++;
            }
        }

        /**
         * 弹出堆顶，即最小值
         *
         * @return
         */
        public MinDistance pop() {
            MinDistance minDistance = new MinDistance(distanceMap.get(nodes[0]), nodes[0]);
            swap(0, size - 1);
            nodeIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            size--;
            heapify(0, size - 1);
            return minDistance;
        }

        /**
         * 上浮
         *
         * @param index
         */
        public void heapInsert(int index) {
            int parentIndex = (index - 1) / 2;
            while (distanceMap.get(nodes[parentIndex]) > distanceMap.get(nodes[index])) {
                swap(index, parentIndex);
                parentIndex = (parentIndex - 1) / 2;
            }
        }

        /**
         * 下沉
         */
        public void heapify(int index, int endIndex) {
            int leftSubIndex = index * 2 + 1;
            while (leftSubIndex < endIndex) {
                int smallest = leftSubIndex + 1 < endIndex && distanceMap.get(nodes[leftSubIndex + 1]) < distanceMap.get(nodes[leftSubIndex])
                        ? leftSubIndex + 1 : leftSubIndex;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    return;
                }
                swap(index, smallest);
                index = smallest;
                leftSubIndex = index * 2 + 1;
            }
        }

        private void swap(int index1, int index2) {
            nodeIndexMap.put(nodes[index1], index2);
            nodeIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    static class MinDistance {
        private int distance;
        private Node node;

        public MinDistance(int distance, Node node) {
            this.distance = distance;
            this.node = node;
        }
    }
}
