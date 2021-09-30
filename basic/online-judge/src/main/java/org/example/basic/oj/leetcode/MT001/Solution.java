package org.example.basic.oj.leetcode.MT001;

import java.util.Scanner;

/**
 * @author anyuan
 * @since 2021-09-28 22:41
 */
class Solution {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int inputCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < inputCount; i++) {
            final String input = scanner.nextLine();
            // 用户名的首字符必须是大写或者小写字母。
            // 用户名只能包含大小写字母，数字。
            // 用户名需要包含至少一个字母和一个数字。
            // 如果用户名合法，请输出 "Accept"，反之输出 "Wrong"。
            System.out.println(isOk(input) ? "Accept" : "Wrong");
        }
    }

    private static boolean isOk(String s) {
        if (s.length() < 2) {
            return false;
        }
        final char firstChar = s.charAt(0);
        if ((firstChar < 'A' || firstChar > 'Z') && (firstChar < 'a' || firstChar > 'z')) {
            return false;
        }
        boolean hasNumber = false;
        for (char aChar : s.toCharArray()) {
            if (aChar >= '0' && aChar <= '9') {
                hasNumber = true;
            } else if ((aChar < 'A' || aChar > 'Z') && (aChar < 'a' || aChar > 'z')) {
                return false;
            }
        }
        return hasNumber;
    }
}
