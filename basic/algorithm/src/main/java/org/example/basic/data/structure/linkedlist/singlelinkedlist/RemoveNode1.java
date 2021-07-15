package org.example.basic.data.structure.linkedlist.singlelinkedlist;

import org.example.basic.data.structure.linkedlist.model.ListNode;

/**
 * 19 删除倒数第n个节点
 * 解法：递归
 * 逐个移动到尾部，从尾部开始计算尾部到当前节点的长度
 * 当到达目标下一节点（正向看是前一节点）时，把指向其下一节点的指针指向其下下节点
 * @author anyuan
 * @date 2020-11-27 16:55:26
 */
public class RemoveNode1 {

    public static void main(String[] args) {
        ListNode.print(removeNthFromEnd(ListNode.parse(new Integer[]{1,2,4}), 3));

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int length = length(head, n);
        if (length == n) {
            return head.next;
        }
        return head;
    }

    public static int length(ListNode head, int n) {
        if (head == null) {
            return 0;
        }
        final int pos = length(head.next, n) + 1;
        if (pos == n + 1) {
            head.next = head.next.next;
        }
        return  pos;
    }
}
