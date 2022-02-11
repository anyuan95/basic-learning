package org.example.basic.oj.leetcode.Q884;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 *
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 *
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织
 *
 * @author anyuan
 * @date 2022-01-30 20:07
 */
class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        HashMap<String, Integer> count = new HashMap<>();
        final String[] s1Parts = s1.split(" ");
        for (String s : s1Parts) {
            count.put(s, count.getOrDefault(s, 0) + 1);
        }
        final String[] s2Parts = s2.split(" ");
        for (String s : s2Parts) {
            count.put(s, count.getOrDefault(s, 0) + 1);
        }
//        return count.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).toArray(String[]::new);
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                ans.add(entry.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }
}
