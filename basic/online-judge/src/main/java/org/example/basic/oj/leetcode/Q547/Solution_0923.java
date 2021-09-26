package org.example.basic.oj.leetcode.Q547;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-09-23 16:28
 */
class Solution_0923 {
    public int findCircleNum_dfs(int[][] isConnected) {
        // 整个二维数组一定是以斜率为-1的对角线对称的
        // 所以实际上对于每一行i，只需要遍历到第i列即可
        // --实际上第i列也不用遍历，因为每个节点与其自身一定是相连的
        // dfs不能省，因为省略的话会出现跳着的情况，导致少解

        int provCount = 0;

        final int n = isConnected.length;
        boolean[] visited = new boolean[n];
        for (int cityNo = 0; cityNo < n; cityNo++) {
            if (!visited[cityNo]) {
                dfs(visited, isConnected, cityNo);
                provCount++;
            }
        }
        return provCount;
    }

    private void dfs(boolean[] visited, int[][] isConnected, int cityNo) {
        // 没必要做判断了，因为进入dfs的时候就已经判断过visited了，且一定不会越界
//        if (cityNo < 0 || cityNo >= visited.length || visited[cityNo]) {
//            return;
//        }
        visited[cityNo] = true;
        for (int targetCityNo = 0; targetCityNo < visited.length; targetCityNo++) {
            // 如果targetCity没遍历过，且target与city是连通的
            if (!visited[targetCityNo] && isConnected[cityNo][targetCityNo] == 1) {
                dfs(visited, isConnected, targetCityNo);
            }
        }
    }

    /*--------------------------------------------------------------------------*/
    public int findCircleNum_bfs(int[][] isConnected) {
        // bfs方式，用队列做记录
        final int n = isConnected.length;
        Queue<Integer> queue = new LinkedList<>();
        final boolean[] visited = new boolean[n];

        int provCount = 0;

        // 在每次向队列中做add的时候更新visited状态
        // 这样比每次从队列中弹出值时才设置为visited性能要快
        for (int cityNo = 0; cityNo < n; cityNo++) {
            if (!visited[cityNo]) {
                queue.add(cityNo);
                visited[cityNo] = true;
                while (!queue.isEmpty()) {
                    final Integer currentCityNo = queue.poll();
                    for (int targetCityNo = 0; targetCityNo < isConnected[currentCityNo].length; targetCityNo++) {
                        if (!visited[targetCityNo] && isConnected[currentCityNo][targetCityNo] == 1) {
                            queue.add(targetCityNo);
                            visited[targetCityNo] = true;
                        }
                    }
                }
                provCount++;
            }
        }
        return provCount;
    }


    public static void main(String[] args) {
        final Solution_0923 solution = new Solution_0923();
        System.out.println(solution.findCircleNum_dfs(new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}}));
    }
}
