package org.example.basic.oj.leetcode.Q11;

/**
 * @author anyuan
 * @since 2021-08-18 15:02
 */
class Solution {

    /**
     * 双指针逼近的证明：
     * 现有左侧的位置i（从最左侧开始）和右侧的位置j（从最右侧开始），二者中较小的一个 与 (j-1)构成一个矩形。
     * 有且仅有以下两种情况：
     * 1.长板向短板移动1个位置，则底变成底-1，而矩形的高度只会不变或减小，故面积只会不变或减小；
     * 2.短板向长板移动一个位置，则底变成底-1，这里会有三种情况：
     * - 短板移动到的位置，板比长板要长，则面积可能会变大（也有可能变小，也有可能不变）；
     * - 短板移动到的位置，板比长板要短，比短板要长，则面积可能会变大（也有可能变小，也有可能不变）；
     * - 短板移动到的位置，板比短板要短，则面积一定会减小；
     * 综上的情况1和2中，只有情况2中存在子情况，能够导致面积变大。故使用情况2的移动方式，每次取当前面积和移动后面积之间的最大值，保证直到两个板相碰时，一定能找到整个水槽的最大面积。
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int leftPointer = 0, rightPointer = height.length - 1;
        int maxArea = 0;
        while (leftPointer < rightPointer) {
            /* 高度 * 宽度 */
            maxArea = Math.max(
                    maxArea,
                    Math.min(height[leftPointer], height[rightPointer]) * (rightPointer - leftPointer)
            );
            if (height[leftPointer] < height[rightPointer]) {
                leftPointer++;
            } else {
                rightPointer--;
            }
        }
        return maxArea;
    }
}
