package org.example.basic.oj.leetcode.Q594;

import java.util.HashMap;
import java.util.Map;

/**
 * @author anyuan
 * @since 2021-11-20 21:56
 */
class Solution {

    /**
     * 思路1：用哈希表记录出现次数，然后遍历取最大连续数量，On
     *
     * 思路2：排序+滑动窗口，ONlogN
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        Map<Integer, Integer> occur = new HashMap<>();
        for (int num : nums) {
//            occur.putIfAbsent(num, 0);
//            occur.compute(num, (k, v) -> v + 1);
            occur.put(num, occur.getOrDefault(num, 0) + 1);
        }
        int maxLen = 0;
        for (Integer k : occur.keySet()) {
            if (occur.containsKey(k + 1)) {
                maxLen = Math.max(maxLen, occur.get(k) + occur.get(k + 1));
            }
        }
        return maxLen;
    }
}
