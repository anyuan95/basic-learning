package org.example.basic.oj.leetcode.Q496;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author anyuan
 * @since 2021-08-09 09:34
 */
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        stack.push(nums2[0]);
        for (int i = 1; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                // 如果当前元素大于栈顶元素，则弹出，并进行记录
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int[] answer = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            final Integer greater = map.get(nums1[i]);
            answer[i] = greater == null ? -1 : greater;
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.nextGreaterElement(
                new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
        System.out.println(Arrays.toString(solution.nextGreaterElement(
                new int[]{2,4}, new int[]{1, 2,3,4})));
    }
}
