package org.example.basic.oj.leetcode.Q232;

import java.util.Stack;

/**
 * 使用栈实现队列
 *
 * @author anyuan
 * @since 2021-08-25 14:37
 */
class MyQueue {
    /**
    * Your MyQueue object will be instantiated and called as such:
    * MyQueue obj = new MyQueue();
    * obj.push(x);
    * int param_2 = obj.pop();
    * int param_3 = obj.peek();
    * boolean param_4 = obj.empty();
    */

    /**
     * void push(int x) 将元素 x 推到队列的末尾
     * int pop() 从队列的开头移除并返回元素
     * int peek() 返回队列开头的元素
     * boolean empty() 如果队列为空，返回 true ；否则，返回 false
     */


    private Stack<Integer> popStack;
    private Stack<Integer> pushStack;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        popStack = new Stack<>();
        pushStack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        pushStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return popStack.isEmpty() && pushStack.isEmpty();
    }
}
