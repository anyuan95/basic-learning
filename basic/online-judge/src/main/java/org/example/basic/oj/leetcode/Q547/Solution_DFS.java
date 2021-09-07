package org.example.basic.oj.leetcode.Q547;

/**
 * @author anyuan
 * @since 2021-09-06 15:20
 */
class Solution_DFS {

    /**
     * dfs方式
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        int provinces = 0;
        boolean[] visited = new boolean[cities];
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                dfs(visited, isConnected, i);
                provinces++;
            }
        }
        return provinces;
    }

    private void dfs(boolean[] visited, int[][] isConnected, int currentCity) {
        visited[currentCity] = true;
        for (int i = 0; i < isConnected[currentCity].length; i++) {
            // 找到i节点关联的城市，然后对这些关联的城市再进行深度遍历
            if (isConnected[currentCity][i] == 1 && !visited[i]) {
                // 由于调用dfs之前已经检查过visited，所以dfs中不用再给出终止条件了
                dfs(visited, isConnected, i);
            }
        }
    }

    public static void main(String[] args) {
        final Solution_DFS solution = new Solution_DFS();
        System.out.println(solution.findCircleNum(new int[][]{
                {1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}
        }));
    }
}
