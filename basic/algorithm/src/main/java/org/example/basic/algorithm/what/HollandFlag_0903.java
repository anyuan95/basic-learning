package org.example.basic.algorithm.what;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-09-03 14:06
 */
class HollandFlag_0903 {

    /**
     * 简单模式，即只处理一个数（认为数组中等于target的数只有一个）
     *
     * @param nums
     * @param target
     */
    private void hollandFlag(int[] nums, int target) {
        int smallerAreaIndex = -1, currentIndex = 0, n = nums.length;
        while (currentIndex < n) {
            if (nums[currentIndex] < target) {
                swap(nums, currentIndex++, ++smallerAreaIndex);
            } else {
                currentIndex++;
            }
        }
    }

    /**
     * 进阶模式：将整个数组分为三个部分：小于t，等于t，大于t
     *
     * @param nums
     * @param target
     */
    private void hollandFlagEx(int[] nums, int target) {
        int n = nums.length, smallerAreaIndex = -1, biggerAreaIndex = n, currentIndex = 0;
        // 注意结束条件：如果current遇到了bigger区的头，则结束
        while (currentIndex < biggerAreaIndex) {
            if (nums[currentIndex] < target) {
                // 如果当前值小于target，则将当前值移动到small区末尾，然后small区尾索引后移，current索引后移
                swap(nums, ++smallerAreaIndex, currentIndex++);
            } else if (nums[currentIndex] == target) {
                // 如果当前值等于target，则不用交换，直接current往下走
                currentIndex++;
            } else if (nums[currentIndex] > target) {
                // 如果当前值大于target，则将当前值移动到big区头，然后big区头索引前移
                // 注意，这里进行交换后，是把cur位置的值与big区前面的值交换了，并不能确定这个值的大小，所以此时不能直接后移current，还需要再处理一次cur
                swap(nums, --biggerAreaIndex, currentIndex);
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
        final HollandFlag_0903 hf = new HollandFlag_0903();
        final int[] nums = {2, 1, 5, 4, 2, 3, 7, 0, 4};
        hf.hollandFlag(nums, 4);
        System.out.println(Arrays.toString(nums));

        final int[] numsEx = {2, 1, 5, 4, 2, 3, 7, 0, 4};
        hf.hollandFlagEx(numsEx, 4);
        System.out.println(Arrays.toString(numsEx));
    }
}
