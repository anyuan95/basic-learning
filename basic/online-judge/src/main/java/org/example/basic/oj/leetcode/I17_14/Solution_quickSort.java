package org.example.basic.oj.leetcode.I17_14;

import java.util.Arrays;

/**
 * 由于题目中只要求返回最小的k个即可（不需要保证这k个值的顺序），所以可以使用快速排序的思想
 * 快速排序（荷兰国旗问题）可以保证，在选中正确的划分值后，能保证划分值（target）左侧的数都是小于!!等于!!target的，
 * 那么只要找到一个target，然后分别处理以下三种情况：
 * 1.如果target左侧恰好有k个值，那么直接返回这k个；
 * 2.如果target左侧值的数量小于k个，那么基准值向右移动，直到左侧数量达到k；
 * 3.如果target左侧值的数量大于k个，那么基准值向左移动，直到左侧数量达到k；
 *
 * @author anyuan
 * @since 2021-09-03 16:31
 */
class Solution_quickSort {

    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (arr.length < k) {
            return arr;
        }
        process(arr, 0, arr.length - 1, k);
        int[] answer = new int[k];
        System.arraycopy(arr, 0, answer, 0, k);
        return answer;
    }

    private void process(int[] nums, int L, int R, int k) {
        if (L >= R) {
            return;
        }
        Info info = partition(nums, L, R);
        if (info.startIndex == k) {
            // targetStartIndex==k, 则说明L左侧的数都是小于等于L的，再加上L，正好K个值
        } else if (info.startIndex > k) {
            // targetStartIndex>k, 则说明左边还有多余的值，继续处理左边
            process(nums, L, info.startIndex - 1, k);
        } else { // <
            // targetStartIndex<k, 则说明左边值不够，继续处理右边
            process(nums, info.endIndex + 1, R, k);
        }
    }

    private Info partition(int[] nums, int L, int R) {
        if (L > R) {
            return new Info(-1, -1);
        } else if (L == R) {
            return new Info(L, R);
        }
        int currentIndex = L, smallIndex = L - 1, bigIndex = R, target = nums[R];
        while (currentIndex < bigIndex) {
            if (nums[currentIndex] < target) {
                swap(nums, currentIndex++, ++smallIndex);
            } else if (nums[currentIndex] == target) {
                currentIndex++;
            } else { // >
                swap(nums, currentIndex, --bigIndex);
            }
        }
        swap(nums, R, bigIndex);
        return new Info(smallIndex + 1, bigIndex);
    }

    private static void swap(int[] array, int index1, int index2) {
        if (index1 == index2) return;
        array[index1] = array[index1] ^ array[index2];
        array[index2] = array[index1] ^ array[index2];
        array[index1] = array[index1] ^ array[index2];
    }

    static class Info {
        int startIndex, endIndex;

        public Info(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }

    public static void main(String[] args) {
        final Solution_quickSort solution = new Solution_quickSort();
        System.out.println(Arrays.toString(solution.smallestK(new int[]{1, 3, 5, 7, 2, 4, 6, 8}, 4)));
    }
}
