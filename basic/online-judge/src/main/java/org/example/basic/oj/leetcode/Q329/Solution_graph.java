package org.example.basic.oj.leetcode.Q329;

import java.util.*;

/**
 * @author anyuan
 * @since 2021-09-26 15:03
 */
class Solution_graph {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    /**
     * 思路：图，拓扑排序
     * ---------------------------
     * 把整个表格转成一个图，实际上使用的内存空间就是m*n个点对象和从小节点到相邻大节点之间的连线。
     * 题目就转换成了，从入度为0的节点，到达出度为0的节点的最长路径长度。
     * <p>
     * 麻烦的地方变成了将数组转为图。
     * 考虑借助map进行图的构建。
     *
     * 这个思路的时间复杂度可以优化。实际上并不用构建出完整的图对象。因为每个节点的入度+出度最多只会有4个（上下左右），实际上只要单独创建一个二维数组用于记录每个节点的入度即可。
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        final int m = matrix.length, n = matrix[0].length;
        HashMap<Integer, Node> nodeMap = new HashMap<>();
        // 遍历二维数组，构建有向图
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 每个点向四周发散
                int currentIndex = x * n + y, currentValue = matrix[x][y];
                if (!nodeMap.containsKey(currentIndex)) {
                    nodeMap.put(currentIndex, new Node(currentValue));
                }
                final Node currentNode = nodeMap.get(currentIndex);
                // 向四周找比当前节点大的值，加到当前节点的toNodes里
                for (int[] direction : DIRECTIONS) {
                    int targetX = x + direction[0], targetY = y + direction[1];
                    // 位置合法，且填过值了，且值比当前值小。那就可以计算current点的LISL了。
                    if (targetX >= 0 && targetX < m && targetY >= 0 && targetY < n
                            && matrix[targetX][targetY] > currentValue) {
                        // 如果目标节点比当前节点大，先检查这个点是否存在，不存在的话先新建。然后加到当前点的toNodes中。
                        final int targetIndex = targetX * n + targetY;
                        if (!nodeMap.containsKey(targetIndex)) {
                            nodeMap.put(targetIndex, new Node(matrix[targetX][targetY]));
                        }
                        final Node targetNode = nodeMap.get(targetIndex);
                        currentNode.toNodes.add(targetNode);
                        targetNode.inDegree++;
                    }
                }
            }
        }

        // 现在图构建完了。开始按照拓扑结构求LISL。
        int lisl = 0;
        // 先把所有入度为0的点拿出来。
        Queue<Node> queue = new LinkedList<>();
        nodeMap.values().stream().filter(node -> node.inDegree == 0).forEach(queue::offer);
        // 再做一个入度的小顶堆
        while (!queue.isEmpty()) {
            final int size = queue.size();
            lisl++;
            // 把入度为0的拿出来处理一遍
            for (int i = 0; i < size; i++) {
                final Node currentNode = queue.poll();
                for (Node toNode : currentNode.toNodes) {
                    toNode.inDegree--;
                    if (toNode.inDegree == 0) {
                        queue.offer(toNode);
                    }
                }
            }
        }
        return lisl;
    }


    private static class Node {
        int value;
        List<Node> toNodes;
        int inDegree;

        public Node(int value) {
            this.value = value;
            this.toNodes = new ArrayList<>();
            this.inDegree = 0;
        }
    }
}
