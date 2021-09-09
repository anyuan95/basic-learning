package org.example.basic.oj.leetcode.Q470;

import java.util.HashMap;
import java.util.Random;

/**
 * @author anyuan
 * @since 2021-09-05 13:52
 */
class Solution_binary {
    public int rand7() {
        return new Random().nextInt(7) + 1;
    }

    public int rand2() {
        final int i = rand7();
        return i == 7 ? rand2() : i % 2;
    }

    /**
     * 通过均匀生成0和1的方式，生成一个随机二进制数，然后如果这个数满足，则返回；如果不满足，则再来一次
     *
     * 该算法在期望的时间复杂度上略差，因为丢弃了太多不符合要求的可能性
     *
     * @return
     */
    public int rand10() {
        int answer = rand2();
        for (int i = 0; i < 3; i++) {
            answer <<= 1;
            answer ^= rand2();
        }
        return (answer <= 10 && answer > 0) ? answer : rand10();
    }

    public static void main(String[] args) {
        final Solution_binary solution = new Solution_binary();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            final int temp = solution.rand10();
            if (map.containsKey(temp)) {
                map.compute(temp, (i1, i2) -> i2 + 1);
            } else {
                map.put(temp, 1);
            }
        }
        System.out.println(map);
    }
}
