package org.example.basic.oj.leetcode.Q780;

/**
 * @author anyuan
 * @date 2022-04-09 22:48
 */
class Solution {
    /**
     * x,y -> x+y, y 或 x, y+x
     * <p>
     * 走第一步，可能有：x,x+y 或 x+y,y
     * 第二步：x,2x+y 2x+y,x+y 或 x+2y,y x+y,x+2y
     * 第三步：x,3x+y 3x+y,2x+y  2x+y,3x+2y 3x+2y,x+y 或 x+3y,y x+2y,x+3y 2x+3y,x+2y x+y,2x+3y
     * 5       7           8       7              5       7        8          7
     * <p>
     * ..........
     * 逆推。。。神仙思路（但是会超时）
     */
    public boolean _reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > 0 && ty > 0) {
            if (tx == sx && ty == sy) {
                return true;
            }
            if (tx > ty) {
                tx -= ty;
            } else {
                ty -= tx;
            }
        }
        return false;
    }

    /**
     * 在1的基础上优化
     * 将重复的减法改成除法
     * a一直减b，直到a<=b   等同于  a-(a-b)/b*b
     * 需要注意差小于1b的情况
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > 0 && ty > 0) {
            if (tx == sx && ty == sy) {
                return true;
            }
            if (tx > ty) {
                tx -= Math.max((tx - sx) / ty, 1) * ty;
            } else {
                ty -= Math.max((ty - sy) / tx, 1) * tx;
            }
        }
        return false;
    }

    public boolean __reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx < ty) ty %= tx;
            else tx %= ty;
        }
        if (tx < sx || ty < sy) return false;
        return sx == tx ? (ty - sy) % tx == 0 : (tx - sx) % ty == 0;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.reachingPoints(1, 1, 3, 5));
        System.out.println(solution.__reachingPoints(10, 5, 15, 5));
    }
}
