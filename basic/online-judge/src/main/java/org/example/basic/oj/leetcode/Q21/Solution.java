package org.example.basic.oj.leetcode.Q21;

import lombok.ToString;

/**
 * @author anyuan
 * @since 2021-07-29 14:11
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode prev = null, next = null, finalHead = null;
        finalHead = l1.val > l2.val ? l2 : l1;
        while (l2 != null && l1 != null) {
            if (l1.val > l2.val) {
                next = l2.next;
                l2.next = l1;
                if (prev != null) {
                    prev.next = l2;
                }
                prev = l2;
                l2 = next;
            } else {
                prev = l1;
                l1 = l1.next;
            }
        }
        if (l1 != null) {
            prev.next = l1;
        } else {
            prev.next = l2;
        }
        return finalHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(7, null))));
        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8, null))));
        System.out.println(new Solution().mergeTwoLists(l1, l2));
    }

}

@ToString
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}