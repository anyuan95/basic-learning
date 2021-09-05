package org.example.basic.oj.leetcode.Q852;

/**
 * @author anyuan
 * @since 2021-09-02 16:37
 */
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length, left = 0, right = n-1, middle;
        while (left < right) {
            middle = left + ((right-left) >> 1);
            if (arr[middle] > arr[middle+1]) {
                right = middle;
            } else {
                left = middle +1;
            }
        }
        return left;
    }
}
