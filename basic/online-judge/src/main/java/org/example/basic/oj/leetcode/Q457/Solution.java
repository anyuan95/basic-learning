package org.example.basic.oj.leetcode.Q457;

/**
 * @author anyuan
 * @since 2021-08-08 00:20
 */
class Solution {

    /* TODO 学完图之后重新做：*/

    /**
     * 根据题意，我们将每个下标看做“点”，「当前点」和「当前点所能到达的下一个点」看作“边”。
     * <p>
     * 从而将问题转换为经典的「图论寻环」问题，同时又因为每个点出度固定为 11，并且规定「循环」必然是「同向」才合法，因此如果我们在遍历过程中发现存在反向，就停止检查。
     * <p>
     * 作者：AC_OIer
     * 链接：https://leetcode-cn.com/problems/circular-array-loop/solution/gong-shui-san-xie-yi-ti-shuang-jie-mo-ni-ag05/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean circularArrayLoop(int[] nums) {
        return false;
    }
}
