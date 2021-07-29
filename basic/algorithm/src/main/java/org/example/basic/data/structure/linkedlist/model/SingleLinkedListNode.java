package org.example.basic.data.structure.linkedlist.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author anyuan
 * @since 2021-07-29 11:19
 */
@ToString
public class SingleLinkedListNode<T> {

    public T value;
    public SingleLinkedListNode<T> next;


}
