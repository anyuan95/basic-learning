package org.example.basic.oj.leetcode.Q206;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author anyuan
 * @since 2021-07-29 14:05
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        final ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(new Solution().reverseList(listNode));
    }
}

@ToString
@AllArgsConstructor
class ListNode {
    int value;
    ListNode next;
}