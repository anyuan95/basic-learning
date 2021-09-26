package org.example.basic.oj.leetcode.Q11;

/**
 * @author anyuan
 * @since 2021-09-23 09:47
 */
class Solution_0923 {

    public int maxArea(int[] height) {
        int n = height.length, left = 0, right = n - 1;
        int maxArea = 0;
        while (left < right) {
            // 决定移动哪一侧
            // 如果左右高度不同时，将较短的一侧向中间移动
            // 如果左右高度相同

            // 这里实际上还能优化时间：如果移动后的木板高度比移动前还要短，那就不用计算了，一定会更小
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right]) {
                right--;
            } else if (height[left] < height[right]) {
                left++;
            } else {
                // 这里想了好久才想通
                // 一直在纠结两侧等高时移动随机一侧是否有可能会错过最大面积

                // 首先，由于木桶效应，（假如A>B）无论A多长都没有用，最后的面积是取决于B的
                // 从头开始推导：假设当前A和B距离为dist，A比B长，则面积就是B*dist
                // 此时如果将A向内侧移动，无论A'有多长，最终的面积一定是B*(dist-1)，就是一定会减小
                // 此时如果将B向内侧移动，B'有可能比B短/长/等长，仅当B'比B长时，才有可能获得更大的面积

                // 纠结的位置在于：如果A==B，那么应该移动哪一侧？
                // 考虑所有场景：（由于A==B，所以现在移动哪一侧都一样，假设移动A）
                // 如果A'高度大于A，面积会变成A*(dist-1)，必定减小；下次移动B，面积有可能变大
                // 如果A'高度等于A，面积会变成A*(dist-1)，必定减小；下次还是和当前一样的场景，无论移动谁都必定变小
                // 如果A'高度小于A，面积会变成A'*(dist-1)，也是必定减小；下次移动A'，
                // 综上，得出结论：如果A==B，无论如何向内移动，最终面积都会减小
                // 所以此时无论移动哪一侧都不会直接得到更大的面积
                // 同样的，无论移动哪一侧，最终都不会错过最大面积
                left++;
            }
        }
        return maxArea;
    }
}
