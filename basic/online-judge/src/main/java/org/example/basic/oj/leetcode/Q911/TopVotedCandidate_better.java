package org.example.basic.oj.leetcode.Q911;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author anyuan
 * @date 2021-12-12 02:00
 */
class TopVotedCandidate_better {

    private Map<Integer, Integer> personVotesMap = new HashMap<>();
    private TreeMap<Integer, Integer> timePersonMap = new TreeMap<>();

    public TopVotedCandidate_better(int[] persons, int[] times) {
        int lastMaxVotedPerson = -1;
        final int n = persons.length;
        for (int i = 0; i < n; i++) {
            final int votes = personVotesMap.getOrDefault(persons[i], 0) + 1;
            personVotesMap.put(persons[i], votes);
            if (personVotesMap.get(lastMaxVotedPerson) == null || votes >= personVotesMap.get(lastMaxVotedPerson)) {
                // 如果大于等于上一个人的票数
                lastMaxVotedPerson = persons[i];
                timePersonMap.put(times[i], persons[i]);
            }
        }
    }

    public int q(int t) {
        final Map.Entry<Integer, Integer> entry = timePersonMap.floorEntry(t);
        return entry == null ? 0 : entry.getValue();
    }
}
