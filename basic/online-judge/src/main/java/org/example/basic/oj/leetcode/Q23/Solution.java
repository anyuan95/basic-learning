package org.example.basic.oj.leetcode.Q23;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @date 2021-10-14 10:29
 */
class Solution {
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
     * 思路：做一个最小堆，把所有链表头节点放进去
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }
        if (pq.isEmpty()) {
            return null;
        }
        ListNode dummy = new ListNode(), current = dummy;
        while (!pq.isEmpty()) {
            // 拿出pq里值最小的一个节点，拼接到结果链表上
            final ListNode nextNode = pq.poll();
            current.next = nextNode;
            current = nextNode;
            // 如果这个链表后边还有节点，就把下一个节点加到pq里
            if (nextNode.next != null) {
                pq.add(nextNode.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.mergeKLists(new ListNode[]{new ListNode()}));
        System.out.println(solution.mergeKLists(new ListNode[]{null}));
    }
}
