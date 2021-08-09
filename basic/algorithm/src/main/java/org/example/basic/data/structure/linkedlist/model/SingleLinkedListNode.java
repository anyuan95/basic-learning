package org.example.basic.data.structure.linkedlist.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author anyuan
 * @since 2021-07-29 11:19
 */
public class SingleLinkedListNode<T> {

    public T value;
    public SingleLinkedListNode<T> next;

    public SingleLinkedListNode() {
    }

    public SingleLinkedListNode(T value) {
        this.value = value;
    }

    public SingleLinkedListNode(T value, SingleLinkedListNode<T> next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf(value));
        SingleLinkedListNode<T> current = this.next;
        while (current != null) {
            sb.append("->").append(current.value);
            current = current.next;
        }
        return sb.toString();
    }
}
