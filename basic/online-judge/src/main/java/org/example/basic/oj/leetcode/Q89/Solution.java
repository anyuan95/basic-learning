package org.example.basic.oj.leetcode.Q89;

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * 每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
     * 第一个整数是 0
     * 一个整数在序列中出现 不超过一次
     * 每对 相邻 整数的二进制表示 恰好一位不同 ，且
     * 第一个 和 最后一个 整数的二进制表示 恰好一位不同
     *
     * 2位数的可能结果有：
     * - 00,01,11,10
     * - 00,10,11,01
     *
     * 3:
     * - 000,001,011,010,110,111,101,100
     * 4:
     * - 0000,0001,0011,0010,0110,0111,0101,0100,1100,1101,1111,1110,1010,1011,1001,1000
     * 0,1,2,1,2,3,2,1,2,3,4,3,2,3,2,1
     *
     * 思路：
     * 首先还是要满足最后一个要求，就是第一个和最后一个数只有一位不同，假定最后的结果是100...0
     *
     * 每当多一位时，就把n-1的结果掉过来再拼一遍
     *
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(1);
        int pow = 2;
        for (int i = 2; i <= n; i++) {
            final int size = answer.size();
            for (int j = size - 1; j >= 0; j--) {
                answer.add(pow + answer.get(j));
            }
            pow *= 2;
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final List<Integer> answer = solution.grayCode(5);
        for (Integer integer : answer) {
            System.out.print(Integer.toBinaryString(integer) + " ");
        }
    }
}
