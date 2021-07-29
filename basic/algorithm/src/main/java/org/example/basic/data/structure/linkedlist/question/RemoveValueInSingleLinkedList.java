package org.example.basic.data.structure.linkedlist.question;

import org.example.basic.data.structure.linkedlist.model.SingleLinkedListNode;

import java.util.Objects;

/**
 * @author anyuan
 * @since 2021-07-29 11:59
 */
public class RemoveValueInSingleLinkedList {

    /**
     * 常规逻辑处理。
     * 需要注意：
     * 1.头部可能发生变化；
     * 2.可能整个链表都空了；
     *
     * @param head
     * @param targetValue
     * @param <T>
     * @return
     */
    public <T> SingleLinkedListNode<T> removeValueInSingleLinkedList(SingleLinkedListNode<T> head, T targetValue) {
        SingleLinkedListNode<T> prev = null, finalHead = head;
        while (finalHead != null && Objects.equals(finalHead.value, targetValue)) {
            finalHead = finalHead.next;
        }
        while (head != null) {
            if (Objects.equals(targetValue, head.value)) {
                if (prev != null) {
                    prev.next = head.next;
                }
            }
            prev = head;
            head = head.next;
        }
        return finalHead;
    }

    public static void main(String[] args) {
        final SingleLinkedListNode<Integer> node1 = new SingleLinkedListNode<>();
        final SingleLinkedListNode<Integer> node2 = new SingleLinkedListNode<>();
        final SingleLinkedListNode<Integer> node3 = new SingleLinkedListNode<>();
        final SingleLinkedListNode<Integer> node4 = new SingleLinkedListNode<>();
        node1.value = 3;// 1;
        node2.value = 1;// 2;
        node3.value = 4;// 3;
        node4.value = 3;// 4;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(new RemoveValueInSingleLinkedList().removeValueInSingleLinkedList(node1, 3));
    }
}
