package org.example.basic.data.structure.linkedlist.question;

import org.example.basic.data.structure.linkedlist.model.SingleLinkedListNode;

/**
 * @author anyuan
 * @since 2021-07-29 11:21
 */
public class ReverseSingleLinkedList {

    public <T> SingleLinkedListNode<T> reverseSingleLinkedList(SingleLinkedListNode<T> head) {
        SingleLinkedListNode<T> curr = null, prev = null;
        while (head != null) {
            curr = head;
            head = head.next;
            curr.next = prev;
            prev = curr;
        }
        return prev;
    }

    public <T> SingleLinkedListNode<T> reverseSingleLinkedList_1(SingleLinkedListNode<T> head) {
        SingleLinkedListNode<T> prev = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            head = next;
            prev = head;
        }
        return prev;
    }

    public static void main(String[] args) {
        final SingleLinkedListNode<Integer> node1 = new SingleLinkedListNode<>();
        final SingleLinkedListNode<Integer> node2 = new SingleLinkedListNode<>();
        final SingleLinkedListNode<Integer> node3 = new SingleLinkedListNode<>();
        final SingleLinkedListNode<Integer> node4 = new SingleLinkedListNode<>();
        node1.value = 1;
        node2.value = 2;
        node3.value = 3;
        node4.value = 4;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(new ReverseSingleLinkedList().reverseSingleLinkedList(node1));
    }
}
