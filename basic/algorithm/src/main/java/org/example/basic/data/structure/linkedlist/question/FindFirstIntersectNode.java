package org.example.basic.data.structure.linkedlist.question;

/**
 * 查找两个链表相交的头节点
 * 注意：链表中可能有环
 *
 * @author anyuan
 * @since 2021-08-09 22:22
 */
class FindFirstIntersectNode {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 思路：
     * 首先获取两个链表的入环节点，
     * 然后分为如下几种情况：
     * 1.两个链表都没有环
     *
     * @param head1
     * @param head2
     * @return
     */
    private ListNode findFirstIntersectNode(ListNode head1, ListNode head2) {
        final ListNode ringEnter1 = findRingEnterNode(head1);
        final ListNode ringEnter2 = findRingEnterNode(head2);
        if (ringEnter1 == null && ringEnter2 == null) {
            return neitherHasRing(head1, head2);
        } else if (ringEnter1 != null && ringEnter2 != null) {
            return bothHaveRing(head1, ringEnter1, head2, ringEnter2);
        } else {
            // 一个带环，一个不带，不可能有交集
            return null;
        }
    }

    /**
     * 两个链表(a,b)都有环的情况，细分：
     * 1.a和b相交后，过了一段之后才出现环，仅有这种情况下a和b的入环位置相同；
     * 2.a和b从不同的点进入环；
     * 3.a和b相互独立，没有交集；
     *
     * @param head1
     * @param ringEnter1
     * @param head2
     * @param ringEnter2
     * @return
     */
    private ListNode bothHaveRing(ListNode head1, ListNode ringEnter1, ListNode head2, ListNode ringEnter2) {
        if (ringEnter1 == ringEnter2) {
            // 如果二者相等，则说明是情况1
            // 与neitherHasRing时处理类似
            // 相当于将a和b截断，只处理head1-ringEnter1和head2-ringEnter2的部分
            ListNode curr1 = head1, curr2 = head2;
            int lengthDiff = 0;
            while (curr1.next != ringEnter1) {
                curr1 = curr1.next;
                lengthDiff++;
            }
            while (curr2.next != ringEnter2) {
                curr2 = curr2.next;
                lengthDiff--;
            }
            ListNode longerList = lengthDiff > 0 ? head1 : head2;
            ListNode shorterList = longerList == head1 ? head2 : head1;
            while (lengthDiff-- > 0) {
                longerList = longerList.next;
            }
            while (longerList != shorterList) {
                longerList = longerList.next;
                shorterList = shorterList.next;
            }
            return longerList;
        }
        // 如果a和b有交集，那么他们的环一定有交集
        ListNode temp = ringEnter1.next;
        while (temp != ringEnter1) {
            if (temp == ringEnter2) {
                // 从re1往后走，遇到了re2，说明是情况2
                return ringEnter1;
            }
            temp = temp.next;
        }
        // re1一直往后走，如果走了一圈还遇不到re2，说明是情况3
        return null;
    }

    /**
     * 如果两个链表中都没有环，则：
     * 如果两个链表相交，则两个链表最后一个节点一定是相同的
     *
     * @param head1
     * @param head2
     * @return
     */
    private ListNode neitherHasRing(ListNode head1, ListNode head2) {
        ListNode curr1 = head1, curr2 = head2;
        // 记录两个链表的长度差
        int lengthDiff = 0;
        while (curr1.next != null) {
            curr1 = curr1.next;
            lengthDiff++;
        }
        while (curr2.next != null) {
            curr2 = curr2.next;
            lengthDiff--;
        }
        // 如果两个链表的尾节点不同，则两个链表一定是不相交的
        if (curr1 != curr2) {
            return null;
        }
        ListNode longerList = lengthDiff > 0 ? head1 : head2;
        ListNode shorterList = longerList == head1 ? head2 : head1;
        lengthDiff = Math.abs(lengthDiff);
        while (lengthDiff-- > 0) {
            longerList = longerList.next;
        }
        while (longerList != shorterList) {
            longerList = longerList.next;
            shorterList = shorterList.next;
        }
        return longerList;
    }

    /**
     * 找到入环节点。
     * 如果没有环，则返回null。
     *
     * @param head
     * @return
     */
    private ListNode findRingEnterNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode fast = head.next.next, slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        final FindFirstIntersectNode findFirstIntersectNode = new FindFirstIntersectNode();
        ListNode head1 = new ListNode(10);
        ListNode head2 = new ListNode(20);
        ListNode node1_1 = new ListNode(11);
        ListNode node1_2 = new ListNode(12);
        ListNode node1_3 = new ListNode(13);
        ListNode node2_1 = new ListNode(21);
        ListNode ringNode1 = new ListNode(31);
        ListNode ringNode2 = new ListNode(32);
        ListNode ringNode3 = new ListNode(33);
        head1.next = node1_1;
        node1_1.next = node1_2;
        node1_2.next = node1_3;
        head2.next = node2_1;
        ringNode1.next = ringNode2;
        ringNode2.next = ringNode3;
        ringNode3.next = ringNode1;
        node1_3.next = ringNode1;
        // 调整
//        node2_1.next = ringNode1;
//        node2_1.next = ringNode2;
//        node2_1.next = node1_2;
        final ListNode firstIntersectNode = findFirstIntersectNode.findFirstIntersectNode(head1, head2);
        System.out.println(firstIntersectNode == null ? null : firstIntersectNode.val);
    }


}
