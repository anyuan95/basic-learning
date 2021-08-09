package org.example.basic.data.structure.linkedlist.question;

/**
 * @author anyuan
 * @since 2021-08-09 18:30
 */
class CycleLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 寻找链表中环开始的节点（入环节点）
     *
     * @param head
     * @return
     */
    private ListNode findCycleStartNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode quick = head.next.next, slow = head.next;
        while (quick != slow) {
            if (quick.next == null || quick.next.next == null) {
                return null;
            }
            quick = quick.next.next;
            slow = slow.next;
        }

        quick = head;
        while (quick != slow) {
            quick = quick.next;
            slow = slow.next;
        }

        return quick;
    }

}
