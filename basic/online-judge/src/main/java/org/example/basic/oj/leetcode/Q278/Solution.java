package org.example.basic.oj.leetcode.Q278;

/**
 * @author anyuan
 * @since 2021-08-12 17:47
 */
class Solution {
    private static int BAD_VERSION = -1;
    private static int checkCount = 0;
    boolean isBadVersion(int version) {
        checkCount++;
        return version == BAD_VERSION;
    }

    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left <= right) {
            final int middle = left + ((right - left) >> 1);
            final boolean badVersion = isBadVersion(middle);
            if (!badVersion) {
                // 如果当前版本没有坏，说明是在后面坏的，把左指针挪到mid+1
                left = middle + 1;
            } else {
                if (middle == 1 || !isBadVersion(middle - 1)) {
                    // 如果当前已经是1了，或者当前节点是坏的，但上一节点不是坏的，则返回当前节点
                    return middle;
                }
                right = middle - 1;
            }
        }
        return -1;
    }

    private int test(int n, int bad) {
        BAD_VERSION = bad;
        return firstBadVersion(n);
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.test(5, 4));
        System.out.println(solution.test(1, 1));
        System.out.println(checkCount);
    }
}
