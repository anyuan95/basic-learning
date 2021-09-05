package org.example.basic.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author anyuan
 * @since 2021-09-03 09:35
 */
class QuickSort {

    /**
     * v1版本：每次只取一个值，将比它小（或等于）的放在左侧，比它大的放在右侧
     */

    /**
     * v2版本：每次取一个值，将比他小的放在左侧，大于的放在右侧，等于的放在中间
     * <p>
     * 思路：在荷兰国旗问题Ex的基础上，
     * 1.从数组中取一个值，将数组调整成[小于部分|等于部分|大于部分]的格式，
     * 2.不断重复以下操作，直到所有位置都调整过了：
     * 2.1.在小于部分取一个值，使用它对小于部分做步骤1的操作；
     * 2.2.在大于部分取一个值，使用它对大于部分做步骤1的操作；
     *
     * @param nums
     * @return
     */
    private static int[] sort(int[] nums) {
        process(nums, 0, nums.length - 1);
        return nums;
    }

    private static void process(int[] nums, int L, int R) {
        if (L >= R) {
            return;
        }
        /**
         * v3: 不再默认使用R位置的值作为target了，改为使用一个随机函数，随机从[L,R]中选择一个值作为target
         * v1/v2时间复杂度高的原因在于--划分值选的差
         * 通过随机函数，将选择到最差划分值的概率进行降低
         *
         * !!!从L-R中随机选择一个值与R进行交换
         */
        swap(nums, R, L + new Random().nextInt(R - L + 1));
        final EqualsAreaInfo info = partition(nums, L, R);
        process(nums, L, info.startIndex - 1);
        process(nums, info.endIndex + 1, R);
    }

    /**
     * 使用nums[R]作为target
     *
     * @param nums
     * @param L
     * @param R
     * @return
     */
    private static EqualsAreaInfo partition(int[] nums, int L, int R) {
        if (L > R) {
            return new EqualsAreaInfo(-1, -1);
        } else if (L == R) {
            return new EqualsAreaInfo(L, R);
        }
        int target = nums[R];
        int currentIndex = L, smallIndex = L - 1, bigIndex = R;
        while (currentIndex < bigIndex) {
            if (nums[currentIndex] < target) {
                swap(nums, currentIndex++, ++smallIndex);
            } else if (nums[currentIndex] == target) {
                currentIndex++;
            } else if (nums[currentIndex] > target) {
                swap(nums, currentIndex, --bigIndex);
            }
        }
        // 从[0,n-2]部分已经调整完了，现在将n-1和big区第一个元素位置交换
        swap(nums, R, bigIndex);
        // 返回等于区的起止位置，起位置一定是small区下一个位置，止位置由于上一句做了交换，所以就是big区第一个位置
        return new EqualsAreaInfo(smallIndex + 1, bigIndex);
    }

    private static void swap(int[] array, int index1, int index2) {
        if (index1 == index2) return;
        array[index1] = array[index1] ^ array[index2];
        array[index2] = array[index1] ^ array[index2];
        array[index1] = array[index1] ^ array[index2];
    }


    static class EqualsAreaInfo {
        int startIndex;
        int endIndex;

        public EqualsAreaInfo(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 9, 7, 6, 1, 4, 5};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
