package org.example.basic.oj.leetcode.Q859;

/**
 * @author anyuan
 * @date 2021-11-23 22:55
 */
class Solution {
    public boolean buddyStrings(String s, String goal) {
        final int sLen = s.length(), goalLen = goal.length();
        if (sLen != goalLen) {
            return false;
        }
        boolean hasRepeatChar = false;
        int[] sCount = new int[26];
        int diffIndex1 = -1, diffIndex2 = -1;
        for (int i = 0; i < sLen; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (diffIndex1 == -1) {
                    diffIndex1 = i;
                } else if (diffIndex2 == -1) {
                    diffIndex2 = i;
                } else {
                    return false;
                }
            }
            sCount[s.charAt(i) - 'a']++;
            if (!hasRepeatChar) {
                if (sCount[s.charAt(i) - 'a'] >= 2) {
                    hasRepeatChar = true;
                }
            }
        }
        if (diffIndex1 != -1 && diffIndex2 != -1) {
            return s.charAt(diffIndex1) == goal.charAt(diffIndex2) && s.charAt(diffIndex2) == goal.charAt(diffIndex1);
        }
        if (diffIndex1 == -1 && diffIndex2 == -1) {
            return hasRepeatChar;
        }
        // 如果只有一个不同，那一定不是
        if (diffIndex1 == -1 || diffIndex2 == -1) {
            return false;
        }
        return true;
    }

    public boolean buddyStrings_ex(String s, String goal) {
        int n = s.length(), m = goal.length();
        if (n != m) return false;
        int[] cnt1 = new int[26], cnt2 = new int[26];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int a = s.charAt(i) - 'a', b = goal.charAt(i) - 'a';
            cnt1[a]++;
            cnt2[b]++;
            if (a != b) sum++;
        }
        boolean ok = false;
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) return false;
            if (cnt1[i] > 1) ok = true;
        }
        return sum == 2 || (sum == 0 && ok);
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.buddyStrings("ab", "ba"));
        System.out.println(solution.buddyStrings("ab", "ab"));
        System.out.println(solution.buddyStrings("aab", "aab"));
        System.out.println(solution.buddyStrings("aab", "aba"));
        System.out.println(solution.buddyStrings("aabaa", "aabcc"));
    }
}
