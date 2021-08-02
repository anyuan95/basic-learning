package org.example.basic.data.structure.stack.model;

import lombok.ToString;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于队列实现栈
 *
 * 思路：
 * 同样使用两个队列倒数据。
 * 创建pushQ和popQ。push时向pushQ中add，pop时从popQ中remove。
 * 每当要进行pop时，在remove前，进行以下操作：
 * 1.将pushQ里除最后一个元素外的所有元素都逐个add到popQ里；
 * 2.然后将pushQ和popQ交换；
 * （这样就保证了每次调用pop时，popQ做remove之前，里面都只有队尾（栈顶）的一个元素）
 *
 * @author anyuan
 * @since 2021-08-01 14:30
 */
@ToString
public class QueueBasedStack<T> implements Stack<T>{

    /**
     * 用linkedList作为队列
     */
    private Queue<T> pushQueue;
    private Queue<T> popQueue;

    public QueueBasedStack() {
        pushQueue = new LinkedList<>();
        popQueue = new LinkedList<>();
    }

    private void shuffleQueues() {
        Queue<T> tempQueue;
        if (popQueue.isEmpty()) {
            while (pushQueue.size() > 1) {
                popQueue.add(pushQueue.remove());
            }
            tempQueue = popQueue;
            popQueue = pushQueue;
            pushQueue = tempQueue;
        }
    }

    @Override
    public T push(T value) {
        pushQueue.add(value);
        return value;
    }

    @Override
    public T pop() {
        if (pushQueue.isEmpty() && popQueue.isEmpty()) {
            throw new IllegalStateException("empty stack");
        }
        shuffleQueues();
        return popQueue.remove();
    }

    @Override
    public T peek() {
        return null;
    }
}
