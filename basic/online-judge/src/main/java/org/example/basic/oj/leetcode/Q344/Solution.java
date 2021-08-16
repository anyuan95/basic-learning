package org.example.basic.oj.leetcode.Q344;

/**
 * @author anyuan
 * @since 2021-08-15 11:57
 */
class Solution {

    public void reverseString(char[] s) {
        if (s == null || s.length == 0) return;
        int left = 0, right = s.length - 1;
        while (left <= right) {
            swap(s, left++, right--);
        }
    }

    private void swap(char[] array, int index1, int index2) {
        if (index1 == index2) return;
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
