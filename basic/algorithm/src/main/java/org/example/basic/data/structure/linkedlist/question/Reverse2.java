package org.example.basic.data.structure.linkedlist.question;

/**
 * @author anyuan
 * @since 2021-08-31 20:53
 */
public class Reverse2 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseLink(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null, temp = null, current = head;
        while (current != null) {
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        ListNode reversed = reverseLink(n1);
        System.out.println("------");
        while (reversed != null) {
            System.out.print(reversed.val + "->");
            reversed = reversed.next;
        }

    }
}
