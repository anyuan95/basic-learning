package org.example.basic.oj.leetcode.Q682;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int calPoints(String[] ops) {
        final List<Integer> points = new ArrayList<>();
        int index = 0;
        for (String op : ops) {
            if ("+".equals(op)) {
                points.add(points.get(index - 1) + points.get(index - 2));
                index++;
            } else if ("D".equals(op)) {
                points.add(points.get(index - 1) * 2);
                index++;
            } else if ("C".equals(op)) {
                points.remove((int) index - 1);
                index--;
            } else {
                points.add(Integer.parseInt(op));
                index++;
            }
        }
        return points.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.calPoints(new String[]{"5", "2", "C", "D", "+"}));
    }
}
