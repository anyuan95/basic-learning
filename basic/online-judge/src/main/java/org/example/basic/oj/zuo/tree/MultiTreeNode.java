package org.example.basic.oj.zuo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-08-13 15:10
 */
public class MultiTreeNode {
    int val;
    List<MultiTreeNode> children;

    public MultiTreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public MultiTreeNode(int val, List<MultiTreeNode> children) {
        this.val = val;
        this.children = children;
    }
}
