package org.example.basic.oj.leetcode.Q440;

class Solution {
    /**
     * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getCount(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    public int getCount(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }

//    private static class DictNode {
//        private DictNode[] children;
//
//        DictNode() {
//            this.children = new DictNode[10];
//        }
//    }
}
