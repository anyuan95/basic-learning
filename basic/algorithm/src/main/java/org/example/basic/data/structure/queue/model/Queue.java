package org.example.basic.data.structure.queue.model;

/**
 * @author anyuan
 * @since 2021-07-30 17:59
 */
public interface Queue<E> {

    boolean add(E e);

    boolean offer(E e);

    E remove();

    E poll();

    E element();

    E peek();

}
