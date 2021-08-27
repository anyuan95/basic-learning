package org.example.basic.oj.leetcode.Q881;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-26 17:47
 */
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        // 按体重升序
        Arrays.sort(people);
        int boatCount = 0;
        int lightestPeopleIndex = 0;
        // 从后往前效率应该更高
        for (int i = people.length - 1; i >= lightestPeopleIndex; i--) {
            if (people[i] == limit) {
                // 一人就能撑下一条船
                boatCount++;
            } else if (limit - people[i] >= people[lightestPeopleIndex]) {
                // 如果还能坐下最轻的一个
                boatCount++;
                lightestPeopleIndex++;
            } else {
                // 如果坐不下最轻的人了
                boatCount++;
            }
        }
        return boatCount;
    }
}
