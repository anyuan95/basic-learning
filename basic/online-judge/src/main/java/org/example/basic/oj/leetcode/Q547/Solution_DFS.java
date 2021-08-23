package org.example.basic.oj.leetcode.Q547;

/**
 * @author anyuan
 * @since 2021-08-20 14:39
 */
public class Solution_DFS {

    /**
     * 尝试使用BFS方式
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        final int cityCount = isConnected.length;
        int provCount = 0;
        boolean[] isVisited = new boolean[cityCount];
        for (int i = 0; i < cityCount; i++) {
            if (!isVisited[i]) {
                dfs(isConnected, isVisited, i);
                provCount++;
            }
        }
        return provCount;
    }

    private void dfs(int[][] isConnected, boolean[] isVisited, int currentCity) {
        // 从某一个点开始向深处找到所有与它关联的点
        isVisited[currentCity] = true;

        for (int i = 0; i < isConnected[currentCity].length; i++) {
            // 找到所有与current节点相连的节点，然后继续进行dfs
            if (!isVisited[i] && isConnected[currentCity][i] == 1) {
                dfs(isConnected, isVisited, i);
            }
        }
    }


}
