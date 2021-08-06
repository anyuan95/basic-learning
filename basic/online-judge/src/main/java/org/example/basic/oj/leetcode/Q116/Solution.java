package org.example.basic.oj.leetcode.Q116;

import lombok.ToString;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-08-05 17:32
 */
class Solution {
    public Node connect(Node root) {
        // 前序遍历？
        int step = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node preLeftNode = new Node();
        Node preRightNode = new Node();
        while (!queue.isEmpty()) {
            final Node currentNode = queue.remove();
            if (currentNode == null) {
                break;
            }

            if (step % 2 == 0) {
                // step为偶数的节点都是右节点
                preLeftNode.next = currentNode;
                // 如果step+2是2的n次幂，则这个节点是当前层的最右节点
                if (((step + 2) & (step + 1)) == 0) {
                    currentNode.next = null;
                    preRightNode = null;
                } else {
                    preRightNode = currentNode;
                }
            } else {
                // 奇数位置都是左节点，暂存，等到处理右节点时连上
                preLeftNode = currentNode;
                if (preRightNode != null) {
                    preRightNode.next = currentNode;
                }
            }

            step++;
            queue.add(currentNode.left);
            queue.add(currentNode.right);
        }
        return root;
    }

    private Node buildTree(int[] arr) {
        final Node root = new Node(arr[0]);
        // 数组转树
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < arr.length) {
            final Node currentNode = queue.remove();
            currentNode.left = new Node(arr[index]);
            if (index + 1 < arr.length) {
                currentNode.right = new Node(arr[index + 1]);
                index++;
            }
            index++;
            queue.add(currentNode.left);
            queue.add(currentNode.right);
        }
        return root;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        int[] arr = {-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13};
        final Node node = solution.buildTree(arr);
        System.out.println(node);
        solution.connect(node);
        System.out.println(node);
//        测试用例:[-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13]
//        测试结果:[-1,#,0,1,#,2,3,6,7,8,9,10,11,12,13,#,6,7,8,9,10,11,12,13,#]
//        期望结果:[-1,#,0,1,#,2,3,4,5,#,6,7,8,9,10,11,12,13,#]
    }

}

@ToString
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};