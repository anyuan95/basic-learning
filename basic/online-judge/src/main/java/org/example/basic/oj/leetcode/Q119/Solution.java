package org.example.basic.oj.leetcode.Q119;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-08-10 17:40
 */
class Solution {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>(rowIndex + 1);
        list.add(1);
        int index = 1;
        // 此处计算过程中还是需要用long，用int在rowIndex=30左右还是会溢出
        long lastValue = 1;
        while (index <= rowIndex) {
            lastValue = lastValue * (rowIndex - index + 1) / index;
            list.add((int) lastValue);
            index++;
        }
        return list;
    }

    /**
     * 暴力求每个位置的组合，会有溢出的问题。
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow_2(int rowIndex) {
        List<Integer> list = new ArrayList<>(rowIndex + 1);
        int index = 0;
        final int middle = (rowIndex + 1) / 2;
        while (index < middle) {
            list.add(combination(index, rowIndex));
            index++;
        }
        if ((rowIndex & 1) == 0) {
            list.add(combination(index, rowIndex));
        }
        while (--index >= 0) {
            list.add(list.get(index));
        }
        return list;
    }

    private int combination(int m, int n) {
        if (m == 0) {
            return 1;
        } else {
            long combination = 1;
            for (int i = n - m + 1; i <= n; i++) {
                combination *= i;
            }
            for (int i = 2; i <= m; i++) {
                combination /= i;
            }
            return (int) combination;
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.getRow(0));
        System.out.println(solution.getRow(1));
        System.out.println(solution.getRow(2));
        System.out.println(solution.getRow(3));
        System.out.println(solution.getRow(4));
        System.out.println(solution.getRow(10));
        System.out.println(solution.getRow(18));
        System.out.println(solution.getRow(30));
    }
}
