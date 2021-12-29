package org.example.basic.oj.leetcode.Q825;

import java.util.Arrays;

/**
 * @author anyuan
 * @date 2021-12-27 11:17
 */
class Solution {
    /***
     * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
     *
     * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
     *
     * age[y] <= 0.5 * age[x] + 7
     * age[y] > age[x]
     * age[y] > 100 && age[x] < 100
     * 否则，x 将会向 y 发送一条好友请求。
     *
     * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
     *
     * 返回在该社交媒体网站上产生的好友请求总数。
     *
     * n == ages.length
     * 1 <= n <= 2 * 104
     * 1 <= ages[i] <= 120
     *
     * @param ages
     * @return
     */
    public int numFriendRequests(int[] ages) {
        /***
         * 思路：先把ages升序排序，然后按握手问题方式遍历，遍历过程中计算出用户i需要发送好友请求的age范围，
         * 再在ages里找到这个范围内有多少个数（并且把自己排出去）
         *
         * 如果任意一个条件为真，就不发送 => 如果全部为假，才发送。即：仅当满足以下三个条件时，才发送。
         * y > 0.5 * x + 7
         * y <= x
         * y <= 100 || x >= 100
         *
         * 相同年龄的人会向相同数量的人进行发送
         */
        Arrays.sort(ages);
        final int n = ages.length;
        int count = 0;
        // 找到最后一个等于或者小于100的
        int index100 = findLastEqualOrSmallerIndex(ages, 100, 0, n - 1);

        int lastAge = -1;
        int lastCount = 0;
        for (int i = 0; i < n; i++) {
            if (ages[i] == lastAge) {
                // 相同年龄的人会向相同数量的人进行发送
                count += lastCount;
                continue;
            } else {
                lastAge = ages[i];
            }

            int minAge = (int) Math.floor(ages[i] * 0.5 + 7);
            // y的下界大于了上界，就没有可选值
            if (minAge > ages[i]) {
                continue;
            }
            // 因为要大于minAge，所以直接找minAge+1位置
            int minIndex = findFirstEqualOrBiggerIndex(ages, minAge + 1, 0, n - 1);
            if (minIndex == -1) {
                // 如果找不到等于或者更大的，说明minAge比最大值还要大，ages里不会有满足大于minAge的值
                // 又因为ages是升序的，那么age[i+1] * 0.5 + 7肯定相同或者更大，肯定还是大于ages里的最大值，后边就肯定没有能互相发送的了
                return count;
            }
            // 最大值就是x，但是需要找到最后一个age[x]的位置
            // 这个值是一定存在的，因为至少会有一个等于age[i]的
            int maxIndex = findLastEqualOrSmallerIndex(ages, ages[i], i, n - 1);

            // 应该就在二者范围内了
            // 如果ages[i]>=100，那就不用管了；如果ages[i]<100，那就得要求ages[y]<=100
            if (ages[i] < 100) {
                // 取原来的结束位置和100里较小的
                maxIndex = Math.min(maxIndex, index100);
            }

            // 拿到的范围应该是左开右开的
            // 如果自己在min,max范围内，减掉自己
            lastCount = maxIndex - minIndex + 1;
            if (ages[i] >= ages[minIndex] && (maxIndex >= n || ages[i] <= ages[maxIndex])) {
                lastCount--;
            }
            count += lastCount;
        }
        return count;
    }

    // -1表示没有等于或者更大的值
    private int findFirstEqualOrBiggerIndex(int[] arr, int value, int start, int end) {
        if (arr[start] >= value) {
            return start;
        }
        int l = start, r = end;
        while (l < r) {
            int middle = (l + r + 1) / 2;
            if (arr[middle] >= value && arr[middle - 1] < value) {
                // 如果上一个比val小，这一个大于或等于val
                return middle;
            } else if (arr[middle] >= value) {
                r = middle - 1;
            } else {
                l = middle;
            }
        }
        if (arr[l] == value) {
            return l;
        }
        return -1;
    }

    // -1表示没有等于或者更小的值
    private int findLastEqualOrSmallerIndex(int[] arr, int value, int start, int end) {
        if (arr[end] <= value) {
            return end;
        }
        int l = start, r = end;
        while (l < r) {
            int middle = (l + r + 1) / 2;
            if (arr[middle] <= value && arr[middle + 1] > value) {
                // 如果下一个比val大，这一个小于或等于val
                return middle;
            } else if (arr[middle] > value) {
                r = middle - 1;
            } else {
                l = middle;
            }
        }
        if (arr[l] == value) {
            return l;
        }
        return -1;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findFirstEqualOrBiggerIndex(new int[]{26, 32, 43, 57, 59, 59, 60, 69, 76, 78, 80, 80, 89, 90, 90, 99, 103, 106, 112, 116}, 59, 0, 19));
        System.out.println(solution.numFriendRequests(new int[]{89, 76, 80, 106, 103, 99, 32, 90, 59, 80, 78, 57, 116, 60, 26, 112, 43, 59, 90, 69}));
        System.out.println(solution.numFriendRequests(new int[]{101, 98, 80, 20, 1, 97, 3, 77, 114, 109}));
        System.out.println(solution.numFriendRequests(new int[]{1, 2, 3, 4, 5, 6}));
//        System.out.println(solution.numFriendRequests(new int[]{16, 16}));
        System.out.println(solution.numFriendRequests(new int[]{16, 17, 18}));
        final int[] ages = {20, 30, 100, 110, 120};
        System.out.println(solution.numFriendRequests(ages));
//        System.out.println(solution.findFirstEqualOrBiggerIndex(ages, 20, 0, 5));
//        System.out.println(solution.findFirstEqualOrBiggerIndex(ages, 20, 1, 5));
//        System.out.println(solution.findFirstEqualOrBiggerIndex(ages, 20, 2, 5));
//        System.out.println(solution.findFirstEqualOrBiggerIndex(ages, 25, 0, 5));
//        System.out.println(solution.findFirstEqualOrBiggerIndex(ages, 25, 1, 5));
//        System.out.println(solution.findFirstEqualOrBiggerIndex(ages, 120, 1, 4));
//        System.out.println(solution.findFirstEqualOrBiggerIndex(ages, 120, 1, 5));
//        System.out.println(solution.findFirstEqualOrBiggerIndex(ages, 125, 0, 4));
    }
}
