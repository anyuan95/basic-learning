package org.example.basic.oj.leetcode.Q241;

import java.util.ArrayList;
import java.util.List;

/**
 * 由于数据量较小，可以使用dfs的方式暴力计算
 * 简单的思路就是，以每个运算符的位置，分别计算左右的所有可能的结果，然后使用运算符对左右所有可能的结果进行计算，得到的结果一定就是所有可能出现的结果
 *
 * @author anyuan
 * @date 2022-07-01 20:41
 */
class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        return dfs(expression.toCharArray(), 0, expression.length() - 1);
    }

    private List<Integer> dfs(char[] chars, int l, int r) {
        final List<Integer> answer = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            // 找到每个运算符位置，分别计算左右内容
            final char aChar = chars[i];
            if (aChar >= '0' && aChar <= '9') {
                continue;
            }
            final List<Integer> left = dfs(chars, l, i - 1);
            final List<Integer> right = dfs(chars, i + 1, r);
            for (Integer al : left) {
                for (Integer ar : right) {
                    if (aChar == '+') {
                        answer.add(al + ar);
                    } else if (aChar == '-') {
                        answer.add(al - ar);
                    } else if (aChar == '*') {
                        answer.add(al * ar);
                    }
                }
            }
        }
        // 考虑可能有多位数的情况
        if (answer.isEmpty()) {
            int num = 0;
            for (int i = l; i <= r; i++) {
                num = num * 10 + (chars[i] - '0');
            }
            answer.add(num);
        }
        return answer;
    }

}
