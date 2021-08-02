package org.example.basic.data.structure.queue.model;

import lombok.ToString;

import java.util.Stack;

/**
 * 基于栈的队列
 *
 * 思路：
 * 使用两个栈倒数据。
 * 创建一个push栈和一个pop栈，push栈内元素顺序和用户置入数据的顺序一致，pop栈内元素顺序和用户置入数据的顺序相反。
 * 只需要满足以下两点：
 * 1.倒数据时每次都是完整的、不可中断的
 * 2.在每次push操作完成后倒入，或者在每次pop操作前倒入
 *
 * TODO 如何做到线程安全
 *
 * @author anyuan
 * @since 2021-08-01 14:30
 */
@ToString
public class StackBasedQueue<T> implements Queue<T> {

    private Stack<T> pushStack;
    private Stack<T> popStack;

    public StackBasedQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    private void shuffleStack() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    @Override
    public boolean add(T t) {
        pushStack.push(t);
        shuffleStack();
        return true;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return popStack.pop();
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
