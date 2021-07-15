package org.example;

/**
 * 验证单链表是否是回文串
 * 思路：快慢两根指针同时向链尾移动，快指针每次移动两位，慢指针每次移动一位（慢指针逐渐将走过的节点进行翻转）。
 * 当快指针走到链尾时，慢指针走到链中央，然后从中央开始对两侧的节点逐个进行对比。出现不一致则证明不是回文串。
 */
public class Palindrome {

    static class ListNode {
        int var;
        ListNode next;

        public ListNode(int x) {
            this.var = x;
        }

        public ListNode(int var, ListNode next) {
            this.var = var;
            this.next = next;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        //先找到链表的中间节点，使用两个快慢指针：快指针移动两步，慢指针移动一步
        ListNode pre = null;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        if (fast != null) {
//			fast = fast.next;
            slow = slow.next;
        }

        //这时得到的pre和slow都是在中点
        while (slow != null) {
            if (slow.var != pre.var) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }

    public static void main(String[] args) {
//		1234321
//		System.out.println(isPalindrome(new ListNode(1, new ListNode(2, new ListNode(3,
//				new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1, null))))
//		)))));

//		12344321
        System.out.println(isPalindrome(new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(4, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1, null)))))
        )))));
    }

}