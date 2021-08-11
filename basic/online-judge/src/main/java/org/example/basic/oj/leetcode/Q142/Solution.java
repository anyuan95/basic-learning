package org.example.basic.oj.leetcode.Q142;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
 *
 * @author anyuan
 * @since 2021-08-09 22:25
 */
class Solution {

    /**
     * 快指针每次走2格，慢指针每次走1格，如果二者相遇，则说明有环；
     * 相遇后，快指针回到head节点，然后再和慢指针一起每次走1格，二者相遇的节点就是入环的节点；
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode fast = head.next.next, slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
