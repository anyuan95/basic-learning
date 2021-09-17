package org.example.basic.oj.leetcode.Q36;

import java.util.function.BiPredicate;

/**
 * @author anyuan
 * @since 2021-09-17 09:32
 */
class Solution_bit_array {
    /**
     * 对于每个位置的值，进行三种判断：所在行、所在列、所在3x3分区
     * <p>
     * 点[x,y]所在的格子是x/3*3+y/3
     *
     * 在数据极大地情况下可能会比使用boolean二维数组效率高，不过在题目的数据范围中并没有体现出多少区别
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        // 直接用数组进行标记，用三个9*9的数组
        // 下标0~8对应值1~9
        // false表示未有过这个值
        // 如果发现某个新的位置的值是true了，说明这个值之前填过了，即说明该数组不合规
        // 可以优化，改为使用二进制去标识该位是否有放过值
        int[] rows = new int[9];
        int[] columns = new int[9];
        int[] areas = new int[9];

        // 判断 base的二进制数中，倒数第target位是否是1
        BiPredicate<Integer, Integer> isTargetDigit1 = (base, target) -> (base & (1 << target)) != 0;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y] == '.') {
                    continue;
                }
                int val = board[x][y] - '1';
                int areaNo = x / 3 * 3 + y / 3;
                if (isTargetDigit1.test(rows[x], val) || isTargetDigit1.test(columns[y], val) || isTargetDigit1.test(areas[areaNo], val)) {
                    return false;
                }
                rows[x] |= (1 << val);
                columns[y] |= (1 << val);
                areas[areaNo] |= (1 << val);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 如果base的倒数第target位为0，则base & (1<<target) 的结果一定是0
        // 同样地，如果这一位为1，则一定有base & (1<<target) 的结果一定不是0
//        BiPredicate<Integer, Integer> containsTargetDigit = (base, target) -> (base & (1 << target)) != 0;
//
//        System.out.println(containsTargetDigit.test(8, 3));
//        System.out.println(containsTargetDigit.test(16, 4));
//        System.out.println(containsTargetDigit.test(8, 4));


        int num = 0;
        // 设置第8位=true
        int newNum = (num | (1 << 7));
        System.out.println(Integer.toBinaryString(newNum));
        // 判断第8位是不是true
        System.out.println((newNum & (1 << 7)) != 0);
        // 判断第9位是不是true
        System.out.println((newNum & (1 << 8)) != 0);

    }
}
