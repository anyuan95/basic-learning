package org.example.basic.oj.leetcode.Q678;

/**
 * @author anyuan
 * @since 2021-09-13 09:56
 */
class Solution_simulation_better {
    public boolean checkValidString(String s) {
        final char[] chars = s.toCharArray();
        // 遍历到当前位置为止剩余的未配对的左括号最小/最大数量
        int minLeftBracketCount = 0, maxLeftBracketCount = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                minLeftBracketCount++;
                maxLeftBracketCount++;
            } else if (aChar == ')') {
                minLeftBracketCount = Math.max(0, minLeftBracketCount-1);
                maxLeftBracketCount--;
                if (maxLeftBracketCount < 0) {
                    // 如果max<0了，说明 右括号数量 > (左括号+星号)数量
                    return false;
                }
            } else if (aChar == '*') {
                // 有可能是作为右括号，与已统计的某个左括号抵消了
                // 有可能是作为左括号，添加到统计值中
                // 最小值为负数时，重置为0
                minLeftBracketCount = Math.max(0, minLeftBracketCount-1);
                maxLeftBracketCount++;
            }
        }
        // 由于min是最小的左括号数量。当遍历完整个字符串后，只有最小需要的左括号数为0，才说明当前串是匹配的。
        return minLeftBracketCount == 0;
    }
}
