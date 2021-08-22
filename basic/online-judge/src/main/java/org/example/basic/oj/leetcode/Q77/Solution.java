package org.example.basic.oj.leetcode.Q77;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * <p>
 * 回溯？
 * <p>
 * 其实都不用回溯，双层for就行？--不对... 是k层for才行
 *
 * @author anyuan
 * @since 2021-08-22 22:33
 */
class Solution {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        process(answer, new ArrayList<>(), 1, n, k);
        return answer;
    }

    /**
     * 思路：场景还原，先加一个值放到list中，然后用这个list继续递归，等递归完成后，回到当前方法，再删掉这个值
     * 每层递归从current~n中选择一个值放进list，然后下一层递归从current+1~n再选一个值放进去，以此类推。
     * <p>
     * 相当于dfs？
     *
     * @param answer  结果
     * @param current 取值范围开始值，即当前值
     * @param n       取值范围结束值，即n
     * @param k       剩余要取的数量
     */
    private void process(List<List<Integer>> answer, List<Integer> currentList, int current, int n, int k) {
        if (currentList.size() == k) {
            answer.add(new ArrayList<>(currentList));
            return;
        }
//        if (currentList.size() + n - current + 1 < k) {
//            // 如果当前剩下的值个数（n - current + 1）已经不足以填满count，则直接返回
//            // 例如，现在currentList中已经有3个值，题目给定n=10，k=5，current本次已经遍历到9，则剩余的值（10 - 9 + 1）= 2 + 3 < 5
//            return;
//        }

        // 剪枝前：69ms+5%，剪枝后：2ms+85%

        // 下一个值选current~end中的一个
        // 将上面的判断挪到下面，将遍历的最大值缩小：当前list中已经选了x个值，总计要k个值，当前遍历的是c，一共有n个值能选，则：
        // 搜索起点的上界 + 接下来要选择的元素个数 - 1 = n
        for (int i = current; i <= n - (k - currentList.size()) + 1; i++) {
            currentList.add(i);
            process(answer, currentList, i + 1, n, k);
            currentList.remove((Object) i);
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.combine(4, 2));
    }

}
