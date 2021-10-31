package org.example.basic.oj.leetcode.Q240;

import java.util.Arrays;

/**
 * @author anyuan
 * @date 2021-10-25 10:16
 */
class Solution {
    /**
     * 从一个上->下从小到大，左->右从小到大的m*n二维数组中，找出target值是否存在
     * <p>
     * 几乎确定是二分了
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        final int m = matrix.length, n = matrix[0].length;
        // 首先在[x][0]中找到比target小的位置
        int mRightBorder = m - 1, nRightBorder = n - 1;
        // 由于是有序的，所以只要从最大的一侧开始找即可
        for (int i = m - 1; i >= 0; i--) {
            if (matrix[i][0] == target) {
                return true;
            } else if (matrix[i][0] < target) {
                mRightBorder = i;
                break;
            }
        }
        // 同样地，列也一样缩小范围
        for (int j = n - 1; j >= 0; j--) {
            if (matrix[0][j] == target) {
                return true;
            } else if (matrix[0][j] < target) {
                nRightBorder = j;
                break;
            }
        }
        // 这样，把搜索范围缩小到0,0 ~ mRightBorder,nRightBorder了
        // 然后就可以开始二分了
        for (int i = 0; i <= mRightBorder; i++) {
            if (Arrays.binarySearch(matrix[i], 0, nRightBorder + 1, target) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean searchMatrix_2dBinarySearch(int[][] matrix, int target) {
        final int m = matrix.length, n = matrix[0].length;
        // 实际上就是对m的每一行进行二分查找
        for (int i = 0; i < m; i++) {
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (matrix[i][mid] <= target) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (matrix[i][r] == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从右上角开始，当做一个bst，向左的箭头都是左子树，乡下的箭头都是右子树
     * 然后从右上角开始对这个bst做bfs
     *
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix_bst(int[][] matrix, int target) {
        return false;
    }



        public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.searchMatrix(new int[][]{{-5}}, -5));
    }


}
