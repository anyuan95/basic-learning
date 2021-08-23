package org.example.basic.oj.leetcode.Q412;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-08-20 16:24
 */
class Solution {

    public List<String> fizzBuzz(int n) {
        List<String> answer = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                answer.add("FizzBuzz");
            } else if (i % 3 == 0) {
                answer.add("Fizz");
            } else if (i % 5 == 0) {
                answer.add("Bzzz");
            } else {
                answer.add(String.valueOf(i));
            }
        }
        return answer;
    }
}
