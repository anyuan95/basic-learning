package org.example.basic.oj.leetcode.Q430;

import java.util.Stack;

/**
 * @author anyuan
 * @since 2021-09-24 21:39
 */
class Solution_stack {
    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    public Node flatten(Node head) {
        // 存原本的next节点
        Stack<Node> stack = new Stack<>();
        Node curr = head, prev = null;
        while (curr != null) {
            if (curr.child != null) {
                if (curr.next != null) {
                    // 先把原本的next记录到栈里
                    stack.push(curr.next);
                }
                // 然后把next指向child
                curr.next = curr.child;
                // 双向链表，所以next的prev也得改
                curr.next.prev = curr;
                // 记得清空child
                curr.child = null;
            }
            // 然后把curr指向next
            prev = curr;
            curr = curr.next;
        }
        curr = prev;
        // 直到curr是null了，就从栈顶取一个出来，作为curr的next
        while (!stack.isEmpty()) {
            curr.next = stack.pop();
            curr.next.prev = curr;
            // 找到下一个空的位置
            while (curr.next != null) {
                curr = curr.next;
            }
        }
        return head;
    }
}
