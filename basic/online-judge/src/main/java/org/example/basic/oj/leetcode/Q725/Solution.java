package org.example.basic.oj.leetcode.Q725;

import lombok.ToString;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-09-22 10:22
 */
class Solution {

    @ToString
    public static class ListNode {
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

    /**
     * 思路：最好的办法可能是通过快慢指针方式
     * 快指针每走k格就记录一下，直到k走到末尾为止
     * --没必要了，那样相当于也是要先遍历一次，而且每走k个位置就要把N/k个位置计数器都进行自增
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        // 链表长度
        int n = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            n++;
        }
        // 应该分为两种情况：
        // 1.n<k时，将每个链表节点都拆开，数组长度就是k
        // 2.n>=k时，数组长度是k，每一段中的链表长度为n/k。对于前n%k个链表，长度为n/k+1
        // -----没必要分开处理
        int eachPartLength = n / k;
        int biggerPartCount = n % k;

        final ListNode[] answer = new ListNode[k];
        int answerIndex = 0;

        curr = head;
        ListNode lastNode = null;

        while (k > 0) {
            answer[answerIndex++] = curr;
            lastNode = curr;
            if (biggerPartCount > 0) {
                curr = curr.next;
                biggerPartCount--;
            }
            for (int i = 0; i < eachPartLength; i++) {
                lastNode = curr;
                curr = curr.next;
            }
            k--;
            if (lastNode != null) {
                lastNode.next = null;
            }
        }

        // 结果是要把链表进行切分，切成长度为n/k或n/k+1的链表
        // 就是说要将尾指针值为null
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println(Arrays.toString(solution.splitListToParts(head1, 5)));
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7, new ListNode(8, new ListNode(9, new ListNode(10))))))))));
        System.out.println(Arrays.toString(solution.splitListToParts(head2, 3)));
    }

}
