package org.example.basic.oj.leetcode.Q846;

import java.util.TreeMap;

/**
 * @author anyuan
 * @date 2021-12-30 22:58
 */
class Solution {
    /**
     * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
     *
     * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
     *
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        final int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int card : hand) {
            treeMap.put(card, treeMap.getOrDefault(card, 0) + 1);
        }
        Integer firstCard = treeMap.firstKey();
        Integer firstCardCount = treeMap.get(firstCard);
        while (treeMap.size() > 0) {
            for (int i = 1; i < groupSize; i++) {
                if (!treeMap.containsKey(firstCard + i)) {
                    return false;
                }
                // 下一张卡数量没有这张卡多
                Integer currentCardCount = treeMap.get(firstCard + i);
                if (currentCardCount < firstCardCount) {
                    return false;
                }
                if (currentCardCount - firstCardCount > 0) {
                    treeMap.put(firstCard + i, currentCardCount - firstCardCount);
                }
            }
        }
        return true;
    }
}
