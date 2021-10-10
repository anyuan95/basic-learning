package org.example.basic.oj.leetcode.Q78;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-09 14:40
 */
class Solution_loop {
    /**
     * 类似地，还是可以用二叉树的结构进行思考：
     * 由于长度为n的数组，一共会有2^n个子集（叶子结点，或者说到从根到叶子结点的路径数）
     * 可以用二进制位的0和1表示是否取该元素（相当于从根到叶子结点的每个路径上选择0(不要)还是选择1(要)）
     * <p>
     * 例如，对于数组（1,2,3），则可以有以下对应方式：
     * 0    000  []
     * 1    001  [3]
     * 2    010  [2]
     * 3    011  [2,3]
     * 4    100  [1]
     * 5    101  [1,3]
     * 6    110  [1,2]
     * 7    111  [1,2,3]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> currentAnswer = new ArrayList<>();
        final int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            int digitCount = n, tempI = i;
            while (digitCount > 0 && tempI > 0) {
                if ((tempI & 1) == 1) {
                    // 如果是1，则取这一位
                    currentAnswer.add(nums[digitCount - 1]);
                }
                tempI >>>= 1;
                digitCount--;
            }
            answer.add(new ArrayList<>(currentAnswer));
            currentAnswer.clear();
        }
        return answer;
    }

    public List<List<Integer>> subsets_lookBetter1(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> currentAnswer = new ArrayList<>();
        final int n = nums.length;
        for (int index = 0; index < (1 << n); index++) {
            for (int digit = 0; digit < n; digit++) {
                // 由于1<<digit只有一位为1，&index的结果只可能是0或1<<digit，如果!=0实际上就相当于说明这一位是1
                if ((index & (1 << digit)) != 0) {
                    currentAnswer.add(nums[digit]);
                }
            }
            answer.add(new ArrayList<>(currentAnswer));
            currentAnswer.clear();
        }
        return answer;
    }

    /**
     * 两种look_better实际上是一样的
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_lookBetter2(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        final int n = nums.length;
        int subSetCount = 1 << n;
        for (int i = 0; i < subSetCount; i++) {
            final List<Integer> currentAnswer = new ArrayList<>();
            // 遍历二进制位
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    // 如果倒数第j位为1，则选
                    // 这里也可以直接把j不断右移，每次直接&1就能取出最后一位了
                    currentAnswer.add(nums[j]);
                }
            }
            answer.add(currentAnswer);
        }
        return answer;
    }

    /**
     * 思路：对于nums中的每个元素，逐个记录当前及之前元素能够组成的所有组合
     * 然后遍历到下一个元素时，相当于把下一个元素与之前所有结果进行一个合并操作
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_ex(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(new ArrayList<>());
        for (int num : nums) {
            final int size = answer.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> temp = new ArrayList<>(answer.get(j));
                temp.add(num);
                answer.add(temp);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution_loop solution = new Solution_loop();
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
    }

}
