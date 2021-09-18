package org.example.basic.oj.leetcode.Q83;

/**
 * @author anyuan
 * @since 2021-09-18 16:51
 */
class Solution_last {
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
     * 删除重复元素，重复元素只留一个（留的是最后一个重复元素）
     *
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(), tail = dummy;
        while (head != null) {
            // 不断向tail里加值

            // 如果curr是最后一个值了，或者下一个值和当前值不等
            if (head.next == null || head.val != head.next.val) {
                tail.next = head;
                tail = head;
            } else {
                // 如果有下一个节点，且下一个节点的值和当前节点相等，那就找到相等的节点中的最后一个，把它加到结果中
                // 因为对于连续的多个相同的值，我们实际上是把最后一个值加到结果中的，所以head节点不一定是结果中的头结点，还是需要一个dummy节点
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // 把最后一个重复的加到列表中
                tail.next = head;
                tail = head;
            }
            head = head.next;
        }
        tail.next = null;
        return dummy.next;
    }
}
