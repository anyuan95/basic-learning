package org.example.basic.algorithm.search;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-03-19 08:49
 */
public class KMP {

    public static void main(String[] args) {
        final int[] next = new int[8 + 1];
        new KMP().generateNext("abababca".toCharArray(), next);
        System.out.println(Arrays.toString(next));
    }

    private int kmp(char[] text, char[] pattern) {

        return -1;
    }

    private void generateNext(char[] pattern, int[] next) {
        int i = 0, j = -1;
        next[0] = -1;
        while (i < pattern.length) {
            if (j == -1 || pattern[i] == pattern[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }

}
