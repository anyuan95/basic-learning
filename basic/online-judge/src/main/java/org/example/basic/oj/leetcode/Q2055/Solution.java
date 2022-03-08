package org.example.basic.oj.leetcode.Q2055;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0开始的字符串s，
 * 它只包含字符'*' 和'|'，其中'*'表示一个 盘子，'|'表示一支蜡烛。
 * <p>
 * 同时给你一个下标从 0开始的二维整数数组queries，其中queries[i] = [lefti, righti]表示 子字符串s[lefti...righti]（包含左右端点的字符）。
 * 对于每个查询，你需要找到 子字符串中在 两支蜡烛之间的盘子的 数目。如果一个盘子在 子字符串中左边和右边 都至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间。
 * <p>
 * 比方说，s = "||**||**|*"，查询[3, 8]，表示的是子字符串"*||**|"。
 * 子字符串中在两支蜡烛之间的盘子数目为2，
 * 子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组answer，其中answer[i]是第i个查询的答案。
 * <p>
 * <p>
 * 3 <= s.length <= 105
 * s只包含字符'*' 和'|'。
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 *
 * @author anyuan
 * @date 2022-03-08 16:11
 */
class Solution {

    /**
     * 思路：记录每两个蜡烛之间的盘子数量（为0的不记录），求[i..j]时，分别找到i和j的位置，将二者之间的范围加起来即可
     * <p>
     * TLE, GG
     */
    public int[] __platesBetweenCandles(String s, int[][] queries) {
        // 做一个有序的、可以随时取floor或ceil的集合容器
        // key的含义：区间的开始位置
        // value每个位置的含义：区间的结束位置
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        final char[] chars = s.toCharArray();
        final int n = chars.length;
        int left = 0, right = 0;
        while (left < n && right < n) {
            // 找到一个蜡烛位置
            while (left < n && chars[left] != '|') {
                left++;
            }
            // 重置r的位置，设置成l的右一格
            right = left + 1;
            while (right < n && chars[right] != '|') {
                right++;
            }
            if (right >= n) {
                break;
            }
            // 如果left和right是挨着的，就跳过
            if (left + 1 == right) {
                // 如果是挨着的，那就把left重置到right的位置
                left = right;
            } else {
                // 否则就是说中间有盘子，记录到map里
                treeMap.put(left, right);
                left++;
            }
        }
        final List<Integer> answer = new ArrayList<>();
        for (int[] query : queries) {
            int count = 0;
            final int start = query[0], end = query[1];
            for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
                if (entry.getKey() >= start && entry.getValue() <= end) {
                    count += entry.getValue() - entry.getKey() - 1;
                } else if (entry.getValue() > end) {
                    break;
                }
            }
            answer.add(count);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }


    /**
     * 抄的。
     * 不用treemap了，用一个list记录所有蜡烛的位置，用一个和字符串等长的int数组记录当前位置左边的盘子数量。
     * 然后后面的核心思路还是一样的，改成手动实现二分查找找两个中间蜡烛的位置了，最终结果还是两个位置的前缀和相减。
     *
     * -- 二分查找真神奇啊，真神奇啊（真难写啊
     *
     */
    public int[] _platesBetweenCandles(String s, int[][] queries) {
        final char[] chars = s.toCharArray();
        final int n = chars.length;
        final int[] answer = new int[queries.length];
        final int[] plateCount = new int[n + 1];
        final List<Integer> candleindexes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '|') {
                candleindexes.add(i);
                plateCount[i + 1] = plateCount[i];
            } else if (chars[i] == '*') {
                plateCount[i + 1] = plateCount[i] + 1;
            }
        }
        // 没有蜡烛，返回
        if (candleindexes.size() == 0) {
            return answer;
        }
        for (int i = 0; i < queries.length; i++) {
            final int start = queries[i][0], end = queries[i][1];
            // 开始二分找
            int biggerStart, smallerEnd;
            int left = 0, right = candleindexes.size() - 1;
            // 先找比start大的里最小的
            while (left < right) {
                int mid = (left + right) >> 1;
                if (candleindexes.get(mid) < start) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (candleindexes.get(right) >= start) {
                biggerStart = candleindexes.get(right);
            } else {
                continue;
            }
            // 再找比end小的里最大的
            left = 0;
            right = candleindexes.size() - 1;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                if (candleindexes.get(mid) > end) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            if (candleindexes.get(left) <= end) {
                smallerEnd = candleindexes.get(left);
            } else {
                continue;
            }
            if (biggerStart < smallerEnd) {
                answer[i] = plateCount[smallerEnd] - plateCount[biggerStart];
            }
        }
        return answer;
    }

    /**
     * 猜想：前缀和
     * [0..i]之间有Ai个盘子在蜡烛之间，[0..j]之间有Aj个盘子在蜡烛之间，
     * 则[i..j]之间有Aj-Ai个盘子在蜡烛之间？ --不对
     * <p>
     * 应该改成，找到大于等于i的最小的蜡烛位置i'，找到小于等于j的最大的蜡烛位置j'，则一定有：
     * [i..j]之间的盘子数量等于[i'..j']之间的盘子数量，等同于Aj'-Ai'
     * <p>
     * 偷懒做一个treemap，key是每个蜡烛的位置，value是这个位置左边的所有被蜡烛包围的盘子的总数
     * 然后对于每个query（i,j），找到i'和j'，这两根蜡烛的value的差就是i和j之间的被包围的盘子数量
     * <p>
     * 这题写的真恶心，大杂烩了属于是。
     *
     * @param s
     * @param queries
     * @return
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        final char[] chars = s.toCharArray();
        final int[] answer = new int[queries.length];
        final int n = chars.length;
        // key记录每个蜡烛位置，value记录这个位置左边在蜡烛中间的盘子总数
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int plateCount = 0;
//        for (int i = 0; i < n; i++) {
//            if (chars[i] == '|') {
//                if (treeMap.isEmpty()) {
//                    treeMap.put(i, 0);
//                    plateCount = 0;
//                } else {
//                    treeMap.put(i, plateCount);
//                }
//            } else if (chars[i] == '*') {
//                plateCount++;
//            }
//        }
        int firstCandleIndex = 0;
        while (firstCandleIndex < n && chars[firstCandleIndex] != '|') {
            firstCandleIndex++;
        }
        if (firstCandleIndex < n) {
            treeMap.put(firstCandleIndex, 0);
        } else {
            // 一个蜡烛都没有，直接返回
            return answer;
        }
        for (int i = firstCandleIndex + 1; i < n; i++) {
            if (chars[i] == '*') {
                plateCount++;
            } else if (chars[i] == '|') {
                treeMap.put(i, plateCount);
            }
        }

        if (treeMap.isEmpty()) {
            return answer;
        }

        for (int i = 0; i < queries.length; i++) {
            final int start = queries[i][0], end = queries[i][1];
            if (start == end) {
                answer[i] = 0;
                continue;
            }
            final int leftBigger = treeMap.ceilingKey(start);
            final int rightSmaller = treeMap.floorKey(end);
            if (leftBigger >= rightSmaller) {
                answer[i] = 0;
                continue;
            }
            answer[i] = treeMap.get(rightSmaller) - treeMap.get(leftBigger);
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}})));
        System.out.println(Arrays.toString(solution.platesBetweenCandles("***|**|*****|**||**|*", new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}})));
    }


}
