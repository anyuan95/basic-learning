package org.example.basic.oj.leetcode.Q42;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author anyuan
 * @since 2021-11-03 23:30
 */
class Solution {
    /**
     * 最最最最最暴力的做法
     * 每找到一个比之前高的柱子时，就计算一下这个柱子与前面最高的柱子之间围出来的面积
     * 其实也是递减栈的思想（大概？），反正就是保证维护着的栈一定是递减的。
     * 其实实际上根本用不到栈
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int answer = 0;

        // 刚开始的时候应该先找一个递增结束的位置开始
        int index = 0;
        while (index < height.length - 1 && height[index] < height[index + 1]) {
            index++;
        }

        // 用单向的也行
        int tallestIndex = index;
        for (int i = index + 1; i < height.length; i++) {
            // 只要出现了值更大的位置，就处理
            // 如果当前高度比栈顶高，就说明出现坑了，开始尽量填坑
            // 怎么填坑呢？先找到前面最高的一个，和当前这个取较小的

            // 保证这个栈底一定是当前最大的
            if (height[i] > height[i-1]) {
                // 在当前和之前最高的两个里选一个低一点的（木桶效应）
                final int smallerHeight = Math.min(height[tallestIndex], height[i]);
                int currentIndex = i - 1;
                // 逐渐逼近，但是要注意，不能跟最高的那根撞上
                while (height[currentIndex] < smallerHeight && currentIndex != tallestIndex) {
                    // 把这个坑填平
                    answer += smallerHeight - height[currentIndex];
                    height[currentIndex] = smallerHeight;
                    currentIndex--;
                }
                // 然后，如果当前高度大于之前的最大高度了，那就清空（重新记录一个最高的位置）
                if (height[tallestIndex] <= height[i]) {
                    tallestIndex = i;
                } else {
                    // 否则，把刚才弹出去的那些塞回去
                    // 其实不用操作，因为一直都是用数组连续位置处理的
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(solution.trap(new int[]{4,2,0,3,2,5}));
        System.out.println(solution.trap(new int[]{1,2,3}));
        System.out.println(solution.trap(new int[]{1}));
        System.out.println(solution.trap(new int[]{1,2}));
    }
}
