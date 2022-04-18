package org.example.basic.oj.leetcode.Q1001;

import java.util.*;

class Solution {
    /**
     * 可能会有重复的点，重复点只要处理一次即可
     *
     * 其实不用考虑重复值，而且记录的时候只要记录出现次数即可，不需要精准到点
     * <p>
     * 1 <= n <= 109
     * 0 <= lamps.length <= 20000
     * 0 <= queries.length <= 20000
     * lamps[i].length == 2
     * 0 <= rowi, coli < n
     * queries[j].length == 2
     * 0 <= rowj, colj < n
     *
     * @param n
     * @param lamps
     * @param queries
     * @return
     */
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        long N = n;
        // x
        Map<Integer, Integer> aMap = new HashMap<>();
        // y
        Map<Integer, Integer> bMap = new HashMap<>();
        // 斜率=1
        Map<Integer, Integer> cMap = new HashMap<>();
        // 斜率=-1
        Map<Integer, Integer> dMap = new HashMap<>();

        // 把二维表拉平成一维，x*N+y
        Set<Long> points = new HashSet<>();

        // 记录
        for (int i = 0; i < lamps.length; i++) {
            int[] lamp = lamps[i];
            final int x = lamp[0], y = lamp[1];
            final long index = x * N + y;
            // 添加一个判断，如果这个点有灯了，就不再点亮了。通过这种方式去掉重复点
            if (!points.contains(index)) {
                points.add(index);
                aMap.put(x, aMap.getOrDefault(x, 0) + 1);
                bMap.put(y, bMap.getOrDefault(y, 0) + 1);
                // 斜率=1，y=x+b，k=1，b=y-x
                // 斜率=-1，y=-x+b，b=y+x
                final int b1 = y - x, b2 = y + x;
                cMap.put(b1, cMap.getOrDefault(b1, 0) + 1);
                dMap.put(b2, dMap.getOrDefault(b2, 0) + 1);
            }
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            final int targetX = query[0], targetY = query[1];
            final int targetB1 = targetY - targetX, targetB2 = targetY + targetX;
            if (aMap.containsKey(targetX)
                    || bMap.containsKey(targetY)
                    || cMap.containsKey(targetB1)
                    || dMap.containsKey(targetB2)) {
                answer[i] = 1;
            }
            // 清理九格，同时清理所有记录map
            for (int[] direction : DIRECTIONS) {
                final int newX = targetX + direction[0], newY = targetY + direction[1];
                // 由于是拉平了，所以需要校验点是否合法了
                if (newX < 0 || newX >= n || newY < 0 || newY >= n) {
                    continue;
                }
                if (points.contains(newX * N + newY)) {
                    points.remove(newX * N + newY);
                    decrement(aMap, newX);
                    decrement(bMap, newY);
                    decrement(cMap, newY - newX);
                    decrement(dMap, newY + newX);
                }
            }
        }
        return answer;
    }

    public static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private void decrement(Map<Integer, Integer> map, int key) {
        if (!map.containsKey(key)) {
            return;
        }
        if (map.get(key) == 1) {
            map.remove(key);
        } else {
            map.put(key, map.get(key) - 1);
        }
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.gridIllumination(5, new int[][]{{0, 0}, {4, 4}}, new int[][]{{1, 1}, {1, 0}})));
    }

}
