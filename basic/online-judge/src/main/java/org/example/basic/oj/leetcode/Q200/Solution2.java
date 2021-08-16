package org.example.basic.oj.leetcode.Q200;

import java.util.ArrayList;
import java.util.List;

/**
 * 岛屿问题1
 * 并查集方式
 *
 * @author anyuan
 * @since 2021-08-15 16:16
 */
class Solution2 {
    public int numIslands(char[][] grid) {
        final UnionFindSet unionFindSet = new UnionFindSet(grid);
        final int rows = grid.length;
        final int columns = grid[0].length;
        for (int i = 1; i < rows; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionFindSet.union(i, 0, i - 1, 0);
            }
        }
        for (int i = 1; i < columns; i++) {
            if (grid[0][i] == '1' && grid[0][i - 1] == '1') {
                unionFindSet.union(0, i, 0, i - 1);
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        unionFindSet.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        unionFindSet.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return unionFindSet.sets;
    }

    static class UnionFindSet {
        // 通过一维数组方式，将节点[i,j]映射为i*m+j
        private int[] parents;
        private int[] sizes;
        private int columns;
        private int sets;

        public UnionFindSet(char[][] grid) {
            columns = grid[0].length;
            int rows = grid.length;
            int elements = columns * rows;
            parents = new int[elements];
            sizes = new int[elements];
            sets = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        final int current = indexOf(i, j);
                        parents[current] = current;
                        sizes[current] = 1;
                        sets++;
                    }
                }
            }
        }

        private int indexOf(int row, int column) {
            return row * columns + column;
        }

        public void union(int i1, int j1, int i2, int j2) {
            final int ancestor1 = findAncestor(i1, j1);
            final int ancestor2 = findAncestor(i2, j2);
            if (ancestor1 != ancestor2) {
                int size1 = sizes[ancestor1];
                int size2 = sizes[ancestor2];
                int longer = size1 < size2 ? ancestor2 : ancestor1;
                int shorter = longer == ancestor1 ? ancestor2 : ancestor1;
                parents[shorter] = longer;
                sizes[longer] = size1 + size2;
                sizes[shorter] = 0;
                sets--;
            }
        }

        private int findAncestor(int i, int j) {
            int currentIndex = indexOf(i, j);
            List<Integer> ancestors = new ArrayList<>();
            while (currentIndex != parents[currentIndex]) {
                ancestors.add(currentIndex);
                currentIndex = parents[currentIndex];
            }
            for (Integer ancestor : ancestors) {
                parents[ancestor] = currentIndex;
            }
            return currentIndex;
        }
    }

    public static void main(String[] args) {
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        };
        char[][] grid = {
                {'1'},
                {'1'}
        };
        final Solution2 solution = new Solution2();
        System.out.println(solution.numIslands(grid));
    }
}
