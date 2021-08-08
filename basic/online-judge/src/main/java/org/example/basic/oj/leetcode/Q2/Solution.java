package org.example.basic.oj.leetcode.Q2;

import javax.xml.soap.Node;

/**
 * @author anyuan
 * @since 2021-08-08 21:37
 */
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        final ListNode preHead = new ListNode();
        ListNode lastNode = preHead;
        // 进位
        boolean carry = false;
        while (l1 != null && l2 != null) {
            final int sum = l1.val + l2.val + (carry ? 1 : 0);
            final ListNode tempNode = new ListNode(sum % 10);
            lastNode.next = tempNode;
            lastNode = tempNode;

            carry = sum > 9;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            final int sum = l1.val + (carry ? 1 : 0);
            final ListNode temp = new ListNode(sum % 10);
            lastNode.next = temp;
            lastNode = temp;

            carry = sum > 9;
            l1 = l1.next;
        }
        while (l2 != null) {
            final int sum = l2.val + (carry ? 1 : 0);
            final ListNode temp = new ListNode(sum % 10);
            lastNode.next = temp;
            lastNode = temp;

            carry = sum > 9;
            l2 = l2.next;
        }
        if (carry) {
            lastNode.next = new ListNode(1);
        }
        return preHead.next;
    }

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
}

