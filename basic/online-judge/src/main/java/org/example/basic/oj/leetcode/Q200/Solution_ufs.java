package org.example.basic.oj.leetcode.Q200;

import java.util.ArrayList;
import java.util.List;

/**
 * UnionFindSet
 * 使用并查集方式
 *
 * @author anyuan
 * @since 2021-09-06 09:47
 */
class Solution_ufs {

    public int numIslands(char[][] grid) {
        final UnionFindSet unionFindSet = new UnionFindSet(grid);
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == '1') {
                    if (x > 0 && grid[x - 1][y] == '1') {
                        unionFindSet.union(x, y, x - 1, y);
                    }
                    if (x < grid.length - 1 && grid[x + 1][y] == '1') {
                        unionFindSet.union(x, y, x + 1, y);
                    }
                    if (y > 0 && grid[x][y - 1] == '1') {
                        unionFindSet.union(x, y, x, y - 1);
                    }
                    if (y < grid[0].length - 1 && grid[x][y + 1] == '1') {
                        unionFindSet.union(x, y, x, y + 1);
                    }
                }
            }
        }
        return unionFindSet.setCount;
    }

    /**
     * 在上一个方法的基础上，去掉了不必要的检查
     * 并不需要检查每个1节点的上下左右节点，实际上只需要检查每个节点的左和上即可
     *
     * @param grid
     * @return
     */
    public int numIslands_pro(char[][] grid) {
        final UnionFindSet unionFindSet = new UnionFindSet(grid);
        final int rows = grid.length, columns = grid[0].length;
        // 先处理左边界
        for (int x = 1; x < rows; x++) {
            if (grid[x][0] == '1' && grid[x - 1][0] == '1') {
                unionFindSet.union(x, 0, x - 1, 0);
            }
        }
        // 然后处理上边界
        for (int y = 1; y < columns; y++) {
            if (grid[0][y] == '1' && grid[0][y - 1] == '1') {
                unionFindSet.union(0, y, 0, y - 1);
            }
        }

        // 然后处理剩下的
        for (int x = 1; x < grid.length; x++) {
            for (int y = 1; y < grid[x].length; y++) {
                if (grid[x][y] == '1') {
                    if (grid[x][y - 1] == '1') {
                        unionFindSet.union(x, y, x, y - 1);
                    }
                    if (grid[x - 1][y] == '1') {
                        unionFindSet.union(x, y, x - 1, y);
                    }
                }
            }
        }
        return unionFindSet.setCount;
    }

    static class UnionFindSet {
        int[] parents;
        int[] sizes;
        int columns;
        int setCount;

        public UnionFindSet(char[][] grid) {
            final int rows = grid.length;
            final int columns = grid[0].length;
            this.columns = grid[0].length;
            final int elementCount = rows * columns;
            this.parents = new int[elementCount];
            this.sizes = new int[elementCount];
            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < columns; y++) {
                    if (grid[x][y] == '1') {
                        final int i = indexOf(x, y);
                        parents[i] = i;
                        sizes[i] = 1;
                        setCount++;
                    }
                }
            }
        }

        private int indexOf(int x, int y) {
            return x * columns + y;
        }

        private int findTopParentIndex(int x, int y) {
            int index = indexOf(x, y);
            List<Integer> childs = new ArrayList<>();
            while (parents[index] != index) {
                childs.add(index);
                index = parents[index];
            }
            for (Integer child : childs) {
                parents[child] = index;
            }
            return index;
        }

        private void union(int x1, int y1, int x2, int y2) {
            final int ancestor1 = findTopParentIndex(x1, y1);
            final int ancestor2 = findTopParentIndex(x2, y2);
            if (ancestor1 != ancestor2) {
                final int size1 = sizes[ancestor1];
                final int size2 = sizes[ancestor2];
                int smaller = size1 < size2 ? ancestor1 : ancestor2;
                int bigger = smaller == ancestor1 ? ancestor2 : ancestor1;
                parents[smaller] = bigger;
                sizes[bigger] += sizes[smaller];
                setCount--;
            }
        }
    }
}
