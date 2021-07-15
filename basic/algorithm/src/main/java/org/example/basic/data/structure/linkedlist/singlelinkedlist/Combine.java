package org.example.basic.data.structure.linkedlist.singlelinkedlist;

import org.example.basic.data.structure.linkedlist.model.ListNode;

/**
 * 21 两个有序链表合并
 * @author anyuan
 * @date 2020-11-27 16:02:05
 */
public class Combine {

    public static void main(String[] args) {
        ListNode.print(combine(ListNode.parse(new Integer[]{1,2,4}), ListNode.parse(new Integer[]{1,3,4})));
    }

    public static ListNode combine(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode mark = head;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                head.next = l2;
                break;
            } else if (l2 == null) {
                head.next = l1;
                break;
            }
            if ((int) l1.value == (int) l2.value) {
                head.next = new ListNode(l1.value, new ListNode(l2.value));
                head = head.next.next;
                l1 = l1.next;
                l2 = l2.next;
            } else if ((int) l1.value < (int) l2.value) {
                head.next = new ListNode(l1.value);
                head = head.next;
                l1 = l1.next;
            } else {
                head.next = new ListNode(l2.value);
                head = head.next;
                l2 = l2.next;
            }
        }
        return mark.next;
    }

}
