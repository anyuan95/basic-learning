package org.example.basic.oj.leetcode.Q1705;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-12-24 23:51
 */
class Solution {
    public int eatenApples(int[] apples, int[] days) {
        int i = 0;
        int eaten = 0;
        int n = apples.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        while (i < n) {
            if (apples[i] > 0) {
                queue.add(new int[]{apples[i], i + days[i]});
            }

            // 清理已经过期的苹果
            while (!queue.isEmpty() && queue.peek()[1] <= i) {
                queue.poll();
            }

            if (!queue.isEmpty()) {
                eaten++;
                queue.peek()[0]--;
                // 吃光了一堆，就弹出
                if (queue.peek()[0] == 0) {
                    queue.poll();
                }
            }
            i++;
        }

        while (!queue.isEmpty()) {
            // 继续清理过期苹果
            while (!queue.isEmpty() && queue.peek()[1] <= i) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                // 离腐烂时间最近的苹果堆还剩下poll[0]个
                // 离过期时间还有poll[1]-i天
                // 二者中较小的就是能吃的个数
                final int[] poll = queue.poll();
                int eatable = Math.min(poll[0], poll[1] - i);
                eaten += eatable;
                i += eatable;
            }
        }
        return eaten;
    }


    public int _eatenApples(int[] apples, int[] days) {
        final int n = apples.length;
        // 只要比较过期时间就行，因为肯定是先吃快要过期的最好
        final PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> (o[1] + o[2])));
        // 预处理
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (apples[i] != 0) {
                // 1：苹果数量；2：是哪一天开始的；3：苹果将在最晚哪天烂掉
                // 则所有苹果都吃完或烂完的时间是，i + min(apple,days)
                // 所以就相当于每天得到的苹果都能组成一个时间段，这个时间段的天数就是这期间能吃的苹果数量
                queue.add(new int[]{apples[i], i, days[i]});
            }
        }
        if (queue.isEmpty()) {
            return 0;
        }

        int currentDay = 0;
        while (!queue.isEmpty()) {
            final int[] poll = queue.poll();
            // poll[1]是当前最早有苹果吃的天
            if (currentDay < poll[1]) {
                currentDay = poll[1];
            }
            // 能吃多少个呢？本应该能吃poll[2]-poll[1]个，不过有些天可能吃的是以前的苹果，所以实际上这一坨里能吃的只有poll[2]-currentDay
            // 现在是第3天，有1个能放到11号的苹果
            // 实际上最多能吃的苹果也就只有min个
            final int eatable = Math.min(poll[0], poll[2]);
            // 首先现在已经保证当前天不会早于拿到这一坨苹果的日子了
            int temp;
            if (currentDay + poll[0] < poll[2] + poll[1]) {
                // 如果全吃掉也不会过期，就全吃掉
                temp = poll[0];
            } else {
                // 如果只能吃一部分，剩下的只能烂掉
                temp = poll[1] + poll[2] - currentDay;
            }
            answer += temp;
            // 好了，这天拿到的苹果能吃的都吃完了，吃掉的数量就是过去的天数
            currentDay += temp;

            // 然后去掉已经烂掉的部分，即：全烂掉的时间在currentDay之前的
            while (!queue.isEmpty() && queue.peek()[1] + queue.peek()[2] < currentDay) {
                queue.poll();
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.eatenApples(new int[]{1, 2, 3, 5, 2}, new int[]{3, 2, 1, 4, 2}));
        System.out.println(solution.eatenApples(new int[]{2, 1, 10}, new int[]{2, 10, 1}));
    }
}

