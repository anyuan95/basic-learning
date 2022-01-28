package org.example.basic.oj.leetcode.Q1996;

/**
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
 * <p>
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
 * <p>
 * 返回 弱角色 的数量。
 * <p>
 * 提示：
 * <p>
 * 2 <= properties.length <= 105
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 105
 *
 * @author anyuan
 * @date 2022-01-28 18:23
 */
class Solution_wrong {
    /**
     * 思路：用二维数组，则数组中比任意一个点弱的所有角色就是该点的严格左上范围内的数的数量
     * 感觉可能会MLE --可能不会？或者用map套数组，用这种方式减少几个一维数组
     * 弱角色是有递推关系的，a弱于b,b弱于c，则a一定弱于c
     * 然后再做一个DP表，用来记录[比位置x弱的角色数量]，按照从左上到右下的顺序去填
     * 最后对DP表里的所有内容求和（也可以填DP表的时候顺便求了）
     *
     * @param properties
     * @return
     */
    public int numberOfWeakCharacters(int[][] properties) {
        final int MAX_VALUE = (int) 1e5 + 1;
        int[][] table = new int[MAX_VALUE][MAX_VALUE];
        int[][] dp = new int[MAX_VALUE][MAX_VALUE];
        for (int[] property : properties) {
            table[property[0]][property[1]]++;
        }
        int count = 0;
        // 先填写第1行和第1列  --因为要求严格高于，所以这些都是0
        // 然后按列开始填。由于填值的顺序导致，填每个点时，它左上方的所有点都填完了，当前点的值 = 上方点的值 + 左方点的值 - 左上方点的值
        for (int i = 1; i < MAX_VALUE; i++) {
            for (int j = 1; j < MAX_VALUE; j++) {
                if (table[i][j] != 0) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + table[i - 1][j - 1];
                    count += table[i][j] * dp[i][j];
                }
            }
        }
        return count;
    }
}
