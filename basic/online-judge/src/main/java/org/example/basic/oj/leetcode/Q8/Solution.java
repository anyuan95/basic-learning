package org.example.basic.oj.leetcode.Q8;

/**
 * @author anyuan
 * @since 2021-08-14 13:33
 */
class Solution {
    /**
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * <p>
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        final char[] chars = s.toCharArray();
        final int length = chars.length;
        long answer = 0;
        int index = 0;
        // 去掉前导空格
        while (index < length && chars[index] == ' ') {
            index++;
        }
        boolean isNegative = false;
        // 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正
        if (index < length && chars[index] == '-') {
            isNegative = true;
            index++;
        } else if (index < length && chars[index] == '+') {
            isNegative = false;
            index++;
        }
        while (index < length && chars[index] >= '0' && chars[index] <= '9') {
            answer = answer * 10 + Integer.parseInt("" + chars[index]);
            if (!isNegative && answer > Integer.MAX_VALUE) {
                // 如果正数且>MAX，直接返回MAX
                return Integer.MAX_VALUE;
            } else if (isNegative && -answer < Integer.MIN_VALUE) {
                // 如果负数且<MIN，直接返回MIN
                return Integer.MIN_VALUE;
            }
            index++;
        }
        return (int) (isNegative ? -answer : answer);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();

        System.out.println(solution.myAtoi("0"));
        System.out.println(solution.myAtoi("42"));
        System.out.println(solution.myAtoi("   -42"));
        System.out.println(solution.myAtoi("4193 with words"));
        System.out.println(solution.myAtoi("words and 987"));
        System.out.println(solution.myAtoi("-91283472332"));
    }
}
