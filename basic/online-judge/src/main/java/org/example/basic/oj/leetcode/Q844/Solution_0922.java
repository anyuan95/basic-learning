package org.example.basic.oj.leetcode.Q844;

/**
 * @author anyuan
 * @since 2021-09-22 15:54
 */
class Solution_0922 {
    /**
     * 思路：从后往前进行比较
     *
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        final char[] sChars = s.toCharArray();
        final char[] tChars = t.toCharArray();
        int sIndex = sChars.length - 1, tIndex = tChars.length - 1;
        int sSharpCount = 0, tSharpCount = 0;

        // 只要有一个没处理完，就继续处理
        while (sIndex >= 0 || tIndex >= 0) {
            // 注意：当#数量超过字母数量时，可能会导致越界问题
            // 如果当前有剩余的#，或是当前位置是#，那就继续往前消除
            while (sIndex >= 0 && (sChars[sIndex] == '#' || sSharpCount > 0)) {
                if (sChars[sIndex] == '#') {
                    sSharpCount++;
                } else { // != '#'
                    sSharpCount--;
                }
                sIndex--;
            }
            // 对t字符串的处理同上
            while (tIndex >=0 && (tChars[tIndex] == '#' || tSharpCount > 0)) {
                if (tChars[tIndex] == '#') {
                    tSharpCount++;
                } else { // != '#'
                    tSharpCount--;
                }
                tIndex--;
            }

            // 最后，比较当前字符：
            // 有一种情况：#的个数超过了字符的个数，即index<0。如果二者index都小于0，则说明两个字符串最终结果都是空，否则一定不同
            if (sIndex < 0 && tIndex < 0) {
                return true;
            } else if (sIndex < 0 || tIndex < 0) {
                return false;
            }

            // 如果当前字符相等，则指针都向前移动一个格
            // 如果当前字符不相等，则返回false
            if (sChars[sIndex] == tChars[tIndex]) {
                sIndex--;
                tIndex--;
            } else { // !=
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution_0922 solution = new Solution_0922();
        System.out.println(solution.backspaceCompare("ab#c", "ad#c"));
        System.out.println(solution.backspaceCompare("ab##", "c#d#"));
        System.out.println(solution.backspaceCompare("a##", "#b#"));
        System.out.println(solution.backspaceCompare("a#c", "b"));

    }
}
