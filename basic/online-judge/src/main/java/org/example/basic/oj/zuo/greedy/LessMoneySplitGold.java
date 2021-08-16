package org.example.basic.oj.zuo.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 分金问题（金条分割的最小代价）
 * <p>
 * 给定一个数组，现要将一根金条切分为数组中的每个项的长度。
 * 切分操作是有代价的，代价为当前要切分的金条的长度。
 * 求切分成数组中各元素长度时，需要的最小代价。
 * <p>
 * 关联问题：哈夫曼树
 *
 * @author anyuan
 * @since 2021-08-13 18:05
 */
class LessMoneySplitGold {


    /**
     * 解法：
     * 将所有元素放到小根堆中，每次取出堆顶最小的两个元素，进行相加，将结果放回小根堆中。
     * 以此往复，直到堆空为止。
     * <p>
     * 所有[重新放入堆中]的元素的和就是目标值。
     * 最佳切分方式就是该小根堆的结构，即将[每个根节点]切分成它的两个直接子节点。
     * <p>
     * 证明见哈夫曼树。
     *
     * @param pieces
     * @return
     */

    private int lessMoneySplitGold(int[] pieces) {
        final PriorityQueue<Integer> golds = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        Arrays.stream(pieces).forEach(golds::add);
        int totalCost = 0;
        while (golds.size() > 1) {
            final int temp = golds.poll() + golds.poll();
            totalCost += temp;
            golds.add(temp);
        }
        return totalCost;
    }
}
