package org.example.basic.oj.leetcode.O22;

import lombok.ToString;

/**
 * @author anyuan
 * @since 2021-09-02 15:01
 */
class Solution {
    @ToString
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        // 考虑给定的k超过了链表长度的情况
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
        System.out.println(solution.getKthFromEnd(head, 3));
    }
}
