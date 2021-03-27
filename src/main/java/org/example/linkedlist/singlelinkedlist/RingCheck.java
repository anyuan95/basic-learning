package org.example.linkedlist.singlelinkedlist;

import org.example.linkedlist.model.ListNode;

/**
 * 141 链表中环的检测
 *
 * @author anyuan
 * @date 2020-11-27 11:08:26
 */
public class RingCheck {

    public static void main(String[] args) {
//        System.out.println(ringCheck(ListNode.parseRing(new Integer[]{1, 2, 3, 4, 5}, -1)));;
        System.out.println(ringCheck(ListNode.parseRing(new Integer[]{1, 2, 3, 4, 5}, 2)));
        ;

    }

    /**
     * 快慢指针
     *
     * @return
     */
    public static boolean ringCheck(ListNode head) {
        ListNode quick = head, slow = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow) {
                return true;
            }
        }
        return false;
    }
}
