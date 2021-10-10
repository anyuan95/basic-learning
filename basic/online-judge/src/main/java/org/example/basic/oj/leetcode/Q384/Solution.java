package org.example.basic.oj.leetcode.Q384;

import java.util.Random;

/**
 * 题目的重点在于如何实现一个完全公平的洗牌算法
 * 当前的实现方式是使用交换的方式，将i位置的元素与[i,len]中任意一个元素交换位置
 *
 * @author anyuan
 * @since 2021-10-05 23:25
 */
class Solution {

    private int[] elements;
    private int[] shuffle;
    private Random random = new Random();

    public Solution(int[] nums) {
        this.elements = nums;
        this.shuffle = nums.clone();
    }

    public int[] reset() {
        return this.elements;
    }

    private int rand(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public int[] shuffle() {
        final int elementCount = this.shuffle.length;
        for (int i = 0; i < elementCount; i++) {
            swap(this.shuffle, i, rand(i, elementCount));
        }
        return this.shuffle;
    }

    private void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
