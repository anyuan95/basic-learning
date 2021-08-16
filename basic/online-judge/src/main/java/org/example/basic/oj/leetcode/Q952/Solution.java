package org.example.basic.oj.leetcode.Q952;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author anyuan
 * @since 2021-08-15 20:19
 */
class Solution {
    /**
     * 思路：并查集
     *
     * 为每个因数保存[与该因数可以相连的集合]的下标，
     * 遍历给的数组，当发现数组中nums[i]的因子m，已经在factorMap中出现过时，将i所在的集合与factorMap中标记的集合进行union操作。
     *
     * @param nums
     * @return
     */
    public int largestComponentSize(int[] nums) {
        final UnionFindSet unionFindSet = new UnionFindSet(nums);
        // key = 因子值，value = 在哪个集合中
        final HashMap<Integer, Integer> factorsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= ((int) Math.sqrt(nums[i])); j++) {
                if (nums[i] % j == 0) {
                    if (j != 1) {
                        if (factorsMap.containsKey(j)) {
                            unionFindSet.union(factorsMap.get(j), i);
                        } else {
                            factorsMap.put(j, i);
                        }
                    }
                    int anotherFactor = nums[i] / j;
                    if (anotherFactor != 1) {
                        if (factorsMap.containsKey(anotherFactor)) {
                            unionFindSet.union(factorsMap.get(anotherFactor), i);
                        } else {
                            factorsMap.put(anotherFactor, i);
                        }
                    }
                }
            }
        }
        return unionFindSet.maxSize();
    }

    static class UnionFindSet {
        private int[] parents;
        private int[] sizes;
        private int[] helper;

        public UnionFindSet(int[] nums) {
            final int length = nums.length;
            parents = new int[length];
            sizes = new int[length];
            helper = new int[length];
            for (int i = 0; i < length; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        private int findAncestor(int current) {
            int helperIndex = 0;
            while (current != parents[current]) {
                helper[helperIndex++] = current;
                current = parents[current];
            }
            for (int index = helperIndex - 1; index >= 0; index--) {
                parents[helper[index]] = current;
            }
            return current;
        }

        private void union(int collection1, int collection2) {
            final int ancestor1 = findAncestor(collection1);
            final int ancestor2 = findAncestor(collection2);
            if (ancestor1 != ancestor2) {
                final int size1 = sizes[ancestor1];
                final int size2 = sizes[ancestor2];
                // 这里必须是大于等于
                int longer = size1 >= size2 ? ancestor1 : ancestor2;
                int shorter = longer == ancestor1 ? ancestor2 : ancestor1;
                parents[shorter] = longer;
                sizes[longer] = size1 + size2;
//                sizes[shorter] = 0;
            }
        }

        public int maxSize() {
            return Arrays.stream(sizes).max().getAsInt();
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.largestComponentSize(new int[]{4, 6, 15, 35}));
//        System.out.println(solution.largestComponentSize(new int[]{2,3,6,7,4,12,21,39}));
//        System.out.println(solution.largestComponentSize(new int[]{99,38,73,74,44,19,25,24,57}));
        System.out.println(solution.largestComponentSize(new int[]{99, 100, 69, 39, 14, 56, 91, 60}));
    }
}
