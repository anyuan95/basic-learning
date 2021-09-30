package org.example.basic.oj.leetcode.MT001;

import java.util.Scanner;

/**
 * @author anyuan
 * @since 2021-09-29 00:08
 */
public class Solution_no_passed {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int inputCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < inputCount; i++) {
            final String input = scanner.nextLine();
            final char[] chars = input.toCharArray();
            // 用户名的首字符必须是大写或者小写字母。
            // 用户名只能包含大小写字母，数字。
            // 用户名需要包含至少一个字母和一个数字。
            // 如果用户名合法，请输出 "Accept"，反之输出 "Wrong"。
            boolean isStartWithLetter = false, hasNumber = false, hasLetter = false;
            if (chars[0] >= 'A' && chars[0] <= 'Z' || chars[0] >= 'a' && chars[0] <= 'z') {
                isStartWithLetter = true;
                hasLetter = true;
            }
            for (char aChar : chars) {
                if (aChar >= 'A' && aChar <= 'Z' || aChar >= 'a' && aChar <= 'z') {
                    hasLetter = true;
                } else if (aChar >= '0' && aChar <= '9') {
                    hasNumber = true;
                } else {
                    break;
                }
            }
            if (isStartWithLetter && hasLetter && hasNumber) {
                System.out.println("Accept");
            } else {
                System.out.println("Wrong");
            }
        }
    }
}
