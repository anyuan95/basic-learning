package org.example.basic.data.structure.linkedlist.singlelinkedlist;

import org.example.basic.data.structure.linkedlist.model.ListNode;

/**
 * 206 单链表反转
 * @author anyuan
 * @date 2020-11-27 09:28:32
 */
public class Reverse {

    /**
     * 单链表反转
     *
     * 链表中环的检测
     *
     * 两个有序的链表合并
     *
     * 删除链表倒数第 n 个结点
     *
     * 求链表的中间结点
     *
     * 206，141，21，19，876
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode.print(reverse(ListNode.parse(new Integer[]{1, 2, 3, 4, 5})));
        ListNode.print(reverse(ListNode.parse(new Integer[]{1})));
        ListNode.print(reverse(ListNode.parse(new Integer[]{})));

    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
