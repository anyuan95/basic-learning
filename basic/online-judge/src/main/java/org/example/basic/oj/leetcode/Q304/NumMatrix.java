package org.example.basic.oj.leetcode.Q304;

/**
 * 二维数组前缀和
 * !!!矩阵不可变
 *
 * @author anyuan
 * @since 2021-08-05 11:44
 */
class NumMatrix {

    /**
     * 二维前缀和
     */
    private final int[][] sum;

    /**
     * 构建过程其实还可以优化，与求区间和相似，也可以通过已得到的值加减计算获得
     *
     * @param matrix
     */
    public NumMatrix(int[][] matrix) {
        final int height = matrix.length;
        final int width = matrix[0].length;
        sum = new int[height][width];
        // 构建前缀数组
        for (int y = 0; y < height; y++) {
            sum[y][0] = matrix[y][0];
            for (int x = 1; x < width; x++) {
                sum[y][x] = sum[y][x - 1] + matrix[y][x];
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 1; y < height; y++) {
                sum[y][x] = sum[y - 1][x] + sum[y][x];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int upSum = row1 == 0 ? 0 : sum[row1 - 1][col2];
        int leftSum = col1 == 0 ? 0 : sum[row2][col1 - 1];
        int leftUpSum = row1 == 0 || col1 == 0 ? 0 : sum[row1 - 1][col1 - 1];
        return sum[row2][col2] + leftUpSum - upSum - leftSum;
//        return sum[row1 - 1][col1 - 1] + sum[row2][col2]
//                - sum[row1 - 1][col2] - sum[row2][col1 - 1];
    }

    public static void main(String[] args) {
//        int[][] matrix = {
//                {3, 0, 1, 4, 2},
//                {5, 6, 3, 2, 1},
//                {1, 2, 0, 1, 5},
//                {4, 1, 0, 1, 7},
//                {1, 0, 3, 0, 5}
//        };
        // [2,1,4,3],[1,1,2,2],[1,2,2,4]
//        final NumMatrix numMatrix = new NumMatrix(matrix);
//        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
//        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
//        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
//        System.out.println(numMatrix.sumRegion(0, 2, 1, 4));
//        测试结果:[null,0,-2,5]
//        期望结果:[null,8,11,12]

        int[][] matrix = {{-4, -5}};
        final NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(0, 0, 0, 0));
        System.out.println(numMatrix.sumRegion(0, 0, 0, 1));
        System.out.println(numMatrix.sumRegion(0, 1, 0, 1));

    }
}