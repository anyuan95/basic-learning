package org.example.basic.oj.leetcode.Q38;

/**
 * @author anyuan
 * @date 2021-10-15 09:56
 */
class Solution {

    private static final String[] ANSWERS = new String[31];

    static {
        ANSWERS[1] = "1";
        for (int i = 2; i <= 30; i++) {
            String lastAnswer = ANSWERS[i - 1];
            final StringBuilder builder = new StringBuilder();
            // 第0个字符，先记上1个数量
            int lastCharCount = 1;
            char lastChar = lastAnswer.charAt(0);
            for (int k = 1; k < lastAnswer.length(); k++) {
                if (lastChar == lastAnswer.charAt(k)) {
                    lastCharCount++;
                } else {
                    builder.append(lastCharCount).append(lastChar);
                    lastCharCount = 1;
                    lastChar = lastAnswer.charAt(k);
                }
            }
            if (lastCharCount != 0) {
                builder.append(lastCharCount).append(lastChar);
            }
            ANSWERS[i] = builder.toString();
        }
    }

    public String countAndSay(int n) {
        return ANSWERS[n];
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        for (int i = 0; i < ANSWERS.length; i++) {
            System.out.println(i + " " + ANSWERS[i]);
        }
    }
}
