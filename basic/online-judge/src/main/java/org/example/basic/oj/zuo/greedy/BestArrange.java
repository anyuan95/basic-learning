package org.example.basic.oj.zuo.greedy;

import java.util.List;

/**
 * 最多区间问题
 *
 * 会议室安排问题
 * 现有一个数组，数组中每个元素有两个值：start和end，表示会议的开始时间和结束时间。
 * 要求你来安排所有会议，使得这个会议室的会议尽可能地多。要求时间不能有冲突。
 *
 *
 * @author anyuan
 * @since 2021-08-13 17:38
 */
class BestArrange {

    static class Area {
        int start;
        int end;
    }

    /**
     * 能够得到正确最终解的贪心策略：从前到后，每次取结束时间最早的area。
     *
     * @param areas
     * @return
     */
    private int bestArrange(List<Area> areas) {
        return 0;
    }
}
