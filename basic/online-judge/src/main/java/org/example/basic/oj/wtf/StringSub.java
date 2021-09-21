package org.example.basic.oj.wtf;

import java.util.Random;

/**
 * 字符串相减
 *
 * @author anyuan
 * @since 2021-09-15 21:28
 */
class StringSub {

    public static void main(String[] args) {
        System.out.println(sub("523904451", "1795191221"));
//        n = 17951 91221
//        m =  5239 04451
//        sub =    -161186770
//        m - n = -1271286770

        for (int i = 0; i < 100; i++) {
            final Random random = new Random();
            int m = Math.abs(random.nextInt()), n = Math.abs(random.nextInt());
            final long a = Long.parseLong(sub(String.valueOf(m), String.valueOf(n)));
            if (a != m - n) {
                System.out.println("m = " + m);
                System.out.println("n = " + n);
                System.out.println("sub = " + a);
                System.out.println("m - n = " + (m - n));
                return;
            }
        }
    }

    private static String sub(String m, String n) {
        // 先判断大小
        boolean mIsBigger = (m + n).compareTo(n + m) < 0;
        char[] biggerChars = mIsBigger ? m.toCharArray() : n.toCharArray();
        char[] smallerChars = mIsBigger ? n.toCharArray() : m.toCharArray();

        int biggerLen = biggerChars.length, smallerLen = smallerChars.length;
        int maxLen = Math.max(biggerLen, smallerLen);

        char[] answerDigits = new char[maxLen];
        int temp;
        boolean borrow1 = false;
        for (int biggerIndex = maxLen - 1, smallerIndex = smallerLen - 1; biggerIndex >= 0; biggerIndex--, smallerIndex--) {
            if (smallerIndex >= 0) {
                temp = biggerChars[biggerIndex] - smallerChars[smallerIndex];
            } else {
                // small这位已经没了
                temp = biggerChars[biggerIndex] - 48;
            }
            if (borrow1) {
                temp--;
            }
            if (temp < 0) {
                temp = 10 + temp;
            }
            borrow1 = temp < 0;
            answerDigits[biggerIndex] = (char) (48 + temp);
        }

        int firstNonZeroIndex = 0;
        while (answerDigits[firstNonZeroIndex] == '0') {
            firstNonZeroIndex++;
        }
        return firstNonZeroIndex == maxLen - 1 ? "0" : (
                (mIsBigger ? "" : "-") + new String(answerDigits, firstNonZeroIndex, maxLen - firstNonZeroIndex)
        );
//        String answer = new String(answerDigits, firstNonZeroIndex, maxLen - firstNonZeroIndex);
//        return mIsBigger ? answer : "-" + answer;
    }
}
