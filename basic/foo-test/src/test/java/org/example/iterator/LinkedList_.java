package org.example.iterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author anyuan
 * @since 2021-05-11 15:35
 */
public class LinkedList_<E> implements Collection<E> {

    static class Node<E> {
        Node<E> next;
        E value;

        Node(E value) {
            this.value = value;
        }
    }

    // 头节点前的隐藏节点
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList_() {
        head = null;
        tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0) return false;
        Node<E> node = head;
        while (node != null) {
            if (Objects.equals(node.value, o)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(E e) {
        if (head == null) {
            head = new Node<>(e);
            tail = head;
        } else {
            tail.next = new Node<>(e);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) return false;
        Node<E> node = head, preNode = null;
        while (node != null) {
            if (Objects.equals(node.value, o)) {
                if (preNode == null) {
                    this.head = head.next;
                } else {
                    preNode.next = node.next;
                }
                size--;
                if (size == 0 || size == 1) {
                    tail = head;
                }
                return true;
            }
            preNode = node;
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = true;
        for (E o : c) {
            result &= add(o);
        }
        return result;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedList_<E>.LLItr();
    }

    private class LLItr implements Iterator<E> {
        Node<E> pointer;

        public LLItr() {
            this.pointer = new Node<>(null);
            this.pointer.next = LinkedList_.this.head;
        }

        @Override
        public boolean hasNext() {
            return pointer.next != null;
        }

        @Override
        public E next() {
            E result = pointer.next == null ? null : pointer.next.value;
            pointer = pointer.next;
            return result;
        }
    }

}
