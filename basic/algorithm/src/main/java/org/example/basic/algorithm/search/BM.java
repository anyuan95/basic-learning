package org.example.basic.algorithm.search;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-03-18 14:49
 */
public class BM {

    public static void main(String[] args) {
//        System.out.println(bm("abcacabdc".toCharArray(), "abd".toCharArray()));
//        System.out.println(bm("aaaaaaaaaaaaaaaa".toCharArray(), "baaa".toCharArray()));
        System.out.println(bm("aaaaaaaaaaaaaaaa".toCharArray(), "baaa".toCharArray()));

    }

    /**
     * 坏字符规则：
     * 从模式串尾向前与原串进行比对，若不一致，则将原串中的当前位置字符认作'坏字符'；
     * 检查坏字符在模式串中是否存在，若存在，则将模式串中该坏字符的位置与原串中对齐，继续进行对比；
     * 若不存在，则将模式串首与原串中坏字符下一位置对齐，继续进行比对；
     * <p>
     * 算法存在的问题：
     * 例如，原串aaaaaaaaaa，模式串baaa，会导致一直向前移动，直到数组从前部越界
     *
     * @param content
     * @param pattern
     * @return
     */
    private static int bm(char[] content, char[] pattern) {
        final int[] bc = new int[SIZE];
        generateBadCharDict(pattern, bc);
        final int contentLength = content.length;
        final int patternLength = pattern.length;
        final int[] suffix = new int[patternLength];
        final boolean[] prefix = new boolean[patternLength];
        generateGoodSuffix(pattern, suffix, prefix);
        int i = 0;
        while (i <= contentLength - patternLength) {
            int j = 0;
            for (j = patternLength - 1; j >= 0; j--) {
                if (content[i + j] != pattern[j]) {
                    break;
                }
            }
            if (j < 0) {
                return i;
            }
            final int x = j - bc[content[i + j]];
            int y = 0;
            if (j < patternLength - 1) {
                y = moveByGS(j, patternLength, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    // j 表示坏字符对应的模式串中的字符下标 ; m 表示模式串长度
    private static int moveByGS(int j, int patternLength, int[] suffix, boolean[] prefix) {
        int k = patternLength - 1 - j; // 好后缀长度
        if (suffix[k] != -1) {
            return j - suffix[k] + 1;
        }
        for (int r = j + 2; r <= patternLength - 1; ++r) {
            if (prefix[patternLength - r] == true) {
                return r;
            }
        }
        return patternLength;
    }

    public static final int SIZE = 256;

    private static void generateBadCharDict(char[] pattern, int[] bc) {
        Arrays.fill(bc, -1);
        for (int i = 0; i < pattern.length; i++) {
            bc[pattern[i]] = i;
        }
    }

    private static void generateGoodSuffix(char[] pattern, int[] suffix, boolean[] prefix) {
        Arrays.fill(suffix, -1);
        Arrays.fill(prefix, false);
        final int patternLength = pattern.length;
        for (int i = 0; i < patternLength - 1; i++) {
            int j = i;
            int k = 0; // 公共子串长度
            while (j >= 0 && pattern[j] == pattern[patternLength - 1 - k]) { // 求公共后缀子串
                j--;
                k++;
                suffix[k] = j + 1; // 表示公共后缀子串在pattern中的起始下标
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }
}
