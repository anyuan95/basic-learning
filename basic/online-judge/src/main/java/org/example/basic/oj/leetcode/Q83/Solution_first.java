package org.example.basic.oj.leetcode.Q83;

/**
 * @author anyuan
 * @since 2021-09-18 17:02
 */
class Solution_first {
    public class ListNode {
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
     * 删除重复元素，重复元素只留一个（留的是第一个重复元素）
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE), tail = dummy;
        while (head != null) {
            // 用已填的链表的最后一个节点（tail）的值和head比，不一样才加到tail后面
            // 这样，重复元素加的是第一个
            if (head.val != tail.val) {
                tail.next = head;
                tail = head;
            }
            head = head.next;
        }
        tail.next = null;
        return dummy.next;
    }
}
