package org.example.basic.oj.leetcode.O6;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-07-29 19:31
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        if (head == null) return null;
        int arraySize = 0;
        ListNode curr = head;
        while (curr != null) {
            arraySize++;
            curr = curr.next;
        }
        int[] arr = new int[arraySize];
        for (int index = arraySize - 1; index >= 0; index--) {
            arr[index] = head.val;
            head = head.next;
        }
        return arr;
    }

    public int[] reversePrint_stack(ListNode head) {
        if (head == null) return null;
        int arraySize = 0;
        ListNode curr = head;
        while (curr != null) {
            arraySize++;
            curr = curr.next;
        }
        int[] arr = new int[arraySize];
        for (int index = arraySize - 1; index >= 0; index--) {
            arr[index] = head.val;
            head = head.next;
        }
        return arr;
    }


    public static void main(String[] args) {
        final ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(Arrays.toString(new Solution().reversePrint(listNode)));
    }
}

@ToString
@AllArgsConstructor
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
