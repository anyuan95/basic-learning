package org.example.basic.oj.leetcode.Q650;

/**
 * @author anyuan
 * @since 2021-09-19 23:55
 */
class Solution {

    /**
     * 思路：
     * 质数一定要n次，即：复制A一次，加上粘贴n-1次，共n次
     * 非质数，求所有质因数的和？
     *
     * @param n
     * @return
     */
    public int minSteps(int n) {
        int answer = 0;
        // 分解质因数求和
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                answer += i;
                n /= i;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.minSteps(8));
        System.out.println(solution.minSteps(17));
        System.out.println(solution.minSteps(3));
        System.out.println(solution.minSteps(6));
        System.out.println(solution.minSteps(16));
        System.out.println(solution.minSteps(32));
    }
}
