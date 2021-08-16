package org.example.basic.oj.leetcode.Q207;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 课程表
 * 实际上就是判断图中：是否有环
 *
 * @author anyuan
 * @since 2021-08-16 14:33
 */
class SolutionBetter {

    /**
     * 思路：拓扑排序
     * <p>
     * 广度优先遍历
     * <p>
     * 邻接表
     * 性能上确实比自定义图结构高了超过一半
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int canFinishNodeCount = 0;
        int[] inDegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
            adjacency.get(prerequisite[1]).add(prerequisite[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
                canFinishNodeCount++;
            }
        }
        while (!queue.isEmpty()) {
            final Integer from = queue.remove();
            final List<Integer> tos = adjacency.get(from);
            for (Integer to : tos) {
                inDegrees[to]--;
                if (inDegrees[to] == 0) {
                    canFinishNodeCount++;
                    queue.add(to);
                }
            }
        }
        return canFinishNodeCount == numCourses;
    }

    public static void main(String[] args) {
        final SolutionBetter solutionBetter = new SolutionBetter();
        System.out.println(solutionBetter.canFinish(2, new int[][]{{1, 0}}));
    }
}
