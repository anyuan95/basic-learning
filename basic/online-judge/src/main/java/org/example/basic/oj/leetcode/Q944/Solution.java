package org.example.basic.oj.leetcode.Q944;

/**
 * @author anyuan
 * @date 2022-05-12 23:41
 */
class Solution {
    public int minDeletionSize(String[] strs) {
        final int rows = strs.length, columns = strs[0].length();
        int answer = 0;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows - 1; j++) {
                if (strs[j].charAt(i) >= strs[j + 1].charAt(i)) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }

    public int _minDeletionSize(String[] strs) {
        int n = strs.length, m = strs[0].length(), ans = 0;
        out:for (int i = 0; i < m; i++) {
            for (int j = 0, cur = -1; j < n; j++) {
                int t = (int) strs[j].charAt(i);
                if (t < cur && ++ans >= 0) continue out;
                cur = t;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution._minDeletionSize(new String[]{"rrjk", "furt", "guzm"}));
    }
}
