package org.example.basic.oj.leetcode.Q282;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @date 2021-10-16 19:08
 */
class Solution {
    /**
     * 思路：尝试所有情况
     * 每次尝试完成后，都记录以下内容：
     * 1.前边算式的计算结果
     * 2.前边算式结尾最后一个数（有可能是多个字符组成的数字）（要带有正负号）
     * 3.前边算式结尾一个乘法结果（要带有正负号）
     * 回溯时，先用1减去3，然后如果3不是0，就再加上3除以2乘以(2拼接上下一个数)，以此结果作为下一次的1
     * <p>
     * 考虑在已有数字基础上，添加新数字时可能的操作方式：
     * 1.在已有的算式基础上，使用题目给定的运算符拼接下一个数字：+、-、*
     * 2.在已有的算式基础上，将新的数字拼接到算式中最后一个数后面
     *
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) {
        List<String> answer = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        // 第一个先放进去，因为第一个前面不能加符号
        builder.append(num.charAt(0));
        process(num, target, answer, builder,
                1, num.charAt(0) - '0', num.charAt(0) - '0', -1);
        return answer;
    }

    /**
     * @param num
     * @param target
     * @param answer
     * @param builder
     * @param currentIndex       num中当前字符位置索引
     * @param currentCalResult   当前算式计算结果
     * @param lastFullNum        最后一个完整的数
     * @param lastMultiplyResult 最后一个相乘的结果
     */
    private void process(String num, int target, List<String> answer, StringBuilder builder,
                         int currentIndex, long currentCalResult, long lastFullNum, long lastMultiplyResult) {
        if (currentIndex == num.length()) {
            if (currentCalResult == target) {
                answer.add(builder.toString());
            }
            return;
        }
        final int currentDigit = num.charAt(currentIndex) - '0';
        // 分情况处理：
        // 第一种情况，在已有的算式基础上，使用题目给定的运算符拼接下一个数字：+、-、*
        final int oldLen = builder.length();
        // 加
        builder.append('+').append(currentDigit);
        process(num, target, answer, builder, currentIndex + 1,
                currentCalResult + currentDigit, currentDigit, -1);
        builder.delete(oldLen, oldLen + 2);
        // 减
        builder.append('-').append(currentDigit);
        process(num, target, answer, builder, currentIndex + 1,
                currentCalResult - currentDigit, -currentDigit, -1);
        builder.delete(oldLen, oldLen + 2);
        // 乘
        builder.append('*').append(currentDigit);
        if (lastMultiplyResult != -1) {
            // 如果上一个符号是乘号
            process(num, target, answer, builder, currentIndex + 1,
                    currentCalResult - lastMultiplyResult + lastMultiplyResult * currentDigit ,
                    currentDigit, lastMultiplyResult * currentDigit);
        } else {
            // 如果上一个不是乘号
            process(num, target, answer, builder, currentIndex + 1,
                    currentCalResult - lastFullNum + lastFullNum * currentDigit ,
                    currentDigit, lastFullNum * currentDigit);
        }
        builder.delete(oldLen, oldLen + 2);

        // 第二种情况，在已有的算式基础上，将新的数字拼接到算式中最后一个数后面
        // 有一种特殊情况，前面的值是0，那就无法和这个字符拼接到一起
        if (lastFullNum == 0) {
            return;
        }
        builder.append(currentDigit);
        // 相当于是把当前数拼装到上一个数后面，需要根据上一个数的正负情况决定当前拼装只有的数的情况
        long currentFullNum = lastFullNum * 10 + currentDigit * (lastFullNum > 0 ? 1 : -1);
        if (lastMultiplyResult != -1) {
            // != -1 说明最近的一个符号是乘号*
            // 那么就要把前边的内容减去，再重新计算一下当前的乘积
            final long currentMultiplyResult = lastMultiplyResult / lastFullNum * currentFullNum;
            process(num, target, answer, builder, currentIndex + 1,
                    currentCalResult - lastMultiplyResult + currentMultiplyResult,
                    currentFullNum, currentMultiplyResult);
        } else {
            // 否则前一符号就一定是加号或者减号
            process(num, target, answer, builder, currentIndex + 1,
                    currentCalResult - lastFullNum + currentFullNum,
                    currentFullNum, -1);
        }
        builder.deleteCharAt(builder.length()-1);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.addOperators("123", 6));
//        System.out.println(solution.addOperators("232", 8));
//        System.out.println(solution.addOperators("105", 5));
//        System.out.println(solution.addOperators("1005", 5));
//        System.out.println(solution.addOperators("1005", 95));

        System.out.println(solution.addOperators("2147483648", -2147483648));

    }


}
