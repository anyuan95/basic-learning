package org.example.basic.oj.leetcode.Q306;

/**
 * @author anyuan
 * @date 2022-01-10 20:21
 */
class Solution {
    /**
     * 1 <= num.length <= 35
     * <p>
     * 暴力，然后去剪枝
     * 能优化的点：
     * 1. 0不会作为数字的开头
     * 2. m位数+n位数的结果一定是Max(m,n)位数或Max(m,n)+1位数
     * <p>
     * 或许倒着计算会更好？因为倒着保证一定不会出现负数
     * 倒着处理的方式：
     * 1.第n个数一定要大于第n-1个数，即第n个数长度一定大于等于第n-1个数
     * 2.只需要判断An - A(n-1)的值，是否是剩余字符串的结尾
     * <p>
     * 这个字符串实际上应该是一个fibonacci数列
     *
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        final int n = num.length();
        if (n < 3) {
            return false;
        }
        // 正序暴力试一次
        for (int i = 1; i < n; i++) {
            final long num1 = Long.parseLong(num.substring(0, i));
            for (int j = 1; j < n - i; j++) {
                final long num2 = Long.parseLong(num.substring(i, i + j));
                if (check(num, num1, num2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(String num, long num1, long num2) {
        int len1 = String.valueOf(num1).length();
        int len2 = String.valueOf(num2).length();
        if (num.length() == len1 + len2) {
            return true;
        }
        if (num.substring(len1 + len2).startsWith(String.valueOf(num1 + num2))) {
            return check(num.substring(len1), num2, num1 + num2);
        }
        return false;
    }


    public boolean _isAdditiveNumber(String num) {
        final int n = num.length();
        if (n < 3) {
            return false;
        }
        final char[] chars = num.toCharArray();
        // i和j是长度
        for (int i = 1; i < n; i++) {
            if (chars[n - i] == '0') {
                continue;
            }
            final int number = Integer.parseInt(new String(chars, n - i, i));
            if (check(chars, n - i, number)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(char[] chars, int n, int lastNumber) {
        if (Integer.parseInt(new String(chars, 0, n)) == lastNumber) {
            return true;
        }
        final int lastLen = String.valueOf(lastNumber).length();
        // 被减数长度
        for (int i = 1; i <= lastLen; i++) {
            if (chars[n - i] == '0') {
                continue;
            }
            int smaller = Integer.parseInt(new String(chars, n - i, i));
            final int diff = lastNumber - smaller;
            if (diff <= 0) {
                continue;
            }
            final int diffLen = String.valueOf(diff).length();
            if (diffLen > n - i
                    || Integer.parseInt(new String(chars, n - i - diffLen, diffLen)) != diff) {
                return false;
            } else if (diffLen == n - i && Integer.parseInt(new String(chars, 0, diffLen)) == diff) {
                return true;
            }
            if (check(chars, n - i - diffLen, diff)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.isAdditiveNumber("000"));
        System.out.println(solution.isAdditiveNumber("112358"));
        System.out.println(solution.isAdditiveNumber("199100199"));

    }
}
