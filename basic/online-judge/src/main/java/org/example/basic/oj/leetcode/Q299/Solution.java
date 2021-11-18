package org.example.basic.oj.leetcode.Q299;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @date 2021-11-08 20:54
 */
class Solution {
    public String getHint(String secret, String guess) {
        int a = 0, b = 0;
        // 0~9
        final int[] frequencies = new int[10];
        final int n = secret.length();
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                a++;
            } else {
                frequencies[secret.charAt(i) - '0']++;
            }
        }
        // a计算完毕，f数组中剩余的内容比对之后就能得出b
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) != guess.charAt(i) && frequencies[guess.charAt(i) - '0'] > 0) {
                b++;
                frequencies[guess.charAt(i) - '0']--;
            }
        }
        return a + "A" + b + "B";
    }

    public String getHint_better(String secret, String guess) {
        int a = 0, b = 0;
        // 0~9
        final int[] s = new int[10], g = new int[10];
        final int n = secret.length();
        for (int i = 0; i < n; i++) {
            final char sChar = secret.charAt(i), gChar = guess.charAt(i);
            if (sChar == gChar) {
                a++;
            } else {
                s[sChar - '0']++;
                g[gChar - '0']++;
            }
        }
        // 这时每个值只要取s和g中最少出现的次数，加到一起，就是b的数量
        for (int i = 0; i < 10; i++) {
            b += Math.min(s[i], g[i]);
        }
        return a + "A" + b + "B";
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.getHint("1807", "7810"));
        System.out.println(solution.getHint("1123", "0111"));
        System.out.println(solution.getHint("1", "0"));
        System.out.println(solution.getHint("1", "1"));
        System.out.println(solution.getHint("1122", "1222"));
    }
}
