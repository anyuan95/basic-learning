package org.example.basic.oj.leetcode.Q547;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-09-06 14:41
 */
class Solution_BFS {

    /**
     * BFS方式，用队列+标记数组
     *
     * 逐个（未处理过的）城市进行遍历，将当前城市关联的所有（未遍历过的）城市全部加入队列中，然后继续对队列中的所有城市进行（找该城市的所有未被处理过的关联城市加入队列）处理
     * 每处理完一次队列，就相当于这个城市关联的所有城市全部标记完了，省份+1
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        int provinces = 0;
        // 实际上只需要处理矩阵的右上部分即可，右上部分与左下部分是对称的，从0,0到x,x这条线上所有值一定都为1
        boolean[] visited = new boolean[cities];
        Queue<Integer> queue = new LinkedList<>();
        for (int from = 0; from < cities; from++) {
            if (visited[from]) {
                // 如果这个节点处理过，则跳过
                continue;
            }
            // ！注意，当前节点也是已经遍历过了的
            // 此处进行标记后，遍历队列poll时就不能进行判断了，不然结果会有问题
            visited[from] = true;
            queue.add(from);
            while (!queue.isEmpty()) {
                final Integer currentCity = queue.poll();
                for (int i = 0; i < cities; i++) {
                    if (isConnected[currentCity][i] == 1 && !visited[i]) {
                        queue.add(i);
                        visited[i] = true;
                    }
                }
            }
            // 这时候队列一定已经为空了，说明从from节点走出来的关联的所有节点都标记完了
            provinces++;
        }
        return provinces;
    }

    public static void main(String[] args) {
        final Solution_BFS solution = new Solution_BFS();
        System.out.println(solution.findCircleNum(new int[][]{
                {1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}
        }));
    }


}
