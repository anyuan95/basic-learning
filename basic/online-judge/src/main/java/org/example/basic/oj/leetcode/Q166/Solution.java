package org.example.basic.oj.leetcode.Q166;

import java.util.HashMap;

/**
 * @author anyuan
 * @since 2021-10-03 09:50
 */
class Solution {
    /**
     * 思路：如果小数部分出现了重复的余数，则一定是循环小数
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        final StringBuilder answer = new StringBuilder();
        if (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0) {
            answer.append('-');
        }
        // 注意判断完符号之后就去掉符号
        // !!! 注意要先转成long然后再做abs！Integer.MIN_VALUE做abs之后还是MIN_VALUE
        long a = Math.abs((long) numerator), b = Math.abs((long) denominator);
        answer.append(a / b);
        if (a % b == 0) {
            return answer.toString();
        }
        answer.append('.');
        HashMap<Long, Integer> modMap = new HashMap<>();
        while ((a = a % b * 10) > 0 && !modMap.containsKey(a)) {
            modMap.put(a, answer.length());
            answer.append(a / b);
        }
        if (a == 0) {
            return answer.toString();
        }
        return answer.insert(modMap.get(a).intValue(), '(').append(')').toString();
    }

    /**
     * 实际上就是传说中的   竖式计算
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal_ex(int numerator, int denominator) {
        // 尽量先转成long再处理，避免 Integer.MIN_VALUE / -1 溢出
        long a = numerator, b= denominator;
        // 如果整除，就直接用long计算然后返回
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        final StringBuilder answer = new StringBuilder();
        // 解决正负问题
        if (a > 0 && b < 0 || a < 0 && b > 0) {
            answer.append('-');
        }
        // 取绝对值，后面的处理就全都是正数了
        a = Math.abs(a);
        b = Math.abs(b);
        // 解决整数部分
        answer.append(a / b).append('.');

        a %= b;
        // 记录余数部分，如果出现重复的余数了，那这一段就是小淑中循环的部分
        HashMap<Long, Integer> modMap = new HashMap<>();
        while (a != 0 && !modMap.containsKey(a)) {
            modMap.put(a, answer.length());
            a *= 10;
            answer.append(a / b);
            a %= b;
        }
        if (a == 0) {
            // 如果a==0，说明已经结束了，这个数是个不循环小数
            return answer.toString();
        }
        return answer.insert(modMap.get(a).intValue(), '(').append(')').toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.fractionToDecimal(4, 333));
//        System.out.println(solution.fractionToDecimal(-50, 8));
        System.out.println(solution.fractionToDecimal(-1, -2147483648));
    }
}
