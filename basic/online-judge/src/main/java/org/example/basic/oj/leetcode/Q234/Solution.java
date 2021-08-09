package org.example.basic.oj.leetcode.Q234;

import lombok.ToString;

/**
 * @author anyuan
 * @since 2021-08-09 15:21
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode quick = head, slow = head;
        while (quick.next != null && quick.next.next != null) {
            // 先找到尾节点
            quick = quick.next.next;
            slow = slow.next;
        }

        // 尾节点就是快指针当前的位置，或是快指针下一个节点的位置
        ListNode tail = quick.next == null ? quick : quick.next;
        // slow的下一个节点作为翻转开始的节点
        ListNode current = slow.next, lastNode = slow;
        // 翻转从slow到quick的部分
        while (current != null) {
            final ListNode next = current.next;
            current.next = lastNode;
            lastNode = current;
            current = next;
        }
        // 使slow的下一节点指向null
        slow.next = null;

        boolean answer = true;
        ListNode palindromeHead = head, palindromeTail = tail;
        while (palindromeHead != null && palindromeTail != null) {
            if (palindromeHead.val != palindromeTail.val) {
                answer = false;
                break;
            }
            palindromeHead = palindromeHead.next;
            palindromeTail = palindromeTail.next;
        }

        // 最后再把链表改回去
        current = tail;
        ListNode nextNode = null;
        while (current != null) {
            final ListNode last = current.next;
            current.next = nextNode;
            nextNode = current;
            current = last;
        }
        return answer;
    }

    @ToString
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
