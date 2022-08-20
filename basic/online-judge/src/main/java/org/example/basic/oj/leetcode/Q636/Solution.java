package org.example.basic.oj.leetcode.Q636;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        final Stack<Integer> stack = new Stack<>();
        final int[] answer = new int[n];
        int lastProgramIndex = Integer.parseInt(logs.get(0).substring(0, logs.get(0).indexOf(":")));
        int lastTimestamp = Integer.parseInt(logs.get(0).substring(logs.get(0).lastIndexOf(":") + 1, logs.get(0).length()));
        String lastStartOrEnd = logs.get(0).substring(logs.get(0).indexOf(":") + 1, logs.get(0).lastIndexOf(":"));
        stack.push(lastProgramIndex);
        for (int i = 1; i < logs.size(); i++) {
            final String log = logs.get(i);
            final int programIndex = Integer.parseInt(log.substring(0, log.indexOf(":")));
            final int timestamp = Integer.parseInt(log.substring(log.lastIndexOf(":") + 1, log.length()));
            final String startOrEnd = log.substring(log.indexOf(":") + 1, log.lastIndexOf(":"));
            if ("start".equals(lastStartOrEnd) && "start".equals(startOrEnd)) {
                // start - start  执行时间记录在前者上
                answer[lastProgramIndex] += timestamp - lastTimestamp;
                // 然后把start压入栈
                stack.add(programIndex);
            } else if ("start".equals(lastStartOrEnd) && "end".equals(startOrEnd)) {
                // start - end  执行时间记录在谁上都一样，按后者算比较方便
                answer[programIndex] += timestamp - lastTimestamp + 1;
                // 然后把start弹出去
                stack.pop();
            } else if ("end".equals(lastStartOrEnd) && "end".equals(startOrEnd)) {
                // end - end  时间记在后者上
                answer[programIndex] += timestamp - lastTimestamp;
                //  然后把对应的start弹出去
                stack.pop();
            } else if ("end".equals(lastStartOrEnd) && "start".equals(startOrEnd)) {
                // end - start  时间记在栈顶元素上，如果没有栈顶元素，就不用记录
                if (!stack.isEmpty()) {
                    answer[stack.peek()] += timestamp - lastTimestamp - 1;
                }
                // 然后把start压入栈
                stack.add(programIndex);
            }
            lastProgramIndex = programIndex;
            lastTimestamp = timestamp;
            lastStartOrEnd = startOrEnd;
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.exclusiveTime(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6"))));
        System.out.println(Arrays.toString(solution.exclusiveTime(1, Arrays.asList("0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"))));
        System.out.println(Arrays.toString(solution.exclusiveTime(2, Arrays.asList("0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"))));
    }
}
