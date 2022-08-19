package org.example.basic.oj.leetcode.Q1656;

import java.util.ArrayList;
import java.util.List;

class OrderedStream {

    String[] values;
    int ptr;
    public OrderedStream(int n) {
        values = new String[n + 1];
        ptr = 1;
    }
    
    public List<String> insert(int idKey, String value) {
        values[idKey] = value;
        List<String> answer = new ArrayList<>();
        if (idKey == ptr) {
            final int k = values.length;
            int i = idKey;
            for (; i < k; i++) {
                if (values[i] == null) {
                    break;
                }
                answer.add(values[i]);
            }
            ptr = i;
        }
        return answer;
    }

    public static void main(String[] args) {
        OrderedStream os= new OrderedStream(5);
        System.out.println(os.insert(3, "ccccc")); // 插入 (3, "ccccc")，返回 []
        System.out.println(os.insert(1, "aaaaa")); // 插入 (1, "aaaaa")，返回 ["aaaaa"]
        System.out.println(os.insert(2, "bbbbb")); // 插入 (2, "bbbbb")，返回 ["bbbbb", "ccccc"]
        System.out.println(os.insert(5, "eeeee")); // 插入 (5, "eeeee")，返回 []
        System.out.println(os.insert(4, "ddddd")); // 插入 (4, "ddddd")，返回 ["ddddd", "eeeee"]
    }
}