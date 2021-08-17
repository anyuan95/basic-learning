package org.example.basic.oj.zuo.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 *
 * @author anyuan
 * @since 2021-08-17 14:27
 */
public class PrintAllPermutations {

    /* v1 */
    public List<String> printAllPermutations1(String str) {
        List<String> answer = new ArrayList<>();
        final char[] chars = str.toCharArray();

        List<Character> restChars = new ArrayList<>();
        for (char aChar : chars) {
            restChars.add(aChar);
        }
        process1(restChars, "", answer);
        return answer;
    }

    private void process1(List<Character> restChars, String decided, List<String> answer) {
        if (restChars.isEmpty()) {
            answer.add(decided);
        } else {
            for (int i = 0; i < restChars.size(); i++) {
                final Character current = restChars.get(i);
                restChars.remove(current);
                process1(restChars, decided + current, answer);
                // 恢复现场：在上一行process处理完之后，需要将移除的字符加回来，供下一次再用
                restChars.add(i, current);
            }
        }
    }

    /* v2 */
    public List<String> printAllPermutations2(String str) {
        List<String> answer = new ArrayList<>();
        final char[] chars = str.toCharArray();
        process2(chars, 0, answer);
        return answer;
    }

    private void process2(char[] chars, int index, List<String> answer) {
        if (index == chars.length) {
            answer.add(new String(chars));
        } else {
            for (int i = index; i < chars.length; i++) {
                swap(chars, index, i);
                process2(chars, index + 1, answer);
                // 恢复现场
                swap(chars, index, i);
            }
        }
    }

    /**
     * 在process2的基础上，去重。
     * 添加一个visited数组，如果某个字符串被处理过了，就不再进行处理。
     * 例如，字符串a处理完后，又遇到一个字符串a，就没有必要继续这次交换，因为这次交换的内容都会在第一个a的时候已经处理过了。
     *
     * @param chars
     * @param index
     * @param answer
     */
    private void process3(char[] chars, int index, List<String> answer) {
        if (index == chars.length) {
            answer.add(new String(chars));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < chars.length; i++) {
                if (!visited[chars[i]]) {
                    visited[chars[i]] = true;
                    swap(chars, index, i);
                    process3(chars, index + 1, answer);
                    // 恢复现场
                    swap(chars, index, i);
                }
            }
        }
    }

    private void swap(char[] chars, int index1, int index2) {
        char temp = chars[index1];
        chars[index1] = chars[index2];
        chars[index2] = temp;
    }

    public static void main(String[] args) {
        final PrintAllPermutations printAllPermutations = new PrintAllPermutations();
        System.out.println(printAllPermutations.printAllPermutations1("abcd"));
        System.out.println(printAllPermutations.printAllPermutations2("abcd"));
    }

}
