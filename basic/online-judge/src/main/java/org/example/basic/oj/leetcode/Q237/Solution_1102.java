package org.example.basic.oj.leetcode.Q237;

/**
 * @author anyuan
 * @since 2021-11-02 23:42
 */
class Solution_1102 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
