package org.example.basic.algorithm.merge;

import org.example.basic.helper.ArrayHelper;

import java.util.Arrays;

/**
 * Q327
 * 统计区间和的个数
 * 数组中连续的元素组成区间，计算每个区间的和，统计区间和在给定的LOWER和UPPER之间的个数。
 *
 *
 * @author anyuan
 * @since 2021-08-02 16:05
 */
public class CountOfRangeSum {

    public int countRangeSum_On3(int[] nums, int lower, int upper) {
        // 构建前缀数组
        final int arraySize = nums.length;
        long[] prefixArray = new long[arraySize + 1];
        prefixArray[0] = 0;
        long sum = 0;
        for (int i = 0; i < arraySize; i++) {
            sum += nums[i];
            prefixArray[i + 1] = sum;
        }
        int areaCount = 0;
        for (int leftPointer = 0; leftPointer < arraySize; leftPointer++) {
            for (int rightPointer = leftPointer; rightPointer < arraySize; rightPointer++) {
                // Sum(a~b) = PA(b+1) - PA(a)
                long areaSum = prefixArray[rightPointer + 1] - prefixArray[leftPointer];
                if (areaSum >= lower && areaSum <= upper) {
                    areaCount++;
                }
            }
        }
        return areaCount;
    }

    /**
     * 思路：
     * 首先构建前缀数组，前缀数组中第i个元素的值即为原数组中0~i所有值的和。
     * 将问题拆分为统计数组中以每个数结尾的所有区间的区间和统计，然后再将这些结果求和。
     * 例如：统计结尾为17的所有区间和，即0-17,1-17,2-17...17-17中满足范围的区间个数。
     *
     * 首先推导以下几个结论：
     * 1.前缀数组中第i位置的值为Sum(0...i)=Sum(0,,j-1)+Sum(j,i);
     * 2.由1可得，前缀数组中，第i位置的值减去第(i-n)位置的值，得到的是(i-n+1,i)的区间和;
     * 3.假设前缀数组中第i位置的值为s，则若(i-n,i)的区间和满足[lower,upper]，
     *   则一定有(0,i-n-1)的区间和满足[s-upper,s-lower]范围;
     *
     * 由以上3点，将sum数组进行拆分与归并处理。
     *
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] prefixSum = new long[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = nums[i] + prefixSum[i - 1];
        }
        return count(prefixSum, 0, nums.length - 1, lower, upper);
    }

    private int count(long[] prefixSum, int left, int right, int lower, int upper) {
        if (left == right) {
            return prefixSum[left] >= lower && prefixSum[right] <= upper ? 1 : 0;
        }
        int middle = left + ((right - left) >> 1);
        int leftCount = count(prefixSum, left, middle, lower, upper);
        int rightCount = count(prefixSum, middle + 1, right, lower, upper);
        int mergedCount = merge(prefixSum, left, middle, right, lower, upper);
        return leftCount + rightCount + mergedCount;
    }

    private int merge(long[] prefixSum, int left, int middle, int right, int lower, int upper) {
        int leftStart = left, leftEnd = middle, rightStart = middle + 1, rightEnd = right;
        /* do count */
        int count = 0;
        int leftPointer = leftStart, rightPointer = leftStart;
        for (int i = rightStart; i <= rightEnd; i++) {
            long newLower = prefixSum[i] - upper;
            long newUpper = prefixSum[i] - lower;
            // 注意此处是<而不是<=
            while (prefixSum[leftPointer] < newLower && leftPointer <= leftEnd) {
                leftPointer++;
            }
            while (prefixSum[rightPointer] <= newUpper && rightPointer <= leftEnd) {
                rightPointer++;
            }
            count += rightPointer - leftPointer;
        }
        /* do merge */
        long[] helpArray = new long[right - left + 1];
        int l = leftStart, r = rightStart, h = 0;
        while (l <= leftEnd && r <= rightEnd) {
            helpArray[h++] = prefixSum[l] < prefixSum[r] ? prefixSum[l++] : prefixSum[r++];
        }
        while (l <= leftEnd) {
            helpArray[h++] = prefixSum[l++];
        }
        while (r <= rightEnd) {
            helpArray[h++] = prefixSum[r++];
        }
        for (int i = 0; i < helpArray.length; i++) {
            prefixSum[i + left] = helpArray[i];
        }
        return count;
    }

    public static void main(String[] args) {
        final CountOfRangeSum countOfRangeSum = new CountOfRangeSum();
        for (int i = 0; i < 100; i++) {
            final int[] arr = ArrayHelper.generateRandomArray(1000, Integer.MAX_VALUE);
            final int[] arrayCopy = Arrays.copyOf(arr, arr.length);
            int upper = (int) (Math.random() * Integer.MAX_VALUE);
            int lower = (int) (Math.random() * upper);
            if (countOfRangeSum.countRangeSum_On3(arr, lower, upper) != countOfRangeSum.countRangeSum(arrayCopy,  lower, upper)) {
                System.out.println("error");
                return;
            }
        }
        System.out.println("ok");

//        int[] arr = {-2, 5, -1};
//        System.out.println(countOfRangeSum.countRangeSum(arr, -2, 2));
//        int[] arr = {-2147483647, 0, -2147483647, 2147483647};
//        System.out.println(countOfRangeSum.countRangeSum(arr, -564, 3864));

//        long[] arr = {1, 2, 3, 4, 5, 6, 7, 7};
//        System.out.println(countOfRangeSum.merge(arr, 0, 6, 7, 1, 5));
    }

}
