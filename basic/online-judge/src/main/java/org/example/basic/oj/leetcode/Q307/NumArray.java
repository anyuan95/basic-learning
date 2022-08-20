package org.example.basic.oj.leetcode.Q307;

class NumArray {
    int[] treeArray;
    int[] nums;

    public NumArray(int[] nums) {
        final int n = nums.length;
        treeArray = new int[n + 1];
        this.nums = nums;
        for (int i = 0; i < n; i++) {
            insert(i + 1, nums[i]);
        }
    }

    private void insert(int index, int value) {
        while (index < treeArray.length) {
            treeArray[index] += value;
            index += lowBit(index);
        }
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    public void update(int index, int val) {
        final int increment = val - this.nums[index];
        int temp = index + 1;
        while (temp < treeArray.length) {
            treeArray[temp] += increment;
            temp += lowBit(temp);
        }
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return getPrefixSum(right + 1) - getPrefixSum(left);
    }

    private int getPrefixSum(int index) {
        int value = 0;
        while (index != 0) {
            value += treeArray[index];
            index -= lowBit(index);
        }
        return value;
    }
}
