package org.example.basic.oj.leetcode.Q258;

/**
 * @author anyuan
 * @date 2022-03-03 21:43
 */
class Solution {
    public int _addDigits(int num) {
        while (num > 9) {
            int temp = 0;
            // 求所有位加到一起
            while (num != 0) {
                temp += num % 10;
                num /= 10;
            }
            num = temp;
        }
        return num;
    }

    /**
     * 同余定理
     * 假如这个数是xyz，那么这个数就相当于100x+10y+z = 99x+9y+(x+y+z)
     * 我们就是在不断重复这个过程，直到得到的x+y+z ∈ [0,9]  （当然，0是不可能的，除非n就是0）
     * 就相当于不断地从当前数中去掉9k，即等同于%9
     *
     * 如果这个数刚好是9k，最后剩余的结果一定会是9
     * 否则，最终的结果就会是num % 9
     *
     */
    public int addDigits(int num) {
        if (num % 9 == 0) {
            return 9;
        }
        return num % 9;
    }

        public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.addDigits(38));
        System.out.println(solution.addDigits(0));
    }
}
