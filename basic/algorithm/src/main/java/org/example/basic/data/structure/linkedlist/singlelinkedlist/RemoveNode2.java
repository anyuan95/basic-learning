package org.example.basic.data.structure.linkedlist.singlelinkedlist;

import org.example.basic.data.structure.linkedlist.model.ListNode;

/**
 * 19 删除倒数第n个节点
 * 解法：普通
 * 先计算出整个链表的总长度，然后得到要调整的节点位置序号，再遍历一次
 * @author anyuan
 * @date 2020-11-27 16:55:26
 */
public class RemoveNode2 {

    public static void main(String[] args) {
        ListNode.print(removeNthFromEnd(ListNode.parse(new Integer[]{1,2,4}), 3));

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        final ListNode start = head;
        final int length = length(head);
        if (n == length) {
            return start.next;
        }
        final int targetIndex = length - n - 1;
        for (int i = 0; i < targetIndex; i++) {
            head = head.next;
        }
        head.next = head.next.next;
        return start;
    }

    public static int length(ListNode head) {
        int length = 0;
        while ( head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
