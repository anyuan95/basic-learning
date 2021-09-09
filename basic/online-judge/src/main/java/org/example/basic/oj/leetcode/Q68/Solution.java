package org.example.basic.oj.leetcode.Q68;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定单词数组和要切割成的每行长度
 *
 * 这个解法的思路没有问题，只是很多地方都可以优化
 * 比如：List可以替换成words中的left和right指针；
 *
 * 还有一种思路，是换一种方式去列举情况：
 * 1.如果当前行只有一个单词，按照左对齐处理；
 * 2.如果当前行是最后一行，按照左对齐处理；
 * 3.否则就是普通情况，分别计算出：单词总长度、平均空格数量、剩余空格数量，然后根据这三者进行拼接；
 *
 * @author anyuan
 * @since 2021-09-09 09:11
 */
class Solution {
    /**
     * 做一个list，用于保存当前行可以放下的所有字符串
     * 做一个currentLineCount，记录当前行剩余字符空间的长度
     * 考虑当前行结束的情况：
     * 1.如果加上当前字符串后，currentLineCount > maxWidth，则调整list中的字符串空格，将list内容拼装后加到answer中，清空list；
     * 2.循环结束之后，如果list中不为空，则把内容重新拼装一次，添加到answer中（注意最后一行的空格拼装逻辑与前面行不同）；
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> answer = new ArrayList<>();
        List<String> currentLineWords = new ArrayList<>();
        int currentLineCharCount = 0;
        for (String word : words) {
            // 空格在每个word后添加

            // (当前行之前部分的单词+每个词末尾1个空格) + 当前词长度 > 行长度限制
            // 人话：当前list中的内容，再加上当前单词，超过了行限制
            if (currentLineCharCount + word.length() > maxWidth) {
                answer.add(assembleLine(currentLineWords, maxWidth));
                currentLineWords.clear();
                // 由于word还没处理，此处把word加进去
                currentLineWords.add(word);
                // 重置当前行已填写字符数
                // +1是加上末尾空格
                currentLineCharCount = word.length() + 1;
            } else if (currentLineCharCount + word.length() == maxWidth) {
                // 有可能前面的内容加上这个词刚好填满当前行，那就直接加到结果集中
                currentLineWords.add(word);
                answer.add(String.join(" ", currentLineWords));
                currentLineWords.clear();
                currentLineCharCount = 0;
            } else {
                // 加上当前词也填不满当前行
                // 那就直接加上
                currentLineWords.add(word);
                currentLineCharCount += word.length() + 1;
            }
        }
        if (!currentLineWords.isEmpty()) {
            // 还有没处理的，这些只能是最后一行了
            final StringBuilder builder = new StringBuilder(String.join(" ", currentLineWords));
            // 还得补行尾空格
            while (maxWidth - builder.length() > 0) {
                builder.append(" ");
            }
            answer.add(builder.toString());
        }
        return answer;
    }

    /**
     * 拼装当前行
     * 智能插入空格
     * <p>
     * 要求尽可能均匀分配单词间的空格数量。
     * 如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
     *
     * @return
     */
    private String assembleLine(List<String> currentLineWords, int maxWidth) {
        final StringBuilder builder = new StringBuilder(currentLineWords.get(0));
        final int sum = currentLineWords.stream().mapToInt(String::length).sum();
        final int spaceCount = maxWidth - sum;
        final int wordCount = currentLineWords.size();

        // 只有一个元素的话，直接后边接一堆空格就返回。避免下面平分空格的时候出问题
        if (currentLineWords.size() == 1) {
            for (int i = 0; i < spaceCount; i++) {
                builder.append(" ");
            }
            return builder.toString();
        }
        // 平均每个空位补充的空格数量
        final int averageSpaceCount = spaceCount / (wordCount - 1);
        final String averageSpacePart = String.join("", Collections.nCopies(averageSpaceCount, " "));
        // 多出来的部分，从左侧开始逐个补充到空位中
        int otherSpaceCount = spaceCount % (wordCount - 1);

        for (int i = 1; i < wordCount; i++) {
            // 先加空格
            builder.append(averageSpacePart);
            if (otherSpaceCount > 0) {
                builder.append(" ");
                otherSpaceCount--;
            }
            builder.append(currentLineWords.get(i));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(solution.fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do"}, 20));
        System.out.println(solution.fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16));
    }
}
