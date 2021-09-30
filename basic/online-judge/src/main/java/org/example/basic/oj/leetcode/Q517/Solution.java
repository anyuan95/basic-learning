package org.example.basic.oj.leetcode.Q517;

/**
 * @author anyuan
 * @since 2021-09-29 22:00
 */
 class Solution {
    /**
     * 思路：
//     * 最终状态一定是所有洗衣机中的值都等于平均值，
//     * 所以相当于每个洗衣机都要调整个。
     * 定义balance，表示[为了达到平衡，需要从i位置左侧向i位置右侧移动的衣服数量]
     * 如果大于0，则说明结果是多余的衣服数量，需要传递给右边；
     * 如果小于0，则说明结果是缺少的衣服数量，需要从右边去拿；
     * balance += (machines[i]-avg)
     *
     * 那么在位置i上的最大操作数就是max(abs(balance), machines[i]-avg)
     * 解释：有可能出现两种情况：
     * 1.i位置只是个中介，即其左右两侧需要经过i位置进行平衡，则i位置最少移动次数就是n次
     * 2.i位置的值过大，需要向两侧移出衣服，则i位置最少移动次数就是machines[i]-target次
     *
     * @param machines
     * @return
     */
    public int findMinMoves(int[] machines) {
        int sum = 0;
        for (int machine : machines) {
            sum += machine;
        }
        if (sum % machines.length != 0) {
            return -1;
        }

        int average = sum / machines.length;

        int answer = 0, balance = 0;
        for (int machine : machines) {
            balance = balance + (machine - average);
            answer = Math.max(answer, Math.max(machine - average, Math.abs(balance)));
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findMinMoves(new int[]{1,0,5}));
        System.out.println(solution.findMinMoves(new int[]{0,3,0}));
        System.out.println(solution.findMinMoves(new int[]{0,3,9}));
        System.out.println(solution.findMinMoves(new int[]{0,2,9}));
        System.out.println(solution.findMinMoves(new int[]{6,0,10,0}));
        System.out.println(solution.findMinMoves(new int[]{1,1,1}));
        System.out.println(solution.findMinMoves(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE}));
    }
}
