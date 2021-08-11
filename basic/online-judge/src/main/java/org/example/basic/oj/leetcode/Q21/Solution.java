package org.example.basic.oj.leetcode.Q21;

import lombok.ToString;

/**
 * 合并两个升序链表
 *
 * @author anyuan
 * @since 2021-08-09 23:14
 */
class Solution {
    @ToString
    static class ListNode {
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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode preHead = new ListNode(), curr = preHead;
        ListNode curr1 = l1, curr2 = l2;
        while (curr1 != null && curr2 != null) {
            if (curr1.val > curr2.val) {
                curr.next = curr2;
                curr2 = curr2.next;
            } else {
                curr.next = curr1;
                curr1 = curr1.next;
            }
            curr = curr.next;
        }
        if (curr1 != null) {
            curr.next = curr1;
        } else {
            curr.next = curr2;
        }
        return preHead.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(-9, new ListNode(3));
        ListNode head2 = new ListNode(5, new ListNode(7));
        final Solution solution = new Solution();
        System.out.println(solution.mergeTwoLists(head1, head2));
    }
}
