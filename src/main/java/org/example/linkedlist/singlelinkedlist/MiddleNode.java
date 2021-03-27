package org.example.linkedlist.singlelinkedlist;

import org.example.linkedlist.model.ListNode;

/**
 * 876 链表的中间结点
 * 双指针
 * @author anyuan
 * @date 2020-11-30 13:47:58
 */
public class MiddleNode {

    public static void main(String[] args) {
        ListNode.print(middleNode(ListNode.parse(new Integer[]{1, 2, 3})));
        ListNode.print(middleNode(ListNode.parse(new Integer[]{1, 2, 3, 4})));
        ListNode.print(middleNode(ListNode.parse(new Integer[]{1})));
    }

    public static ListNode middleNode(ListNode head) {
        ListNode quick = head, slow = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
