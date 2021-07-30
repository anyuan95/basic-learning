package org.example.basic.oj.leetcode.Q160;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author anyuan
 * @since 2021-07-29 14:44
 */
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}

@ToString
@AllArgsConstructor
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}