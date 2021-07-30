package org.example.basic.oj.leetcode.O18;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author anyuan
 * @since 2021-07-29 19:12
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode curr = head, prev = head;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return head;
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