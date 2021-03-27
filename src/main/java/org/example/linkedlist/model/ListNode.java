package org.example.linkedlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anyuan
 * @date 2020-11-27 09:30:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListNode {
    public Object value;
    public ListNode next;

    public ListNode(Object value) {
        this.value = value;
    }

    public static void print(ListNode listNode) {
        int index = 0;
        while (listNode != null && index < 999) {
            index++;
            System.out.print(listNode.value + " -> ");
            listNode = listNode.next;
        }
        System.out.println("null");
    }

    public static ListNode parse(Object[] objects) {
        final ListNode listNode = new ListNode();
        ListNode pre = listNode;
        for (Object object : objects) {
            final ListNode current = new ListNode(object, null);
            pre.next = current;
            pre = current;
        }
        return listNode.next;
    }

    public static ListNode parseRing(Object[] objects, int position) {
        final ListNode listNode = new ListNode();
        ListNode pre = listNode, ringPointNode = listNode;
        int index = 0;
        for (Object object : objects) {
            final ListNode current = new ListNode(object, null);
            pre.next = current;
            pre = current;
            if (index == position) {
                ringPointNode = current;
            }
            index++;
        }
        if (position >= 0) {
            pre.next = ringPointNode;
        }
        return listNode.next;
    }
}
