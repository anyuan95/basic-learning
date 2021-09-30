package org.example.basic.oj.leetcode.Q430;

import org.w3c.dom.Node;

/**
 * @author anyuan
 * @since 2021-09-24 22:07
 */
class Solution_recursive {
    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    /**
     * 递归方法：将current为头的链表扁平化
     *
     * @param current
     */
    private Node process(Node head) {
        Node current = head;
        while (current != null) {
            if (current.child != null) {
                final Node childHead = process(current.child);
                final Node oldNext = current.next;
                current.next = childHead;
                current.child = null;
                childHead.prev = current;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = oldNext;
                // 小心oldNext = null
                if (oldNext != null) {
                    oldNext.prev = current;
                }
            }
            current = current.next;
        }
        return head;
    }

    private Node process_tail(Node head) {
        Node prev = null;
        while (head != null) {
            if (head.child != null) {
                final Node oldNext = head.next;
                head.next = head.child;
                head.child = null;
                head.next.prev = head;

                final Node tail = process_tail(head);
                tail.next = oldNext;
                if (oldNext != null) {
                    oldNext.prev = tail;
                }
                prev = tail;
                head = oldNext;
            } else {
                prev = head;
                head = head.next;
            }
        }
        return prev;
    }

}
