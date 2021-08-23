package org.example.basic.oj.leetcode.Q547;

import java.util.ArrayList;
import java.util.List;

/**
 * 岛屿问题
 *
 * @author anyuan
 * @since 2021-08-20 14:02
 */
class Solution_UFS {

    /**
     * 并查集
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        final UnionFindSet unionFindSet = new UnionFindSet(isConnected);
        final int count = isConnected.length;
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (isConnected[i][j] == 1) {
                    unionFindSet.union(i, j);
                }
            }
        }
        return unionFindSet.sets();
    }

    static class UnionFindSet {
        private int[] parent;
        private int sets;

        public UnionFindSet(int[][] nums) {
            sets = nums.length;
            parent = new int[sets];
            for (int i = 0; i < sets; i++) {
                parent[i] = i;
            }
        }

        public void union(int num1, int num2) {
            final int a1 = find(num1);
            final int a2 = find(num2);
            if (a1 != a2) {
                parent[a1] = a2;
                sets--;
            }
        }

        private int find(int num) {
            List<Integer> helper = new ArrayList<>();
            while (num != parent[num]) {
                helper.add(num);
                num = parent[num];
            }
            for (Integer subNode : helper) {
                parent[subNode] = num;
            }
            return num;
        }

        public int sets() {
            return sets;
        }
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1, 1, 0}, {1, 1, 0}, {0, 0, 1}
        };
        final Solution_UFS solution = new Solution_UFS();
        System.out.println(solution.findCircleNum(nums));
    }

}