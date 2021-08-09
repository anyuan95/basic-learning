package org.example.basic.oj.leetcode.Q141;

/**
 * @author anyuan
 * @since 2021-08-09 18:20
 */
class Solution {
    /**
     * 快慢指针
     * <p>
     * 快指针一次2步，慢指针一次1步，直到两者相撞，
     * 然后快指针回到起点，一步一步走，快慢指针遇到的位置就会是起点位置
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode quick = head.next.next, slow = head.next;
        while (quick != slow) {
            if (quick.next == null || quick.next.next == null) {
                return false;
            }
            quick = quick.next.next;
            slow = slow.next;
        }
        return true;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
