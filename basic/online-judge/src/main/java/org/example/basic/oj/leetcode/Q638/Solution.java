package org.example.basic.oj.leetcode.Q638;

import java.util.*;

/**
 * @author anyuan
 * @since 2021-10-24 16:57
 */
class Solution {
    /**
     * 思路：dfs
     * <p>
     * 递归进行dfs：
     * 首先计算所有需求都单买时的花费；
     * 然后再尝试购买各种礼包。首先需要判断购买该礼包是否满足题目'所有需求都不能多买'的要求，如果满足，则再通过dfs计算剩余需求购买时所需的最少花费。
     * 最后将二者的结果取最小值。
     *
     * @param price
     * @param special
     * @param needs
     * @return
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 其实此处可以进行预先过滤
        // 将[价值大于单买价值和]的礼包去掉，不计入考虑
        return dfs(needs, price, special);
    }

    private HashMap<List<Integer>, Integer> cache = new HashMap<>();

    private int dfs(List<Integer> needs, List<Integer> price, List<List<Integer>> special) {
        if (cache.containsKey(needs)) {
            return cache.get(needs);
        }
        int answer = 0;
        for (int i = 0; i < needs.size(); i++) {
            answer += price.get(i) * needs.get(i);
        }

        sp:
        for (List<Integer> currentSpecial : special) {
            // 购买当前礼包后，还剩余的需求数量
            final ArrayList<Integer> stillNeeds = new ArrayList<>(needs);
            for (int i = 0; i < price.size(); i++) {
                // 如果第i种物品，通过当前礼包买的话数量会超，那就放弃
                if (currentSpecial.get(i) > needs.get(i)) {
                    continue sp;
                } else {
                    stillNeeds.set(i, stillNeeds.get(i) - currentSpecial.get(i));
                }
            }
            answer = Math.min(dfs(stillNeeds, price, special) + currentSpecial.get(price.size()), answer);
        }

//        for (List<Integer> currentSpecial : special) {
//            // 购买当前礼包后，还剩余的需求数量
//            final ArrayList<Integer> stillNeeds = new ArrayList<>(needs);
//            boolean isValid = true;
//            for (int i = 0; i < price.size(); i++) {
//                // 如果第i种物品，通过当前礼包买的话数量会超，那就放弃
//                if (currentSpecial.get(i) > needs.get(i)) {
//                    isValid = false;
//                    break;
//                } else {
//                    stillNeeds.set(i, stillNeeds.get(i) - currentSpecial.get(i));
//                }
//            }
//            if (!isValid) {
//                continue;
//            }
//            answer = Math.min(dfs(stillNeeds, price, special) + currentSpecial.get(price.size()), answer);
//        }



        // 记忆化
        cache.put(needs, answer);
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.shoppingOffers(Arrays.asList(2, 5), Arrays.asList(Arrays.asList(3, 0, 5), Arrays.asList(1, 2, 10)), Arrays.asList(3, 2)));
    }
}
