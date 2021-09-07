package org.example.basic.oj.leetcode.Q572;

/**
 * @author anyuan
 * @since 2021-09-06 16:21
 */
class Solution {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (equals(root, subRoot)) {
            return true;
        }
        return root != null && (isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot));
    }

    private boolean equals(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        } else { // val1==val2
            return equals(root1.left, root2.left) && equals(root1.right, root2.right);
        }
    }

    public static void main(String[] args) {
        TreeNode head =
                new TreeNode(1, null, new TreeNode(1, null,
                        new TreeNode(1, null, new TreeNode(1, null,
                                new TreeNode(1, null, new TreeNode(1, null,
                                        new TreeNode(1, null, new TreeNode(1, null,
                                                new TreeNode(1, null, new TreeNode(1, null,
                                                        new TreeNode(1, new TreeNode(2), null)))))))))));
        TreeNode sub =
                new TreeNode(1, null, new TreeNode(1, null,
                        new TreeNode(1, null, new TreeNode(1, null,
                                new TreeNode(1, null, new TreeNode(1, new TreeNode(2), null))))));
        final Solution solution = new Solution();
        System.out.println(solution.isSubtree(head, sub));
    }
}
