package org.example.basic.oj.leetcode.Q82;

/**
 * @author anyuan
 * @since 2021-09-18 16:39
 */
class Solution_common {
    private static class ListNode {
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
     * 通用思路：不断向dummy链表尾加节点
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(), tail = dummy;
        while (head != null) {
            // 当head没有下一个节点了，或者head下一个节点的值和当前节点不同。这时这个节点可以加到链表中。
            if (head.next == null || head.val != head.next.val) {
                tail.next = head;
                tail = head;
            } else {
                // 否则：如果下一节点不为空，且当前节点和下一节点相等。这时继续往后找，直到找到一个不等的。
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
            }
            head = head.next;
        }
        // 把最后一个节点后边指向null。因为节点都是一个个挂进来的，很有可能原来的tail节点后面还有重复值，但是没被清掉。
        tail.next = null;
        return dummy.next;
    }
}
