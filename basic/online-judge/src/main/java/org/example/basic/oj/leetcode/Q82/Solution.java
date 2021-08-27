package org.example.basic.oj.leetcode.Q82;

/**
 * 删除升序链表中所有的重复元素
 * <p>
 * 是要去掉所有的重复的
 *
 * @author anyuan
 * @since 2021-08-27 17:38
 */
class Solution {
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

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(), previous = dummy, current = head;
        dummy.next = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                previous.next = current.next;
            } else {
                previous = current;
            }
            current = current.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
//        ListNode head = new ListNode(1,new ListNode(1,new ListNode(1,new ListNode(1,new ListNode(1,new ListNode(1,new ListNode(1)))))));
        ListNode head = new ListNode(0, new ListNode(0, new ListNode(0, new ListNode(0, new ListNode(0, new ListNode(0, new ListNode(0)))))));
//        ListNode head = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(2)))))));

        System.out.println(solution.deleteDuplicates(head));
    }
}
