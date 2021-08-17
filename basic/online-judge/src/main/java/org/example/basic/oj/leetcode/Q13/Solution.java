package org.example.basic.oj.leetcode.Q13;

import java.util.HashMap;

/**
 * 罗马数字
 *
 * @author anyuan
 * @since 2021-08-17 12:03
 */
class Solution {

    /**
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * <p>
     * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
     * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
     * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        HashMap<Character, Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        int length = s.length(), answer = 0;
        char current = 0, following = 0;
        for (int i = length - 1; i >= 0; i--) {
            following = current;
            current = s.charAt(i);

            if (current == 'I' && (following == 'V' || following == 'X')) {
                answer -= 1;
            } else if (current == 'X' && (following == 'L' || following == 'C')) {
                answer -= 10;
            } else if (current == 'C' && (following == 'D' || following == 'M')) {
                answer -= 100;
            } else {
                answer += roman.get(current);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.romanToInt("III"));
        System.out.println(solution.romanToInt("IV"));
        System.out.println(solution.romanToInt("IX"));
        System.out.println(solution.romanToInt("LVIII"));
        System.out.println(solution.romanToInt("MCMXCIV"));
    }
}
