package org.example.basic.oj.leetcode.Q305$_todo;

/**
 * 岛屿问题2
 * <p>
 * 输入：
 * 给定地图二维数组大小m和n；
 * 给定position数组，数组宽度为2，每一行的元素[i,j]值为1表示地图数组中该位置为陆地。
 * <p>
 * 输出：
 * 将position中的元素逐个加入地图数组中，求第i个元素加入该地图中时岛屿的数量。
 * <p>
 * 要求：
 * 时间复杂度达到O(k·logmn)，k = position.length
 *
 * @author anyuan
 * @since 2021-08-15 15:29
 */
class Solution {
    /**
     * 要求使用两种解法：
     * 解法1：直接使用m*n的数组；
     * 解法2：考虑到有可能出现[m*n很大，但k很小]的情况，使用集合类作为并查集内部的容器；
     */
}
