package org.example.basic.oj.leetcode.OII069;

/**
 * @author anyuan
 * @date 2021-10-14 10:09
 */
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length, left = 0, right = n - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 由于mid是向下取整计算的结果，所以mid+1一定不会越界
            if (arr[mid] > arr[mid+1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.peakIndexInMountainArray(new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19}));
        System.out.println(solution.peakIndexInMountainArray(new int[]{18,29,38,59,98,100,99,98,90}));
        System.out.println(solution.peakIndexInMountainArray(new int[]{3,5,3,2,0}));
        System.out.println(solution.peakIndexInMountainArray(new int[]{0,2,3,5,3}));
    }
}
