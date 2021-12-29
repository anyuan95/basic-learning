package org.example.basic.oj.leetcode.Q876;

/**
 * @author anyuan
 * @since 2021-08-17 09:56
 */
class Solution {
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

    public ListNode middleNode(ListNode head) {
        ListNode quick = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            head = head.next;
        }
        return head;
    }

    public static void main(String[] args) {
//        final ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        final ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        final Solution solution = new Solution();
        System.out.println(solution.middleNode(listNode));
    }
}
