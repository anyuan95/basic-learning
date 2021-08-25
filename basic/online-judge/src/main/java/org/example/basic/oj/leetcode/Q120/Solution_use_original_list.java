package org.example.basic.oj.leetcode.Q120;

import java.util.List;

/**
 * 取巧，直接在原list上修改值
 *
 * 所以，实际上并不是最优解
 *
 * @author anyuan
 * @since 2021-08-25 09:30
 */
class Solution_use_original_list {

    /**
     * 确实，换个思路。为啥没想到从下往上算呢？
     * 直接在原数组上去改值，从最后一层开始，用最后一层填倒数第二层，直到填到第一层，然后返回[0,0]就是结果
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        final int n = triangle.size();
        for (int i = n - 2; i >= 0; i--) {
            // 对于节点[i,j]，其最小值=min([i+1,j], [i+1,j+1]) + value[i,j]
            for (int j = 0; j <= i; j++) {
                set(triangle, i, j, Math.min(get(triangle, i + 1, j), get(triangle, i + 1, j + 1)) + get(triangle, i, j));
            }
        }
        return get(triangle, 0, 0);
    }

    private int get(List<List<Integer>> triangle, int i, int j) {
        return triangle.get(i).get(j);
    }

    private void set(List<List<Integer>> triangle, int i, int j, int value) {
        triangle.get(i).set(j, value);
    }
}
