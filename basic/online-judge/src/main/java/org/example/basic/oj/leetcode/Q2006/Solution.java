package org.example.basic.oj.leetcode.Q2006;

import java.util.TreeMap;

class Solution {
    public int __countKDifference(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int answer = 0;
        for (Integer key : map.keySet()) {
            answer += map.get(key) * map.getOrDefault(key + k, 0);
        }
        return answer;
    }

    public int _countKDifference(int[] nums, int k) {
        int[] dict = new int[201];
        for (int num : nums) {
            dict[num]++;
        }
        int ans = 0;
        for (int i = 0; i + k < dict.length; i++) {
            ans += dict[i] * dict[i + k];
        }
        return ans;
    }

    public int countKDifference(int[] nums, int k) {
        int answer = 0;
        // 1~100,101个数，为了方便，不用0
        int[] cnt = new int[102];
        for (int num : nums) {
            if (num > k) {
                answer += cnt[num - k];
            }
            // 题目给的范围，1~100
            if (num + k <= 100) {
                answer += cnt[num + k];
            }
            cnt[num]++;
        }
        return answer;
    }

        public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.countKDifference(new int[]{1, 2, 2, 1}, 1));
    }
}
