package org.example.basic.oj.leetcode.Q547;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-08-20 14:59
 */
public class Solution_BFS {

    /**
     * 尝试使用BFS方式
     * DFS方式是先找当前节点的相连节点，然后相连节点再去找相连节点；
     * BFS方式是先看当前节点的所有相连节点，然后再看下个节点的所有相连节点...；
     *
     * 不过两种方式都需要对访问过的节点进行记录
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        final int cityCount = isConnected.length;
        int provCount = 0;
        boolean[] isVisited = new boolean[cityCount];
        Queue<Integer> queue = new LinkedList<>();
        // 这一层循环实际上是为了防止给的图并不是一个全节点连通图
        for (int current = 0; current < cityCount; current++) {
            if (!isVisited[current]) {
                // ↑一次循环就是遍历完一个连通图，实际上一个省就是一个连通图
                provCount++;
                isVisited[current] = true;
                queue.add(current);
                // 将当前节点的所有next都加到队列中，然后下次再取一个左相同的操作
                while (!queue.isEmpty()) {
                    final Integer node = queue.remove();
                    for (int i = 0; i < cityCount; i++) {
                        if (isConnected[node][i] == 1 && !isVisited[i]) {
                            isVisited[i] = true;
                            queue.add(i);
                        }
                    }
                }
            }
        }
        return provCount;
    }
}
