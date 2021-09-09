package org.example.basic.oj.leetcode.Q470;

import java.util.HashMap;
import java.util.Random;

/**
 * @author anyuan
 * @since 2021-09-05 14:16
 */
class Solution {
    public int rand7() {
        return new Random().nextInt(7) + 1;
    }

    /**
     * 拒绝采样
     *
     * 通过特定的方式（比如rand7*rand7或相加）得到二维矩阵，通过二维矩阵中的值可以得到每个值的出现概率
     * 然后从得到的矩阵中挑出所有需要得到的目标结果，对需要得到的每种结果的概率进行均匀调整
     */


    /**
     * 通过两次随机函数，以此获取等概率值
     * <p>
     * 这个方法的速度要比binary方法的速度更快，因为这个方法丢弃的概率更少
     * first丢弃1/7，second丢弃2/7
     *
     * @return
     */
    public int rand10() {
        int first, second;
        // first：从1-6中取出一个值，用于判断奇偶性，保证1/2概率
        while ((first = rand7()) > 6) ;
        // second：从1-5中取出一个值，保证每种概率是平均的1/5，根据从first得到的概率，决定直接返回或者返回+5的值
        while ((second = rand7()) > 5) ;
        return (first & 1) == 0 ? second : second + 5;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
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
