package org.example.basic.data.structure.linkedlist.question;

/**
 * 复制带有随机指针的链表
 *
 * @author anyuan
 * @since 2021-08-09 16:38
 */
class CopyRandomLinkedList {

    static class RandomNode {
        int value;
        RandomNode next;
        RandomNode random;

        public RandomNode(int value) {
            this.value = value;
        }

        public RandomNode(int value, RandomNode next) {
            this.value = value;
            this.next = next;
        }

        public RandomNode(int value, RandomNode next, RandomNode random) {
            this.value = value;
            this.next = next;
            this.random = random;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(String.valueOf(value));
            RandomNode current = this.next;
            while (current != null) {
                sb.append("->").append(current.value);
                current = current.next;
            }
            return sb.toString();
        }
    }

    /**
     * 不使用map，在原链表上添加一倍的新节点
     *
     * @param head
     * @return
     */
    public RandomNode copyRandomLinkedList(RandomNode head) {
        RandomNode current = head;
        // 先复制出所有节点
        while (current != null) {
            RandomNode next = current.next;
            // 当前节点指向一个当前节点的复制
            current.next = new RandomNode(current.value, next);
            current = next;
        }
        RandomNode copiedHead = head.next;
        current = head;
        // 补充复制的所有节点的random
        while (current != null) {
            final RandomNode copiedNode = current.next;
            copiedNode.random = current.random == null ? null : current.random.next;
            current = current.next.next;
        }

        current = head;
        // 从原链表中分离复制链表节点（调整next）
        while (current != null) {
            final RandomNode copiedNode = current.next;
            final RandomNode nextCurrent = copiedNode.next;
            current.next = copiedNode.next;
            copiedNode.next = current.next == null ? null : current.next.next;
            current = nextCurrent;
        }

        return copiedHead;
    }

    /**
     * 使用哈希表记录节点关系
     *
     * @param head
     * @return
     */
    public RandomNode copyRandomLinkedList_HashMap(RandomNode head) {
        return head;
    }

    public static void main(String[] args) {
        final CopyRandomLinkedList copyRandomLinkedList = new CopyRandomLinkedList();
        final RandomNode node1 = new RandomNode(1);
        final RandomNode node2 = new RandomNode(2);
        final RandomNode node3 = new RandomNode(3);
        final RandomNode node4 = new RandomNode(4);
        node1.next = node2;
        node1.random = node3;
        node2.next = node3;
        node2.random = node1;
        node3.next = node4;
        node3.random = null;
        System.out.println(copyRandomLinkedList.copyRandomLinkedList(node1));
    }
}
