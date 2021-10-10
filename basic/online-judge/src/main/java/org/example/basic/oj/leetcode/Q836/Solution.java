package org.example.basic.oj.leetcode.Q836;

/**
 * @author anyuan
 * @since 2021-09-30 16:02
 */
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // 先确认哪个左侧的边更左
        if (rec1[0] > rec2[0]) {
            // 如果1的左边在2的左边的右侧，则调转过来再调用一次
            return isRectangleOverlap(rec2, rec1);
        }
        // 现在已经保证1的左边在2的左边的左侧了
        // 没有交集的情况：
        // 1的上边界低于或等于2的下边界
        // 2的上边界低于或等于1的下边界
        // 2的左边界在1的右边界右侧或重叠
        if (rec1[3] <= rec2[1] || rec2[3] <= rec1[1] || rec2[0] >= rec1[2]) {
            return false;
        }
        return true;
    }
}
