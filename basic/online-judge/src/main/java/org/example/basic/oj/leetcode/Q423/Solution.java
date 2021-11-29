package org.example.basic.oj.leetcode.Q423;

/**
 * zero   z
 * one
 * two   w
 * three 去掉8之后的所有h
 * four   去掉567之后的所有u
 * five   去掉67之后的所有v
 * six     x
 * seven  去掉6之后的所有s
 * eight  g
 * nine
 * <p>
 * one 剩下的里的o
 * nine 剩下里的i
 *
 * @author anyuan
 * @date 2021-11-24 10:17
 */
class Solution {
    public String originalDigits(String s) {
        final int n = s.length();
        final int[] frequency = new int[26];
        for (int i = 0; i < n; i++) {
            frequency[s.charAt(i) - 'a']++;
        }
        final int[] numbers = new int[10];
        numbers[0] = frequency['z' - 'a'];
        remove(frequency, "zero".toCharArray(), frequency['z' - 'a']);
        numbers[2] = frequency['w' - 'a'];
        remove(frequency, "two".toCharArray(), frequency['w' - 'a']);
        numbers[6] = frequency['x' - 'a'];
        remove(frequency, "six".toCharArray(), frequency['x' - 'a']);
        numbers[8] = frequency['g' - 'a'];
        remove(frequency, "eight".toCharArray(), frequency['g' - 'a']);

        numbers[7] = frequency['s' - 'a'];
        remove(frequency, "seven".toCharArray(), frequency['s' - 'a']);
        numbers[5] = frequency['v' - 'a'];
        remove(frequency, "five".toCharArray(), frequency['v' - 'a']);
        numbers[4] = frequency['u' - 'a'];
        remove(frequency, "four".toCharArray(), frequency['u' - 'a']);
        numbers[3] = frequency['h' - 'a'];
        remove(frequency, "three".toCharArray(), frequency['h' - 'a']);

        numbers[9] = frequency['i' - 'a'];
        remove(frequency, "nine".toCharArray(), frequency['i' - 'a']);
        numbers[1] = frequency['o' - 'a'];
        remove(frequency, "one".toCharArray(), frequency['o' - 'a']);

        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i]; j++) {
                builder.append(i);
            }
        }
        return builder.toString();
    }

    private void remove(int[] frequency, char[] chars, int times) {
        for (char aChar : chars) {
            frequency[aChar - 'a'] -= times;
        }
    }
}
