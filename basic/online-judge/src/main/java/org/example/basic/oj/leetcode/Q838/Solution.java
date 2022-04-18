package org.example.basic.oj.leetcode.Q838;

import java.util.*;

class Solution {
    public String _pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();
        final int n = chars.length;
        char[] answer = Arrays.copyOf(chars, n);
        boolean needDo = false;
        do {
            needDo = false;
            for (int i = 0; i < chars.length; i++) {
                // 如果当前是.且左边有R或者右边有L
                if (chars[i] == '.') {
                    // 0表示立着，-1表示向左倒，1表示向右倒
                    int flag = 0;
                    // 左边有R
                    if (i > 0 && chars[i - 1] == 'R') {
                        flag += 1;
                        needDo = true;
                    }
                    // 右边有L
                    if (i < n - 1 && chars[i + 1] == 'L') {
                        flag -= 1;
                        needDo = true;
                    }
                    answer[i] = flag == 0 ? '.' : (flag == -1 ? 'L' : 'R');
                }
            }
            char[] temp = answer;
            answer = chars;
            chars = temp;
        } while (needDo);

        return new String(answer);
    }

    public String pushDominoes_bfs(String dominoes) {
        final char[] chars = dominoes.toCharArray();
        final int n = chars.length;
        // 修改时间
        final int[] modTime = new int[n];
        // 0表示立着，-1表示向左倒，1表示向右倒
        final Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '.') {
                continue;
            }
            int dire = chars[i] == 'L' ? -1 : 1;
            queue.add(new int[]{i, 1, dire});
            modTime[i] = 1;
        }

        while (!queue.isEmpty()) {
            final int[] poll = queue.poll();
            int loc = poll[0], time = poll[1], dire = poll[2];
            int nextLoc = loc + dire;
            if (chars[loc] == '.' || nextLoc < 0 || nextLoc >= n) {
                continue;
            }
            if (modTime[nextLoc] == 0) {
                queue.add(new int[]{nextLoc, time + 1, dire});
                modTime[nextLoc] = time + 1;
                chars[nextLoc] = dire == -1 ? 'L' : 'R';
            } else if (modTime[nextLoc] == time + 1) {
                // 说明已经在本轮受过力了，那就一定是竖着的
                chars[nextLoc] = '.';
            }
            System.out.println();
        }
        return new String(chars);
    }

    /**
     * 一个更优的做法，也更好理解
     * 在字符串鹅首尾添加L和R，这一定不会影响最终的结果
     * 对于任意一个'.'，都可以形成X...Y的格式，一共有以下四种情况：
     * L...L
     * R...R
     * L...R
     * R...L
     * 前三种都很简单不用多说，最后一种有两种情况，即RRRRLLLL，或者RRRR.LLLL
     *
     * 注意，实际上每个区间处理的时候，都是只放进去了[L,r)，所以最后只需要去掉前边的L，后边的R没有放进去，不用处理
     *
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        dominoes = 'L' + dominoes + 'R';
        final List<int[]> parts = new ArrayList<>();
        final char[] chars = dominoes.toCharArray();
        final int n = chars.length;
        final StringBuilder builder = new StringBuilder();
        for (int l = 0, r = 1; r < n; r++) {
            // 找到非.的位置，记作r
            if (chars[r] == '.') {
                continue;
            }
            builder.append(chars[l]);
            final int len = r - l - 1;
            // 然后就是分情况讨论四种了
            if (chars[l] == chars[r]) {
                // 1或2
                for (int i = 0; i < len; i++) {
                    builder.append(chars[l]);
                }
            } else if (chars[l] == 'L' && chars[r] == 'R') {
                // 3
                // 中间都是.
                for (int i = 0; i < len; i++) {
                    builder.append('.');
                }
            } else if (chars[l] == 'R' && chars[r] == 'L') {
                // 情况4
                // 再细分，偶数就直接填，奇数就留中间的一个
                for (int i = 0; i < len / 2; i++) {
                    builder.append('R');
                }
                if (len % 2 == 1) {
                    // 说明中间有一位'.'
                    builder.append('.');
                }
                for (int i = 0; i < len / 2; i++) {
                    builder.append('L');
                }
            }
            // 从上一个右侧开始重新找左侧
            l = r;
        }
        builder.deleteCharAt(0);
        return builder.toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.pushDominoes(".L.R...LR..L.."));
//        "LL.RRR.LRR.L..";
//        "LL.RR.LLRRLL..";

    }
}
