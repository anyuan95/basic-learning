package org.example.basic.oj.leetcode.Q476;

/**
 * @author anyuan
 * @date 2021-10-18 10:00
 */
class Solution {
    public int findComplement(int num) {
        int answer = 0;
        int pow = 1;
        while (num != 0) {
            final int curr = num % 2 == 0 ? 1 : 0;
            num /= 2;
            answer += pow * curr;
            pow *= 2;
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findComplement(5));
        System.out.println(solution.findComplement(1));
    }

}
