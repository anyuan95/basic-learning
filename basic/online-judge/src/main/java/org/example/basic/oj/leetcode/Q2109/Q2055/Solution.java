package org.example.basic.oj.leetcode.Q2109.Q2055;

class Solution {
    // public String addSpaces(String s, int[] spaces) {
    //     String result = "";
    //     int lastIndex = 0;
    //     for (int i = 0; i < spaces.length; i++) {
    //         result += (s.substring(lastIndex, spaces[i]) + " ");
    //         lastIndex += (spaces[i] - lastIndex);
    //     }
    //     // 把最后一段加上去
    //     result += s.substring(lastIndex);
    //     return result;
    // }

    public String addSpaces(String s, int[] spaces) {
        char[] result = new char[s.length() + spaces.length];
        int lastIndex = 0;
        for (int i = 0; i < spaces.length; i++) {
            for (int j = lastIndex; j < spaces[i]; j++) {
                result[j + i] = s.charAt(j);
            }
            result[spaces[i] + i] = ' ';
            lastIndex += (spaces[i] - lastIndex);
        }
        // 把最后一段加上去
        for (int j = lastIndex; j < s.length(); j++) {
            result[j + spaces.length] = s.charAt(j);
        }
        return new String(result);
    }
}
