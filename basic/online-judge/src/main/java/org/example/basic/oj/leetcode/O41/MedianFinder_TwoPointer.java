package org.example.basic.oj.leetcode.O41;

/**
 * 思路2：双指针
 * 记录中位数指针位置（size若为奇数，则记录的是中位数；若为偶数，则记录的是左边的中位数），当要插入数据时，找到该数据要插入的位置，以此决定中位数指针应该如何移动
 * 需要返回中位数时，通过size奇偶性进行不同的计算。
 *
 *
 * TODO 使用数组实现还要保持插入数据有序不太现实，但使用arrayList的add又是arrayCopy，感觉并不是最优解。
 *
 * @author anyuan
 * @since 2021-08-17 16:29
 */
public class MedianFinder_TwoPointer {

}
