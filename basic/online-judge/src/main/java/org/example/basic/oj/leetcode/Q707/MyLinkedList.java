package org.example.basic.oj.leetcode.Q707;

import lombok.ToString;

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 *
 * @author anyuan
 * @since 2021-07-29 19:41
 */
@ToString
class MyLinkedList {

    private int size;
    private ListNode head;
    private ListNode tail;

    class ListNode {
        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        ListNode curr = head;
        while (index-- > 0) {
            curr = curr.next;
        }
        return curr.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        final ListNode node = new ListNode(val);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        final ListNode node = new ListNode(val);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            ListNode curr = head;
            while (index-- > 1) {
                curr = curr.next;
            }
            final ListNode node = new ListNode(val);
            curr.next.prev = node;
            node.next = curr.next;
            node.prev = curr;
            curr.next = node;
            size++;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index > size - 1 || index < 0) {
            return;
        }
        if (index == 0) {
            head = head.next;
        } else if (index == size - 1) {
            tail = tail.prev;
        } else {
            ListNode curr = head;
            while (index-- > 0) {
                curr = curr.next;
            }
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
        size--;
    }

    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(2);
        obj.deleteAtIndex(1);
        obj.addAtHead(2);
        obj.addAtHead(7);
        obj.addAtHead(3);
        obj.addAtHead(2);
        obj.addAtHead(5);
        obj.addAtTail(5);
        obj.get(5);
        obj.deleteAtIndex(6);
        obj.deleteAtIndex(4);
    }
}