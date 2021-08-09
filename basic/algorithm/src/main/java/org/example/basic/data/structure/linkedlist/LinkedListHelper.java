package org.example.basic.data.structure.linkedlist;

import cn.hutool.core.util.StrUtil;
import org.example.basic.data.structure.linkedlist.model.SingleLinkedListNode;

import java.util.List;

/**
 * @author anyuan
 * @since 2021-08-09 15:59
 */
public class LinkedListHelper {

    public static SingleLinkedListNode<Integer> parseInteger(String content, char separator) {
        SingleLinkedListNode<Integer> preHead = new SingleLinkedListNode<>(), current = preHead;
        final List<String> pieces = StrUtil.split(content, separator);
        for (String piece : pieces) {
            final SingleLinkedListNode<Integer> next = new SingleLinkedListNode<>(Integer.parseInt(piece));
            current.next = next;
            current = next;
        }
        return preHead.next;
    }
}
