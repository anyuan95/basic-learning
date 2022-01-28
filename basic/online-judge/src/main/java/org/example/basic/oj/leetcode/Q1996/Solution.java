package org.example.basic.oj.leetcode.Q1996;

import java.util.Arrays;

/**
 * @author anyuan
 * @date 2022-01-28 19:22
 */
class Solution {
    /**
     * 题目理解错了，只是求弱角色数量，这个结果的值一定是小于角色的总数量的
     *
     * 把数组里的atk和def都降序排序，遇到atk相等的段，只要atk相等的段中，后边的节点的def小于段首的def，那么这个节点就是弱角色。
     *
     * @param properties
     * @return
     */
    public int numberOfWeakCharacters(int[][] properties) {
        int answer = 0;
        Arrays.sort(properties, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });
        final int n = properties.length;
        // 索引i一直向后跳
        for (int i = 0, maxDef = properties[0][1]; i < n;) {
            int sameAtkIndex = i;
            // 找到同atk段中的最后一个
            // 循环至少会走一次。所以i不用递增，i会随着atk的索引移动
            while (sameAtkIndex < n && properties[i][0] == properties[sameAtkIndex][0]) {
                if (i != 0 && properties[sameAtkIndex][1] < maxDef) {
                    answer++;
                }
                sameAtkIndex++;
            }
            maxDef = Math.max(maxDef, properties[i][1]);
            i = sameAtkIndex;
        }
        return answer;
    }
}
