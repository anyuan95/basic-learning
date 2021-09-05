package org.example.basic.oj.leetcode.Q844;

/**
 * @author anyuan
 * @since 2021-09-02 17:00
 */
class Solution_no_extra_space {
    public boolean backspaceCompare(String s, String t) {
        final char[] sc = s.toCharArray();
        final char[] tc = t.toCharArray();
        int sSharps = 0, tSharps = 0, sIndex = sc.length - 1, tIndex = tc.length - 1;
        while (sIndex >= 0 || tIndex >= 0) {
            // 找到各自最后一个有效字符，然后对比
            // 其实是把所有尾部的#和前面的非#消掉
            while (sIndex >= 0) {
                if (sc[sIndex] == '#') {
                    sSharps++;
                    sIndex--;
                } else if (sSharps > 0) {
                    // 不是#，但后边有#
                    sSharps--;
                    sIndex--;
                } else {
                    break;
                }
            }
            while (tIndex >= 0) {
                if (tc[tIndex] == '#') {
                    tSharps++;
                    tIndex--;
                } else if (tSharps > 0) {
                    // 不是#，但后边有#
                    tSharps--;
                    tIndex--;
                } else {
                    break;
                }
            }

            if (sIndex >= 0 && tIndex >= 0) {
                if (sc[sIndex] != tc[tIndex]) {
                    return false;
                }
            } else if (sIndex >= 0 || tIndex >= 0) {
                // 有且只有一个>=0
                return false;
            }
            sIndex--;
            tIndex--;
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution_no_extra_space solution = new Solution_no_extra_space();
        System.out.println(solution.backspaceCompare("ab##", "c#d#"));
        System.out.println(solution.backspaceCompare("ab##", "c#d#"));
    }
}
