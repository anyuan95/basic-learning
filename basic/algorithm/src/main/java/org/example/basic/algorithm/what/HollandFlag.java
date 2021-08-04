package org.example.basic.algorithm.what;

import org.example.basic.helper.ArrayHelper;

import java.util.Arrays;

/**
 * 荷兰国旗问题
 * 问题描述：给定一个数组，给定一个数，将数组划分为小于该数、等于该数、大于该数的三个区域。区域内的元素不需要有序。
 * 问题限制：不允许使用额外的数组空间。
 *
 * @author anyuan
 * @since 2021-08-03 14:53
 */
public class HollandFlag {

    /**
     * 提供两个指针，small指针指向小于区域最后一个元素位置，great指针指向大于区域第一个元素位置。
     * 遍历每个元素后，判断该元素属于哪个区域，然后进行以下三种条件判断：
     * 1.如果当前元素小于target，则将当前位置与small指针下一位置交换，然后当前指针向后移；
     * 2.如果当前元素等于target，则不进行处理，当前指针向后移；
     * 3.如果当前元素大于target，则将当前位置与great指针前一位置交换；（此处不能将当前指针向右移，因为换过来的元素仍需要进行下一次判断）
     * 直到当前指针与great指针碰撞，则结束。此时已经是满足条件的数组了。
     *
     * @param nums
     * @param target
     */
    private void hollandFlag(int[] nums, int target) {
        int smallerAreaLastIndex = -1, greaterAreaLastIndex = nums.length;
        int index = 0;
        while (index < greaterAreaLastIndex - 1) {
            if (nums[index] < target) {
                swap(nums, index++, ++smallerAreaLastIndex);
            } else if (nums[index] > target) {
                swap(nums, index, --greaterAreaLastIndex);
            } else {
                index++;
            }
        }
    }

    private void swap(int[] array, int index1, int index2) {
        if (index1 == index2) return;
        array[index1] = array[index1] ^ array[index2];
        array[index2] = array[index1] ^ array[index2];
        array[index1] = array[index1] ^ array[index2];
    }

    public static void main(String[] args) {
        final HollandFlag hollandFlag = new HollandFlag();
//        final int[] array = ArrayHelper.generateRandomArray(10, 100);
        int[] array = {2, 3, 1, 9, 7, 6, 1, 4, 5};
        hollandFlag.hollandFlag(array, 4);
        System.out.println(Arrays.toString(array));
    }

}
