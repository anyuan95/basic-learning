package org.example.basic.oj.leetcode.Q120;

import java.util.List;

/**
 * 改为使用一维数组
 *
 * @author anyuan
 * @since 2021-08-25 10:02
 */
class Solution_dp_1d_array {

    /**
     * 思路：
     * 使用一维数组的实现方式，就是只创建一个一维数组，用于保存每行中每个节点的最小值。
     * 然后再当前行处理过程中，逐个更新一维数组中的值，将其逐个更新成当前行的各节点的最小值。
     * <p>
     * 方向：可以从上到下，但是从上到下的方式的话，最后还要对整个数组求一个最小值。
     * 所以理论上从下到上更好，省去了最后一次on的求最小值操作
     * <p>
     * 而且这种方法比使用原始数组的方法更快。
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        final int n = triangle.size();
        // 这里创建的数组需要+1，是为了方便下面遍历
        // 最好理解的方式是，先将最后一行triangle中的所有值填到dp中，然后从倒数第二行开始遍历
        // 这里是用到了int数组默认值为0的特性，通过数组多申请一个位置，以简化代码
        int[] dp = new int[n + 1];

        // 从下到上，就是[i,j] = Math.min([i+1,j], [i+1,j+1]) + current
        for (int i = n - 1; i >= 0; i--) {
            final List<Integer> currentRow = triangle.get(i);
            for (int j = 0; j < currentRow.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + currentRow.get(j);
            }
        }
        return dp[0];
    }
}
