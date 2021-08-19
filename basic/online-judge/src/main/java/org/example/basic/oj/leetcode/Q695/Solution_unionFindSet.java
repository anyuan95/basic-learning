package org.example.basic.oj.leetcode.Q695;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-08-19 14:26
 */
public class Solution_unionFindSet {

    public int maxAreaOfIsland(int[][] grid) {
        final UnionFindSet unionFindSet = new UnionFindSet(grid);
        final int rows = grid.length;
        final int columns = grid[0].length;
        for (int i = 1; i < rows; i++) {
            if (grid[i][0] == 1 && grid[i - 1][0] == 1) {
                unionFindSet.union(i, 0, i - 1, 0);
            }
        }
        for (int j = 1; j < columns; j++) {
            if (grid[0][j] == 1 && grid[0][j - 1] == 1) {
                unionFindSet.union(0, j, 0, j - 1);
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (grid[i][j] == 1) {
                    if (grid[i - 1][j] == 1) {
                        unionFindSet.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == 1) {
                        unionFindSet.union(i, j, i, j - 1);

                    }
                }

            }
        }
        return unionFindSet.maxSize();
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class UnionFindSet {
        HashMap<Integer, Node> nodes;
        HashMap<Node, Node> parents;
        HashMap<Node, Integer> sizes;
        int columns;

        public int maxSize() {
            return sizes.values().stream().max(Integer::compareTo).orElse(0);
        }

        public UnionFindSet(int[][] grid) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizes = new HashMap<>();
            columns = grid[0].length;
            for (int row = 0; row < grid.length; row++) {
                for (int column = 0; column < columns; column++) {
                    if (grid[row][column] == 1) {
                        final Node node = new Node(row, column);
                        nodes.put(indexOf(row, column), node);
                        parents.put(node, node);
                        sizes.put(node, 1);
                    }
                }
            }
        }

        private int indexOf(int x, int y) {
            return x * columns + y;
        }

        private Node findAncestor(Node node) {
            List<Node> helper = new ArrayList<>();
            while (node != parents.get(node)) {
                helper.add(node);
                node = parents.get(node);
            }
            for (Node subNode : helper) {
                parents.put(subNode, node);
            }
            return node;
        }

        public void union(int x1, int y1, int x2, int y2) {
            final int index1 = indexOf(x1, y1);
            final int index2 = indexOf(x2, y2);
            final Node ancestor1 = findAncestor(nodes.get(index1));
            final Node ancestor2 = findAncestor(nodes.get(index2));
            if (ancestor1 != ancestor2) {
                final Integer size1 = sizes.get(ancestor1);
                final Integer size2 = sizes.get(ancestor2);
                Node longer = size1 > size2 ? ancestor1 : ancestor2;
                Node shorter = longer == ancestor1 ? ancestor2 : ancestor1;
                parents.put(shorter, longer);
                sizes.put(longer, size1 + size2);
                sizes.remove(shorter);
            }
        }
    }

    public static void main(String[] args) {
        final Solution_unionFindSet solution = new Solution_unionFindSet();
//        int[][] grid = new int[][]{
//                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
//        };
//        int[][] grid = new int[][]{
//                {1, 1, 0, 0, 0},
//                {1, 1, 0, 0, 0},
//                {0, 0, 0, 1, 1},
//                {0, 0, 0, 1, 1}
//        };
        int[][] grid = new int[][]{{0,0,0,0,0,0}};
        System.out.println(solution.maxAreaOfIsland(grid));
    }

}
