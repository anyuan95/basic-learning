package org.example.basic.oj.leetcode.Q881;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-26 17:40
 */
class Solution_slowest {

    public int numRescueBoats(int[] people, int limit) {
        // 按体重升序
        Arrays.sort(people);
        int boatCount = 0;
        boolean[] boated = new boolean[people.length];
        for (int i = 0; i < people.length; i++) {
            // 没上船，且船能装下i
            if (!boated[i] && people[i] <= limit) {
                // 只要有人，船就得上
                boatCount++;
                // 船剩余空间
                int spare = limit - people[i];
                // 从最重的开始往前找
                for (int j = people.length - 1; j > i; j--) {
                    if (!boated[j] && people[j] <= spare) {
                        boated[j] = true;
                        break;
                    }
                }
            }
        }
        return boatCount;
    }
}
