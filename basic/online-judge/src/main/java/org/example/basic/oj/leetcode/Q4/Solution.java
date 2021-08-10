package org.example.basic.oj.leetcode.Q4;

/**
 * 寻找两个有序数组的中位数
 * <p>
 * 要求时间复杂度O(log(m+n))
 *
 * @author anyuan
 * @since 2021-08-10 11:12
 */
class Solution {

    /**
     * O((m+n)/2)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 是否要特殊处理某数组为空的情况
        final int m = nums1.length, n = nums2.length;
        final int length = m + n;

        final boolean isEvenNumber = (length & 1) == 0;
        int index1 = 0, index2 = 0;
        int leftValue = 0, rightValue = 0;
        for (int i = 0; i <= length / 2; i++) {
            leftValue = rightValue;
            // 两种情况下遍历数组1：
            // 1.数组1没遍历完，数组2已经遍历完了；
            // 2.数组1没遍历完，数组1当前位置小于等于数组2当前位置；
            if (index1 < m && (index2 >= n || nums1[index1] <= nums2[index2])) {
                rightValue = nums1[index1++];
            } else {
                rightValue = nums2[index2++];
            }
        }

        if (isEvenNumber) {
            return (leftValue + rightValue) / 2.0;
        } else {
            return rightValue;
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3, 4}, new int[]{2}));
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{2}));
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{2, 6}));
    }
}
