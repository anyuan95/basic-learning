package org.example.basic.oj.leetcode.Q152;

import java.util.Arrays;

/**
 * passed
 */
class Solution {

    /**
     * 写完Solution_0831后回来看，感觉当时写的更乱，还不如新写的算法，虽然有冗余的计算，但是（比这个）一目了然
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, tempMin = 1, tempMax = 1;
        boolean lastNumIsZero = false, hasZero = false;
        for (int num : nums) {
            max = Math.max(num, max);
            if (num == 0) {
                lastNumIsZero = true;
                hasZero = true;
                continue;
            }
            if (lastNumIsZero) {
                lastNumIsZero = false;
                tempMax = num;
                tempMin = num;
                max = Math.max(tempMax, max);
                continue;
            }
            if (num < 0) {
                int temp = tempMax;
                tempMax = tempMin;
                tempMin = temp;
            }
            tempMax = Math.max(tempMax * num, num);
            tempMin = Math.min(tempMin * num, num);
            max = Math.max(tempMax, max);
        }
        return (max < 0 && hasZero) ? 0 : max;
    }


    public static void main(String[] args) {
//        int[] a = {0, 2, 3, -2, 4};
        int[] a = {-2, 0, -1};
        System.out.println(new Solution().maxProduct(a));
    }
}