package org.example.basic.oj.leetcode.Q382;

import java.util.Random;

class Solution {

    private ListNode listNode;

    public Solution(ListNode head) {
        this.listNode = head;
    }

    public int getRandom() {
        ListNode cur = this.listNode;
        final int rand = new Random().nextInt(Integer.MAX_VALUE);
        int len = 0;
        while (len < rand && cur != null) {
            cur = cur.next;
            len++;
        }
        // 如果当前已经是空，说明已经是最后一个位置了，rand要比长度大
        if (cur == null) {
            final int target = rand % len;
            cur = listNode;
            for (int i = target; i > 0; i--) {
                cur = cur.next;
            }
        }
        return cur.val;
    }

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
}
