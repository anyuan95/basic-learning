package org.example.basic.oj.leetcode.Q345;

/**
 * @author anyuan
 * @since 2021-08-15 12:01
 */
class Solution {
    public String reverseVowels(String s) {
        int left = 0, right = s.length() - 1;
        final char[] chars = s.toCharArray();
        while (left < right) {
            if (isVowel(chars[left]) && isVowel(chars[right])) {
                swap(chars, left++, right--);
            } else if (isVowel(chars[left]) && !isVowel(chars[right])) {
                right--;
            } else if (!isVowel(chars[left]) && isVowel(chars[right])) {
                left++;
            } else {
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    private boolean isVowel(char x) {
        return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u'
                || x == 'A' || x == 'E' || x == 'I' || x == 'O' || x == 'U';
    }

    private void swap(char[] array, int index1, int index2) {
        if (index1 == index2) return;
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseVowels("leetcode"));
    }
}
