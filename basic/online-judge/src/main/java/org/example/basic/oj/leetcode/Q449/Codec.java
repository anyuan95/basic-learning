package org.example.basic.oj.leetcode.Q449;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @date 2022-05-11 23:48
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) {
            return "#";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder answer = new StringBuilder();
        while (!queue.isEmpty()) {
            final TreeNode poll = queue.poll();
            answer.append("#");
            if (null != poll) {
                answer.append(poll.val);
                queue.add(poll.left);
                queue.add(poll.right);
            }
        }
        return answer.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("#".equals(data)) {
            return null;
        }
        final String[] split = data.split("#");
        TreeNode answer = new TreeNode(Integer.parseInt(split[1]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(answer);
        for (int i = 1; i < split.length; i+=2) {
            final TreeNode poll = queue.poll();
            if (i+1 < split.length && split[i+1].length() > 0) {
                poll.left = new TreeNode(Integer.parseInt(split[i+1]));
                queue.add(poll.left);
            }
            if (i+2 < split.length && split[i+2].length() > 0) {
                poll.right = new TreeNode(Integer.parseInt(split[i+2]));
                queue.add(poll.right);
            }
        }
        return answer;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}