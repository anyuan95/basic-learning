package org.example.basic.oj.leetcode.Q218_unfinished;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-27 16:25
 */
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> answer = new ArrayList<>();
        for (int[] building : buildings) {
            answer.add(Arrays.asList(building[0], building[2]));
        }












        return answer;
    }
}
