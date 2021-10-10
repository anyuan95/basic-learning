package org.example.basic.oj.leetcode.Q352;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 思路：做一个list，list中的每个元素都是一个升序且元素相邻的链表。
 * 那么getIntervals的结果就是将该list中的所有链表的头尾指针拿出来。
 * 插入一个值的方式为，先在list中查找这个值的位置，如果不存在这个值，那么找到比这个值小的链表的尾，和比这个值大的链表的头，看看是否能拼到一起：
 * - 如果不能，就在二者中间插入一个新的元素
 * - 如果能，就将该值合并到前面的链表或者后面的链表中
 */
class SummaryRanges {

    private final LinkedList<Node> nodeList;

    public SummaryRanges() {
        nodeList = new LinkedList<>();
    }

    public void addNum(int val) {
        if (nodeList.isEmpty()) {
            nodeList.add(new Node(val));
        } else {
            Node currentNode = new Node(val);
            // 先找到应该添加的位置，即大于val的位置
            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).val > val) {
                    // 找到比这个值大的第一个值
                    // 然后将当前值插入到这个位置中

                    // 标记当前节点是否在nodeList中是一个单独的节点
                    boolean currentNodeIsSingleton = true;
                    int greaterIndex = i, smallerIndex = i - 1;
                    Node greaterNode = nodeList.get(greaterIndex);
                    // 应该先往smaller里做尾插
                    if (smallerIndex >= 0) {
                        final Node smallerNode = nodeList.get(smallerIndex);
                        // smaller链表的尾节点
                        final Node smallerNodeTail = smallerNode.prev;
                        final int smallerNodeTailVal = smallerNodeTail.val;
                        // 如果当前值比smaller的尾节点大超过1，那么就不能合并，需要单独放节点
                        // 如果当前值比smaller的尾节点刚好大1，那么就把val追加到smaller的尾部
                        // 如果当前值比smaller的尾节点小，那就说明val一定已经存在了，就不用处理
                        // 后两种情况可以进行合并：直接在链表中找到[应该在哪个节点之后插入该节点]；
                        if (val - smallerNodeTailVal == 1) {
                            smallerNodeTail.next = currentNode;
                            currentNode.prev = smallerNodeTail;
                            currentNode.next = smallerNode;
                            smallerNode.prev = currentNode;
                            currentNodeIsSingleton = false;
                        } else if (val - smallerNodeTailVal > 1) {
                            // 单独加一个节点
                            nodeList.add(i, currentNode);
                        }
                    } else {
                        nodeList.add(i, currentNode);
                    }

                    // 然后开始处理greater
                    // 如果val比greater的headVal小1，则说明curr应该放在greater的头
                    if (greaterNode.val - val == 1) {
                        if (currentNodeIsSingleton) {
                            currentNode.next = greaterNode;
                            currentNode.prev = greaterNode.prev;
                            greaterNode.prev.next = currentNode;
                            greaterNode.prev = currentNode;
                        } else {
                            // 如果不是单独的节点，就说明是合并两个链表，两个链表的头尾都要调整
                            currentNode.next.prev = greaterNode.prev;
                            greaterNode.prev.next = currentNode.next;
                            currentNode.next = greaterNode;
                            greaterNode.prev = currentNode;
                        }
                        nodeList.remove(greaterNode);
                    }
                    break;
                } else if (nodeList.get(i).val == val) {
                    // 这个值已经添加过了，那么这个重复值就不用再进行处理了
                    return;
                }
            }
            // TODO 还有一种可能性：curr比整个列表中的所有值都大（即比nodeList中最后一个节点的最后一个节点还要大）
            final Node last = nodeList.getLast();
            final Node maxNode = last.prev;
            if (val == maxNode.val + 1) {
                // 加到最后一个节点的最后一个节点后边
                maxNode.next = currentNode;
                currentNode.prev = maxNode;
                currentNode.next = last;
                last.prev = currentNode;
            } else if (val > maxNode.val + 1) {
                nodeList.addLast(currentNode);
            }
        }
    }

    public int[][] getIntervals() {
        final int[][] answer = new int[nodeList.size()][2];
        for (int i = 0; i < nodeList.size(); i++) {
            final Node node = nodeList.get(i);
            answer[i][0] = node.val;
            answer[i][1] = node.prev.val;
        }
        return answer;
    }

    /**
     * 环形链表
     */
    private static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
            this.prev = this;
            this.next = this;
        }
    }

    public static void main(String[] args) {
        final SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
        summaryRanges.addNum(0);
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));

    }
}