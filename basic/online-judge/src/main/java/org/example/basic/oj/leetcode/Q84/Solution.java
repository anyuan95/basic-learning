package org.example.basic.oj.leetcode.Q84;

import java.util.Stack;

/**
 * 暴力解法：对于每个位置的高度，求这个高度下的最大矩形面积。On^2
 *
 *
 * @author anyuan
 * @date 2021-11-03 10:25
 */
class Solution {
    /**
     * 思路：单调栈（递增）
     * 单调栈的性质：
     * - 当元素出栈时，说明新元素是弹出的这个元素后面第一个比它小的元素
     * - 当元素出栈后，新的栈顶元素就是弹出的元素前面第一个比它小的元素
     *
     * l和r的边界问题需要自行模拟调试
     *
     * 重新梳理思路：
     * 使用的是一个单调递增的栈
     * 其实还是需要理解单调栈的各种性质
     * 填充这个单调栈的过程中，会填入栈中的内容都会是比栈顶值更大的；
     * 如果遇见了一个比栈顶小的值，则这时候就可以计算：由 当前遍历到的位置 到 当前栈顶的位置 组成一个矩形，这个矩形就有可能是最大面积
     * 所以，在向单调栈中填值的过程中，不断计算这个面积，最终就一定会得到最大值
     *
     * 小技巧：在数组头尾添加一个0，以此方式保证：栈中一定会放进元素，且，向栈中放入数据的最后，一定会计算至少一次最大面积
     *
     * -- 头部加0还是没十分懂
     *
     * 单调栈的一个常用作用：可以在一次遍历过程中找到数组中每个元素最近的更大/更小元素
     *
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        final int[] newHeights = new int[heights.length + 2];
        // 首尾加上0，让结果强制弹出
//        newHeights[0] = 0;
//        newHeights[newHeights.length - 1] = 0;
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        final Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < newHeights.length; i++) {
            int height = newHeights[i];
            // 然后就是拼装单调栈了
            // 只要单调栈中有内容需要弹出了，就重新计算一下面积
            // 实际上栈中存的并不是每个块的高度，而是块的下标
            while (!stack.isEmpty() && newHeights[stack.peek()] > height) {
                final Integer currentIndex = stack.pop();
                int left = stack.peek();
                int right = i;
                maxArea = Math.max(maxArea, (right - left - 1) * newHeights[currentIndex]);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
