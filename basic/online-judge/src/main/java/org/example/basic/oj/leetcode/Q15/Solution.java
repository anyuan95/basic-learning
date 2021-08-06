package org.example.basic.oj.leetcode.Q15;

import com.google.common.collect.HashBasedTable;

import java.util.*;

/**
 * 三数之和
 * 获取数组中所有三数之和为指定值的集合
 *
 * @author anyuan
 * @since 2021-08-06 17:06
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new ArrayList<>();
        }
        return threeSum(nums, 0);
    }

    /**
     * 求数组中3数之和为sum的所有集合
     * <p>
     * 不要元素重复的元组
     *
     * @param nums
     * @param sum
     * @return
     */
    private List<List<Integer>> threeSum(int[] nums, int sum) {
        final List<List<Integer>> answers = new ArrayList<>();
//        TreeMap<Integer, Integer> lastIndexMap = new TreeMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            // 只保存每个数字最后一次出现的位置
//            final int currentIndex = i;
//            lastIndexMap.compute(nums[i], (key, oldValue) -> oldValue == null ? currentIndex : Math.max(oldValue, currentIndex));
//        }
//        final Set<Integer> numSet = lastIndexMap.keySet();


        Arrays.sort(nums);
        HashMap<Integer, Integer> lastIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 只保存每个数字最后一次出现的位置
            lastIndexMap.put(nums[i], i);
        }

        // k=value1, v=value2
//        HashMap<Integer, Integer> recordMap = new HashMap<>();
        final HashBasedTable<Integer, Integer, Integer> hashBasedTable = HashBasedTable.create();

        // 先固定第一个指针位置
        for (int index1 = 0; index1 < nums.length; index1++) {
            // 然后固定第二个指针位置
            for (int index2 = index1 + 1; index2 < nums.length; index2++) {
                int needNum3 = sum - nums[index1] - nums[index2];
                if (lastIndexMap.containsKey(needNum3)) {
                    // 第三个数字最后一次出现的位置
                    // 不需要循环，因为结果不要重复元组
                    int currentIndex3 = lastIndexMap.get(needNum3);
                    if (currentIndex3 > index2 && nums[currentIndex3] == needNum3) {
                        if (hashBasedTable.get(nums[index1], nums[index2]) == null
                                || hashBasedTable.get(nums[index1], nums[index2]) != nums[currentIndex3]){
                            ArrayList<Integer> answer = new ArrayList<>();
                            answer.add(nums[index1]);
                            answer.add(nums[index2]);
                            answer.add(nums[currentIndex3]);
                            answers.add(answer);
                            hashBasedTable.put(nums[index1], nums[index2], nums[currentIndex3]);
                        }
                    }
                }
            }
        }
        return answers;
    }

    private boolean threeElementListValueEqual(List<Integer> list1, List<Integer> list2) {
        if (list1 == null || list2 == null) return false;
        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
        int[] arr = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        System.out.println(solution.threeSum(arr));
    }
}
