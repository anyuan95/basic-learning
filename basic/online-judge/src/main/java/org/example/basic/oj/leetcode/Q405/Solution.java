package org.example.basic.oj.leetcode.Q405;

/**
 * @author anyuan
 * @since 2021-10-02 22:49
 */
class Solution {
    /**
     * 十六进制中所有字母(a-f)都必须是小写。
     * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
     * 给定的数确保在32位有符号整数范围内。
     * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
     *
     * @param num
     * @return
     */
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        char[] hex = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder answer = new StringBuilder();

        // 每4位2进制就相当于1位16进制
        while (num != 0 && answer.length() < 8) {
            answer.insert(0, hex[num & 0xf]);
            num >>= 4;
        }
        return answer.toString();
    }


    /**
     * 97=a
     * 102=f
     * 48=0
     * 57=9
     */

    public static void main(String[] args) {
        final Solution solution = new Solution();
        int num = -1;
        System.out.println(Integer.toHexString(num));
        System.out.println(solution.toHex(num));
    }
}
