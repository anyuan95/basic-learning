package org.example.basic.oj.leetcode.Q391;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @author anyuan
 * @date 2021-11-16 14:56
 */
class Solution {
    /**
     * 1 <= rectangles.length <= 2 * 10^4
     * rectangles[i].length == 4
     * -10^5 <= xi, yi, ai, bi <= 10^5
     *
     * rectangles[i] = [xi, yi, ai, bi]
     * 这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
     * == [0],[1]  [2],[3]
     * 那么左上顶点是（xi, bi），右下顶点是（ai, yi）。
     * == [0],[3]  [2],[1]
     *
     * 先试着写一个最暴力的方法，
     * 先把最左上左下右上右下的位置都找到，
     * 然后做一个能够涵盖所有点的数组。
     * 给定的矩形覆盖的格子的值都加一，
     * 如果有值为2的格子，就是有叠加的，直接返回；
     * 如果一直没有值为2的格子，就全部填完，然后遍历一遍看有没有0，有0就是有空隙；
     *
     * 1.二维数组：还没等到TLE，就MLE了。。。
     * 2.bitSet数组：这回不MLE了，变TLE了。。。
     *
     * @param rectangles
     * @return
     */
    public boolean isRectangleCover(int[][] rectangles) {
        int leftestX = rectangles[0][0],
                rightestX = rectangles[0][2],
                highestY = rectangles[0][3],
                lowestY = rectangles[0][1];
        final int n = rectangles.length;
        for (int i = 1; i < n; i++) {
            int[] currentRectangle = rectangles[i];
            leftestX = Math.min(leftestX, currentRectangle[0]);
            rightestX = Math.max(rightestX, currentRectangle[2]);
            highestY = Math.max(highestY, currentRectangle[3]);
            lowestY = Math.min(lowestY, currentRectangle[1]);
        }
        // m*n的矩形，切分成1*1的小正方形，实际上是(m-1)*(n-1)个
        // 做一个映射关系，节点x,y映射的矩形是x+1,y+1
        // leftestX对应的record下标是0，highestY对应的record下标也是0
        BitSet[] record = new BitSet[highestY - lowestY];
//        boolean[][] record = new boolean[highestY - lowestY][rightestX - leftestX];
        for (int[] currentRectangle : rectangles) {
            final int left = currentRectangle[0];
            final int down = currentRectangle[1];
            final int right = currentRectangle[2];
            final int top = currentRectangle[3];
            // 然后把这个矩形里的所有小方块都填写到record里
//            for (int j = top; j > down; j--) {
//                for (int k = left; k < right; k++) {
//                    if (record[highestY - j][k - leftestX]) {
//                        return false;
//                    } else {
//                        record[highestY - j][k - leftestX] = true;
//                    }
//                }
//            }
            for (int j = top; j > down; j--) {
                if (record[highestY - j] == null) {
                    record[highestY - j] = new BitSet();
                }
                BitSet curr = record[highestY - j];
                for (int k = left; k < right; k++) {
                    if (curr.get(k - leftestX)) {
                        return false;
                    } else {
                        curr.set(k - leftestX);
                    }
                }
            }
        }
//        for (boolean[] booleans : record) {
//            for (boolean aBoolean : booleans) {
//                if (!aBoolean) {
//                    return false;
//                }
//            }
//        }
        for (BitSet bitSet : record) {
            for (int i = 0; i < rightestX - leftestX; i++) {
                if (!bitSet.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.isRectangleCover(new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}}));
//        System.out.println(solution.isRectangleCover(new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {2, 3, 3, 4}}));
        System.out.println(solution.isRectangleCover(new int[][]{{0,0,4,1},{0,0,4,1}}));
    }
}
