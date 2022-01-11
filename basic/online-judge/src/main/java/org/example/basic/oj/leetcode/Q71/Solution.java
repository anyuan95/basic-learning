package org.example.basic.oj.leetcode.Q71;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author anyuan
 * @date 2022-01-06 19:30
 */
class Solution {
    /**
     * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
     *
     * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
     *
     * 请注意，返回的 规范路径 必须遵循下述格式：
     *
     * 始终以斜杠 '/' 开头。
     * 两个目录名之间必须只有一个斜杠 '/' 。
     * 最后一个目录名（如果存在）不能 以 '/' 结尾。
     * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
     * 返回简化后得到的 规范路径 。
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        final Stack<String> stack = new Stack<>();
        final String[] pieces = path.split("/");
        for (String piece : pieces) {
            if ("..".equals(piece)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (".".equals(piece) || "".equals(piece)) {
                continue;
            } else {
                stack.push(piece);
            }
        }
        final StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.insert(0,stack.pop());
            builder.insert(0,"/");
        }
        if (builder.length() == 0) {
            return "/";
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.simplifyPath("/a/./b/../../c/"));
        System.out.println(solution.simplifyPath("/home//foo/"));
        System.out.println(solution.simplifyPath("/../"));
        System.out.println(solution.simplifyPath("/home/"));
    }
}
