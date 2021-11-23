package org.example.basic.oj.leetcode.Q397;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正整数n ，你可以做如下操作：
 * <p>
 * 如果n是偶数，则用n / 2替换n 。
 * 如果n是奇数，则可以用n + 1或n - 1替换n 。
 * n变为 1 所需的最小替换次数是多少？
 *
 * @author anyuan
 * @date 2021-11-19 11:44
 */
class Solution {
    /**
     * 第一个方式，暴力来。
     * 由于题目给定的限制：
     * - 如果是奇数，就可以选择加一或减一
     * - 如果是偶数，只能除二
     * <p>
     * 其实感觉时间复杂度应该还好？
     * 因为是int，所以实际最高复杂度也就是31
     * <p>
     * 因为最大值是2^31-1，加上1之后就超过int了。
     * 所以用long。（划掉
     * 其实不用，如果出现2^31的话就直接加一除二就好了。给方法的参数还是int的。
     * <p>
     * 惊了。。。
     * 加了个map做记忆化，竟然能直接0ms。。。
     * 为什么呢。。。
     * 好像想通了，假设当前值是2n+1（n为奇数），那么我就要计算Math.min(f(n)+2,f(n-1)+2)；
     * 继续往下算，要计算f(n)，那么就要计算f((n-1)/2)和f((n+1)/2)；
     * 这里就可以看到了，f((n-1)/2)实际上就是f(n-1)+1。
     * 所以用记忆化搜索，能省掉大约一半的计算。而且数越大，节省的计算量就越多。
     * <p>
     * 其实就是个DFS。。。
     * 不过BFS也能做就是了。。。
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        // key=当前值，value=已经走的步数
        if ((long) n == 1) {
            return 0;
        } else if (stepCount.containsKey(n)) {
            return stepCount.get(n);
        } else {
            // 2n+1
            // 直接计算两步，奇数±1之后一定是偶数，一定要除二，所以直接执行±1和除2两步，直接结果+2
            int answer = n % 2 == 0
                    ? (integerReplacement((int) (long) n / 2) + 1)
                    : (Math.min(integerReplacement((int) (((long) n - 1) / 2)) + 2, integerReplacement((int) (((long) n + 1) / 2)) + 2));
            stepCount.put(n, answer);
            return answer;
        }
    }

    private Map<Integer, Integer> stepCount = new HashMap<>();

    /**
     * 另一种方法，从位运算的方向考虑
     * 首先，奇数的最后一位一定是1，偶数的最后一位一定是0
     * 题目的最后目标是获得0000001
     * 对于偶数，我们要做且只能做的操作就是右移（即除以二，即去掉最右侧的一个0）
     * 对于奇数（即最后一位是1），还是有两种情况：
     * - 如果倒数第二位也是1，那肯定是加1能获得更多末尾连续的0。
     * - 如果倒数第二位是0，那如果加1，就会导致倒数第2位变成1。不如减去1好。
     * 特殊的，需要考虑3的情况。3倒数第二位也是1，但是这个1是最后要得到的1（唯一的一个的1）。所以3实际上是减去1会更快。
     *
     * @param n
     * @return
     */
    public int integerReplacement_bin(int n) {
        /* TODO */
        return -1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.integerReplacement(8));
        System.out.println(solution.integerReplacement(2147483647));
    }
}
