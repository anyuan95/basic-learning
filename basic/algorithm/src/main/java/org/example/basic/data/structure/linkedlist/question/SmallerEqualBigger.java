package org.example.basic.data.structure.linkedlist.question;

import org.example.basic.data.structure.linkedlist.LinkedListHelper;
import org.example.basic.data.structure.linkedlist.model.SingleLinkedListNode;

/**
 * 荷兰国旗问题（链表版本）
 * <p>
 * 给定值target
 * 将链表分为左中右三部分，要求：左侧的值都小于target，中间的值都等于target，右边的值都大于target
 *
 * @author anyuan
 * @since 2021-08-09 15:40
 */
class SmallerEqualBigger {

    /**
     * 分成三部分，然后再合并
     *
     * 空间复杂度O(1)
     * 时间复杂度O(N)
     *
     * @param head
     * @return
     */
    public SingleLinkedListNode<Integer> smallerEqualBigger(SingleLinkedListNode<Integer> head, int target) {
        SingleLinkedListNode<Integer>
                smallerPreHead = new SingleLinkedListNode<>(),
                smallerCurrent = smallerPreHead,
                equalPreHead = new SingleLinkedListNode<>(),
                equalCurrent = equalPreHead,
                biggerPreHead = new SingleLinkedListNode<>(),
                biggerCurrent = biggerPreHead,
                current = head;
        while (current != null) {
            SingleLinkedListNode<Integer> next = current.next;
            current.next = null;
            if (current.value < target) {
                smallerCurrent.next = current;
                smallerCurrent = current;
            } else if (current.value == target) {
                equalCurrent.next = current;
                equalCurrent = current;
            } else {
                biggerCurrent.next = current;
                biggerCurrent = current;
            }
            current = next;
        }
        // 需要考虑中间部分为空的情况
        if (equalPreHead.next == null) {
            smallerCurrent.next = biggerPreHead.next;
        } else {
            smallerCurrent.next = equalPreHead.next;
            equalCurrent.next = biggerPreHead.next;
        }


        return smallerPreHead.next;
    }

    /**
     * 将所有内容放到数组中
     * 然后使用普通的荷兰国旗问题（快排）
     * <p>
     * 有空再实现
     *
     * @param head
     * @return
     */
    public SingleLinkedListNode<Integer> smallerEqualBigger_ExternalArray(SingleLinkedListNode<Integer> head, Integer target) {
        return head;
    }

    public static void main(String[] args) {
        final SingleLinkedListNode<Integer> head = LinkedListHelper.parseInteger("3,9,1,7,2,4,6,8,1", ',');
        final SmallerEqualBigger smallerEqualBigger = new SmallerEqualBigger();
        System.out.println(smallerEqualBigger.smallerEqualBigger(head, 10));
    }

}
