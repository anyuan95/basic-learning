package org.example.basic.oj.leetcode.Q49;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author anyuan
 * @since 2021-08-19 17:33
 */
class Solution {
    /**
     * 偷懒：写个哈希函数，交给哈希表去去重
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<String, List<String>> strMap = new HashMap<>();
        for (String str : strs) {
            final char[] tempChars = str.toCharArray();
            Arrays.sort(tempChars);
            final String orderedStr = new String(tempChars);
            final List<String> strList = strMap.computeIfAbsent(orderedStr, s -> new ArrayList<>());
            strList.add(str);
        }
        return new ArrayList<>(strMap.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Function<String, String> sortStringFunc = s -> {
            final char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        };

        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(sortStringFunc))
                .values());
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams2(strs));
    }

}
