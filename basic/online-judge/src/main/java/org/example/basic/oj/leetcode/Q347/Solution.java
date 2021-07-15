package org.example.basic.oj.leetcode.Q347;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new Problem347().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(new Solution().topKFrequent(new int[]{1}, 1)));
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        final HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length - k + 2];
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            final Integer count = entry.getValue();
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(entry.getKey());
        }

        for (int i = buckets.length - 1, j = 0; i >= 0 && j < k; i--) {
            if (buckets[i] != null) {
                for (Integer integer : buckets[i]) {
                    result[j++] = integer;
                    if (j >= k) {
                        return result;
                    }
                }
            }
        }
        return result;
    }
}