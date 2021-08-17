package org.example.basic.oj.zuo.recursion;

import java.util.Arrays;

/**
 * 给定一个长度，给定一个起始位置、目标位置、要走的步数；
 * 现有一个每次只能走一步的机器人，走到边界时就只能向反方向走，问一共有多少种走法达到目标位置。
 *
 * @author anyuan
 * @since 2021-08-17 16:44
 */
public class RobotWalk {

    /**
     * @param length 总长度
     * @param start  开始时所在位置（从1开始计数）
     * @param aim    目标位置（从1开始计数）
     * @param times  移动次数
     * @return
     */
    public int ways1(int length, int start, int aim, int times) {
        return process1(start, times, aim, length);
    }

    /**
     * 暴力遍历
     *
     * @param currentIndex 当前位置
     * @param rest         剩余要走的步数
     * @param aim          目标位置
     * @param length       总长度
     * @return 方法数
     */
    private int process1(int currentIndex, int rest, int aim, int length) {
        if (rest == 0) {
            return currentIndex == aim ? 1 : 0;
        }
        if (currentIndex == 1) {
            return process1(currentIndex + 1, rest - 1, aim, length);
        } else if (currentIndex == length) {
            return process1(currentIndex - 1, rest - 1, aim, length);
        } else {
            // 向右走的方法数 + 向左走的方法数
            return process1(currentIndex + 1, rest - 1, aim, length)
                    + process1(currentIndex - 1, rest - 1, aim, length);
        }
    }

    public int ways2(int length, int start, int aim, int times) {
        // 所有子状态区别只在于currentIndex和rest不同，故使用这两个值进行一个缓存记录
        int[][] dp = new int[length + 1][times + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return process2(start, times, aim, length, dp);
    }

    /**
     * 由于存在相同子状态，故可以加上缓存，减少重复子状态的计算
     * 避免对相同子状态的重复计算。
     * <p>
     * 自顶向下的动态规划、记忆化搜索
     *
     * @param currentIndex 当前位置
     * @param rest         剩余要走的步数
     * @param aim          目标位置
     * @param length       总长度
     * @param dp           缓存表，根据[当前位置,剩余要走的步数]，对子状态进行记录
     * @return 方法数
     */
    private int process2(int currentIndex, int rest, int aim, int length, int[][] dp) {
        if (dp[currentIndex][rest] != -1) {
            return dp[currentIndex][rest];
        }
        int answer = 0;
        if (rest == 0) {
            answer = currentIndex == aim ? 1 : 0;
        } else if (currentIndex == 1) {
            answer = process2(currentIndex + 1, rest - 1, aim, length, dp);
        } else if (currentIndex == length) {
            answer = process2(currentIndex - 1, rest - 1, aim, length, dp);
        } else {
            // 向右走的方法数 + 向左走的方法数
            answer = process2(currentIndex + 1, rest - 1, aim, length, dp)
                    + process2(currentIndex - 1, rest - 1, aim, length, dp);
        }
        dp[currentIndex][rest] = answer;
        return answer;
    }

    /**
     * 根据暴力递归的逻辑，可以得到：
     * 假如有二维数组dp，节点[i,j]表示当前在i位置时，剩余j步时的可达路径数，则有以下几种情况：
     * 1.对于所有的[x,0]，仅当x==aim时，结果才为1，其余都为0；
     * 2.对于所有的[1,x]，其值等同于[2,x-1]的值；
     * 3.对于所有的[length,x]，其值等同于[length-1,x-1]的值；
     * 4.对于不满足条件123的[x,y]，其值等同于[x-1,y-1] + [x+1,y-1]；
     * <p>
     * 由以上四点，可以直接构造出数组dp。
     * 而题目要求的结果，即是dp[start][times]位置的值。
     *
     * @param length
     * @param start
     * @param aim
     * @param times
     * @return
     */
    public int ways3(int length, int start, int aim, int times) {
        /* 补充无效参数判断 */
        if (length < 2 || start < 1 || start > length || aim < 1 || aim > length || times < 0) {
            return -1;
        }
        // 所有子状态区别只在于currentIndex和rest不同，故使用这两个值进行一个缓存记录
        int[][] dp = new int[length + 1][times + 1];
        // 填充条件1位置
        dp[aim][0] = 1;
        // 第0行留空，不使用
        for (int rest = 1; rest <= times; rest++) {
            // 填充条件2位置
            dp[1][rest] = dp[2][rest - 1];
            for (int currentIndex = 2; currentIndex < length; currentIndex++) {
                // 填充条件4位置
                dp[currentIndex][rest] = dp[currentIndex - 1][rest - 1] + dp[currentIndex + 1][rest - 1];
            }
            // 填充条件3位置
            dp[length][rest] = dp[length - 1][rest - 1];
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[start][times];
    }

    public static void main(String[] args) {
        final RobotWalk robotWalk = new RobotWalk();
        System.out.println(robotWalk.ways3(5, 2, 4, 6));
    }


}
