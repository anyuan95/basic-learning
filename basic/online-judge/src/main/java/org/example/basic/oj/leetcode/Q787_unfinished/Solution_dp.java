package org.example.basic.oj.leetcode.Q787_unfinished;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-24 16:07
 */
class Solution_dp {

    /**
     * 尝试用dp方式解
     * <p>
     * 有可能有环。
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // dp表：i=使用步数，j=当前到达位置，dp[i][j]=从src走i步到j时使用的最小cost
        // 有状态转移方程：dp[i][j] = min { dp[i-1][k] + cost[k][j] }
        // 解释：从src到j，走了i步时使用的最小cost = { 从src到k，走了i-1步花费的最小cost + 一步从k走到j的cost }的最小值
        int[][] dp = new int[k + 2][n];
        // 1 <= n <= 100   1 <= pricei <= 104
        int INF = 101 * 10001;
        for (int[] ints : dp) {
            Arrays.fill(ints, INF);
        }
        // 从src到src，走0步，只需要0cost
        dp[0][src] = 0;
        // 此处需要逐层填，因为每个dp[i][j]都是依赖于dp[i-1][k]的
        for (int i = 1; i < k + 2; i++) {
            for (int[] flight : flights) {
                int from = flight[0], to = flight[1], cost = flight[2];
                dp[i][to] = Math.min(dp[i][to], dp[i - 1][from] + cost);
            }
        }
        int answer = INF;
        for (int i = 1; i < k + 2; i++) {
            answer = Math.min(answer, dp[i][dst]);
        }
        return answer == INF ? -1 : answer;
    }

        public static void main(String[] args) {
        final Solution_dp solution = new Solution_dp();
        int[][] flights = new int[][]{
                {4,1,1},{1,2,3},{0,3,2},{0,4,10},{3,1,1},{1,4,3}
//                {1,0,5}
//                {0,3,7},{4,5,3},{6,4,8},{2,0,10},{6,5,6},{1,2,2},{2,5,9},{2,6,8},{3,6,3},{4,0,10},{4,6,8},{5,2,6},{1,4,3},{4,1,6},{0,5,10},{3,1,5},{4,3,1},{5,4,10},{0,1,6}
        };
        System.out.println(solution.findCheapestPrice(5, flights, 2, 1, 1));
//        System.out.println(solution.findCheapestPrice(2, flights, 0, 1, 1));
//        System.out.println(solution.findCheapestPrice(7, flights, 2, 4, 1));
    }
}
