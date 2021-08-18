package org.example.basic.oj.leetcode.Q19;

import lombok.ToString;

/**
 * 删除倒数第N个链表节点
 *
 * @author anyuan
 * @since 2021-08-18 18:17
 */
class Solution {

    @ToString
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode quick = head, beforeSlow = head;
        for (int i = 0; i < n; i++) {
            quick = quick.next;
        }
        if (quick == null) {
            // 从头向尾走n已经到null了，说明链表总长度是n，那么就是要删除头节点
            return head.next;
        }

        while (quick.next != null) {
            quick = quick.next;
            beforeSlow = beforeSlow.next;
        }
        beforeSlow.next = beforeSlow.next.next;
        return head;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.removeNthFromEnd(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5))))), 1));
        System.out.println(solution.removeNthFromEnd(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5))))), 4));
        System.out.println(solution.removeNthFromEnd(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5))))), 5));
    }
}
