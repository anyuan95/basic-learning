package org.example.basic.oj.leetcode.Q488;

import java.util.*;

/**
 * @author anyuan
 * @since 2021-11-09 23:49
 */
class Solution {
    /**
     * 完全没有思路
     * 看题解，用bfs就行，但是需要剪枝
     *
     * @param board
     * @param hand
     * @return
     */
    public int findMinStep(String board, String hand) {
        Set<String> seen = new HashSet<>();
        Queue<String[]> queue = new ArrayDeque<>();
        queue.add(new String[]{board,hand});
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final String[] combine = queue.poll();
                // 从手里拿出一个球试着往桌上放
                for (int hIndex = 0; hIndex < combine[1].length(); hIndex++) {
                    String newHand = combine[1].substring(0, hIndex) + combine[1].substring(hIndex+1);
                    // 试着往桌上塞，桌上n个球，是有n+1个可以放的位置的
                    for (int bIndex = 0; bIndex <= combine[0].length(); bIndex++) {
                        String newBoard = combine[0].substring(0, bIndex) + combine[1].charAt(hIndex) + combine[0].substring(bIndex);
                        // 这里做一个记忆，如果处理过这种情况了，就不用再处理了
                        String mark = newBoard + '-' + newHand;
                        if (seen.contains(mark)) {
                            continue;
                        }
                        seen.add(mark);
                        // 然后就是去试一下桌上能不能清空了
                        newBoard = tryClear(newBoard);
                        if (newBoard.length() == 0) {
                            return count;
                        }
                        queue.add(new String[]{newBoard, newHand});
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 尝试去清理能消除的部分
     *
     * @param board
     * @return
     */
    private String tryClear(String board) {
        String temp;
        do {
            temp = board;
            int sameCount = 1;
            char currentChar = board.charAt(0);
            for (int i = 1; i < board.length(); i++) {
                if (board.charAt(i) == currentChar) {
                    sameCount++;
                } else {
                    if (sameCount >= 3) {
                        board = board.substring(0,i-sameCount) + board.substring(i);
                        sameCount = 0;
                        break;
                    }
                    currentChar = board.charAt(i);
                    sameCount = 1;
                }
            }
            // 清除尾部
            if (sameCount >= 3) {
                board = board.substring(0, board.length() - sameCount);
            }
        } while (board.length() >= 3 && board.length() != temp.length());
        return board;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.findMinStep("WRRBBW", "RB"));
    }
}
