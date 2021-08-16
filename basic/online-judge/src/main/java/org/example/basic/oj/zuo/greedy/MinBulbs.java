package org.example.basic.oj.zuo.greedy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 输入：
 * 给定数组，由0和1组成。0表示空地，1表示墙。
 * <p>
 * 要求：
 * 墙可以不被照亮，空地必须被照亮。只有空地可以放灯，灯可以照亮当前格和相邻两格（i-1,i,i+1）。
 * <p>
 * 输出：
 * 求最小要用多少灯泡。
 *
 * @author anyuan
 * @since 2021-08-14 17:06
 */
class MinBulbs {

    private static int minBulbs(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int answer = 0;
        int serial0s = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // 遇到了墙，或者已经到数组末尾了
                // 先加上前边的灯
                answer += (int) Math.ceil(serial0s / 3.0);
                serial0s = 0;
            } else if (nums[i] == 0) {
                serial0s++;
            }
        }
        if (nums[nums.length - 1] == 0) {
            answer += (int) Math.ceil(serial0s / 3.0);
        }
        return answer;
    }

    /**/
    public static int minLight1(int[] nums) {
        return process(nums, 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(int[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 1) { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == 0) {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }
    /**/

    public static int[] random01Array(int len) {
        int length = (int) (Math.random() * len) + 1;
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = Math.random() < 0.5 ? 1 : 0;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(minBulbs(new int[]{0}));

        for (int i = 0; i < 100; i++) {
            final int[] ints = random01Array(20);
            if (minLight1(ints) != minBulbs(ints)) {
                System.out.println("no");
                System.out.println(Arrays.toString(ints));
                return;
            }
        }
        System.out.println("ok");
    }
}
