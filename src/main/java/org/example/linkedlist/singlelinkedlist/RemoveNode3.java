package org.example.linkedlist.singlelinkedlist;

import org.example.linkedlist.model.ListNode;

/**
 * 19 删除倒数第n个节点
 * 解法：双指针
 * 快指针先走n步，慢指针再跟着快指针走，直到快指针走到null，就对慢指针所在的点进行调整
 * @author anyuan
 * @date 2020-11-27 16:55:26
 */
public class RemoveNode3 {

    public static void main(String[] args) {
        ListNode.print(removeNthFromEnd(ListNode.parse(new Integer[]{1, 2, 4}), 1));

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode quick = head, slow = head;
        for (int i = 0; i < n; i++) {
            quick = quick.next;
        }
        if (quick == null) {
            return head.next;
        }
        while (quick.next != null) {
            quick = quick.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

}
