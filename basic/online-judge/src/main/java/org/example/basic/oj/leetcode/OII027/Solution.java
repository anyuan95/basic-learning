package org.example.basic.oj.leetcode.OII027;

import lombok.ToString;

import java.util.Stack;

/**
 * @author anyuan
 * @since 2021-08-09 14:31
 */
class Solution {

    /**
     * 假设原链表为l1；
     * 翻转链表的后半部分，得到新链表l2；
     * 然后从l1的头和l2的头开始逐个比对；
     * <p>
     * 4 ms
     *
     * @param head
     * @return
     */
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


    /**
     * 直接往栈里扔；
     * 然后链表一边往下走，栈一边往外弹，逐个比对
     * <p>
     * 10 ms
     *
     * @param head
     * @return
     */
    public boolean isPalindrome_Stack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }
        // 由于是回文，所以一定是对称，所以只要校验一半就行
        final int middle = stack.size() / 2;
        for (int i = 0; i < middle; i++) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 哈希表方式？
     * TODO 不懂
     *
     * @param head
     * @return
     */
    public boolean isPalindrome_HashMap(ListNode head) {
        return false;
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

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3,
//                new ListNode(3, new ListNode(2, new ListNode(1, null))))));
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4, new ListNode(4,
                new ListNode(3, new ListNode(2, new ListNode(1, null)))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
        System.out.println(node);
        final long startTime = System.nanoTime();
        System.out.println(solution.isPalindrome_Stack(node));
        System.out.println(System.nanoTime() - startTime);
        System.out.println(node);
    }

}
