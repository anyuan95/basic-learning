package org.example.basic.oj.leetcode.Q82;

/**
 * @author anyuan
 * @since 2021-09-18 16:08
 */
class Solution {
    private static class ListNode {
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

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(), previous = dummy, current = head;
        dummy.next = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                // 当前值和下一个值相等
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                previous.next = current.next;
            } else {
                previous = current;
            }
            current = current.next;
        }
        return dummy.next;
    }
}
