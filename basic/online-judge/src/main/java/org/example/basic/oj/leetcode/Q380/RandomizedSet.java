package org.example.basic.oj.leetcode.Q380;

import java.util.*;

class RandomizedSet {
    int[] nums;
    int currentIndex;
    Map<Integer, Integer> map;

    public RandomizedSet() {
        this.map = new HashMap<>();
        this.nums = new int[200010];
        this.currentIndex = -1;
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        nums[++currentIndex] = val;
        map.put(val, currentIndex);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        final Integer index = map.get(val);
        // 交换要删除的位置和最后一个位置
        map.put(nums[currentIndex], index);
        nums[index] = nums[currentIndex];
        currentIndex--;
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return nums[new Random().nextInt(currentIndex + 1)];
    }

    public static void main(String[] args) {
        final RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(0);
        randomizedSet.insert(1);
        randomizedSet.remove(0);
        randomizedSet.insert(2);
        randomizedSet.remove(1);
        System.out.println(randomizedSet.getRandom());
    }
}