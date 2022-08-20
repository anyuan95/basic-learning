package org.example.basic.oj.leetcode.Q929;

import java.util.Arrays;

class Solution {
    public int numUniqueEmails(String[] emails) {
        return (int) Arrays.stream(emails)
                .map(email -> {
                    final String[] parts = email.split("@");
                    final StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < parts[0].length(); i++) {
                        if (parts[0].charAt(i) == '.') {
                            continue;
                        } else if (parts[0].charAt(i) == '+') {
                            break;
                        } else {
                            builder.append(parts[0].charAt(i));
                        }
                    }
                    builder.append('@').append(parts[1]);
                    return builder.toString();
                })
                .distinct()
                .count();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numUniqueEmails(new String[]{"test.email+alex@leetcode.com", "test.email.leet+alex@code.com"}));
    }
}
