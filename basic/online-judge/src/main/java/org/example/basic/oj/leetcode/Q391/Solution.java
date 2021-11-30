package org.example.basic.oj.leetcode.Q391;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-11-16 23:33
 */
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        final int[][] rt = new int[n * 2][4];
        // 首先转换原有的矩形坐标数组，转换成左右两侧竖直线的信息
        // [x坐标，竖直线低点y坐标，竖直线高点y坐标，1表示矩形左侧/2表示矩形右侧]
        for (int i = 0, index = 0; i < n; i++) {
            int[] curr = rectangles[i];
            rt[index++] = new int[]{curr[0], curr[1], curr[3], 1};
            rt[index++] = new int[]{curr[2], curr[1], curr[3], -1};
        }
        // 按照横坐标和低点排序
        Arrays.sort(rt, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        // 由于是n个矩形，所以一定会有2n个竖线
        n*=2;
        // 做两个列表，分别保存矩形左侧的线和矩形右侧的线
        final List<int[]> left = new ArrayList<>(), right = new ArrayList<>();
        int l = 0, r= 0;
        while (l < n) {
            r = l;
            left.clear();
            right.clear();
            // 找到横坐标相同的部分
            while (r < n && rt[l][0] == rt[r][0]) {
                r++;
            }
            for (int i = l; i < r; i++) {
                int[] curr = new int[]{rt[i][1],rt[i][2]};
                // 确定当前竖线应该放在哪个列表里
                List<int[]> targetList = rt[i][3] == 1 ? left : right;
                if (targetList.isEmpty()) {
                    targetList.add(curr);
                } else {
                    // 找到同一侧的上一个竖线，将两个竖线做比较
                    int[] prev = targetList.get(targetList.size() - 1);
                    if (curr[0] < prev[1]) {
                        // 有相交部分，就一定是有重叠
                        return false;
                    } else if (curr[0] == prev[1]) {
                        // 能连上，那就加到前一个的上边
                        prev[1] = curr[1];
                    } else {
                        // 连不上，说明这两段中间有间隔，都要留着
                        targetList.add(curr);
                    }
                }
            }
            if (l > 0 && r < n) {
                // 说明不是最两侧的竖线
                // 由于所有能合并的连着的线我们都合并了，所以两侧的线一定得是一样多
                if (left.size() != right.size()) {
                    return false;
                }
                // 而且两侧的线一定都得一样长
                for (int j = 0; j < left.size(); j++) {
                    if (left.get(j)[0] != right.get(j)[0] || left.get(j)[1] != right.get(j)[1]) {
                        return false;
                    }
                }
            } else {
                // 否则就是最两侧的线
                // 首先由于前边是放的x坐标一样的，所以不可能有两侧的线
                // 然后如果这个线是某一侧最边上的线，那么必须是能够连上成整个一条线的
                if (left.size() + right.size() != 1) {
                    return false;
                }
            }
            l = r;
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.isRectangleCover(new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}}));
    }
}
