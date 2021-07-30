package org.example.basic.oj.leetcode.Q237;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author anyuan
 * @since 2021-07-29 19:06
 */
class Solution {
    /**
     * 脑筋急转弯。。。
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

@ToString
@AllArgsConstructor
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}