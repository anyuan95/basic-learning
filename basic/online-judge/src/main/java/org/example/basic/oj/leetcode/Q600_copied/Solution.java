package org.example.basic.oj.leetcode.Q600_copied;

/**
 * @author anyuan
 * @since 2021-09-30 17:09
 */
class Solution {

    private static final int N = 50;
    // dp数组表示 二进制数长度为i时，最高位不超过j时的合法数个数
    // 例如，dp[2][1]表示二进制数长度为2时，最高位不超过1时的合法数个数：10,01,00
    private static final int[][] dp = new int[N][2];

    static {
        // 0
        dp[1][0] = 1;
        // 0 1
        dp[1][1] = 2;
        for (int i = 2; i < N; i++) {
            // 要使当前值满足条件，且首位是0、
            // 首位是0实际上就跟不存在一样，实际上就是长度为i-1时取任意值（相当于首位小于等于1）的结果。
            dp[i][0] = dp[i - 1][1];
            // 要使当前值满足条件，且首位是0或1.
            // 如果首位必须是1，且又要满足条件。那就说明第二位一定不能是1，可选的情况一共就只有dp[i-1][0]种
            // 然后再加上首位是0的情况，就能够得到首位是0或1时的有效情况数量。
            dp[i][1] = dp[i - 1][0] + dp[i - 1][1];
        }
    }

    public int findIntegers(int n) {
        // 求出n的最高位是多少
        final int highestBit = getLen(n);
        int answer = 0, curr = 0, prev = 0;
        for (int bit = highestBit; bit >= 0; bit--) {
            // 每次循环将当前位为0的场景的个数累加到结果上
            // 每次循环计算[0,2^bit-1]部分

            // 取当前位
            curr = (n >> bit) & 1;
            // 如果当前位是1，那么所有当前位是0的值都是小于n的
            // 先将首位是0的加进去
            if (curr == 1) {
                answer += dp[bit + 1][0];
            }
            // 如果发现了上一位和这一位都是1，那么后边无论是什么，都不满足要求了
            if (prev == 1 && curr == 1) {
                break;
            }
            // TODO 没懂
            // 这里实际上是处理最后一位是0的情况。因为后面不再迭代了，所以要把dp[1][0]（即0的情况）加进去。
            if (bit == 0) {
                answer++;
            }
            prev = curr;
        }
        return answer;
    }

    private int len(int n) {
        int digit = 1;
        while (n != 0 && n != 1) {
            n >>= 1;
            digit++;
        }
        return digit;
    }

    int getLen(int n) {
        for (int i = 31; i >= 0; i--) {
            if (((n >> i) & 1) == 1) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findIntegers(16));
        System.out.println(solution.findIntegers(5));
//        System.out.println(solution.findIntegers(13));
    }
}
