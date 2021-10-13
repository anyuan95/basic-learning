package org.example.basic.oj.leetcode.Q29;

class Solution_binarySearch {
    /**
     * 方案1，使用二分方式
     * 由于a和b一定是整数，先不考虑符号问题，分两个部分考虑：
     * - 如果b为0，直接返回错误
     * - 否则b的绝对值一定大于等于1，则a/b一定小于a，即在[0,a]中
     * 综上，首先把二分范围划定在[0,a]中。
     * 然后，假定相除结果为ans，则一定有：
     * - 对于在[0,ans)上的数x，x乘以b一定小于a
     * - 对于在[ans,a]上的数x，x乘以b一定大于等于a
     * <p>
     * 实现上的问题：使用二分方式分段时，需要使用乘法进行check。所以此处使用二进制实现乘法。
     *
     * 算法时间复杂度在O(loga)级别。因为采取的是二分方式，在0和dividend之间逼近。
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        long a = dividend, b = divisor;
        boolean isNegative = false;
        if (a < 0 && b > 0) {
            a = -a;
            isNegative = true;
        } else if (a > 0 && b < 0) {
            b = -b;
            isNegative = true;
        } else if (a < 0 && b < 0) {
            a = -a;
            b = -b;
        }
        long left = 0, right = a;
        while (left < right) {
            // TODO !!! 这里取mid的时候一定要先加一，因为例如[0,1]情况下，如果不加一则会出现mid永远为0的死循环情况。
            long mid = (left + right + 1) >> 1;
            if (mul(b, mid) <= a) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        right = isNegative ? -right : right;
        right = right < Integer.MIN_VALUE || right > Integer.MAX_VALUE ? Integer.MAX_VALUE : right;
        return (int) right;
    }

    /**
     * 通过位运算实现相乘操作
     *
     * @param x
     * @param y
     * @return
     */
    private long mul(long x, long y) {
        long ans = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                // 如果最后一位是1，那就把乘数x加到结果中
                ans += x;
            }
            // y处理完一位了，继续处理下一位
            y >>= 1;
            // 下一次的x就是当前x的二倍了
            x <<= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution_binarySearch solution = new Solution_binarySearch();
        System.out.println(solution.divide(10, 3));
        System.out.println(solution.divide(Integer.MIN_VALUE, -1));
    }
}