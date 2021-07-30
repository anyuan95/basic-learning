package org.example.basic.oj.leetcode.Q160;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 相交链表
 *
 * @author anyuan
 * @since 2021-07-29 14:44
 */
class Solution {
    /**
     * 思路：
     * 假定链表1中不重复的部分为A，链表2中不重复的部分为B，两个链表重复的部分为C
     * 则现有2个指针，分别指向链表1和链表2的head，
     * 则有：指针1走完A+C+B，指针2走完B+C+A，两个指针就都走到了C部分的头的位置。
     * 得到的C位置就是要求的结果。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}

@ToString
@AllArgsConstructor
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}