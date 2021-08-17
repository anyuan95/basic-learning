package org.example.basic.oj.zuo.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出字符串的所有子序列
 *
 * @author anyuan
 * @since 2021-08-17 14:12
 */
public class PrintAllSubSequences {

    public List<String> printAllSubSequences(String str) {
        final char[] chars = str.toCharArray();
        List<String> answer = new ArrayList<>();
        process(chars, 0, answer, "");
        return answer;
    }

    /**
     * 对字符数组中的每个位置进行两种决策：要 和 不要
     * 将决策后的内容继续进行处理
     *
     * 可以想象成一个二叉树，根节点为null，第i层的第n个小三角中，左子树和右子树的结果就是三角中根节点的决策下，第i个字符添加和不添加的结果。
     * 以此类推，整棵树最后一层的所有节点的值就是所有的子序列。
     *
     * @param chars   字符串中的所有字符
     * @param index   当前索引位置
     * @param answer  结果集
     * @param decided 已经决定的部分
     */
    private void process(char[] chars, int index, List<String> answer, String decided) {
        if (index == chars.length) {
            answer.add(decided);
            return;
        }
        String noAddCurrent = decided;
        process(chars, index + 1, answer, noAddCurrent);
        String addCurrent = decided + chars[index];
        process(chars, index + 1, answer, addCurrent);
    }

    public static void main(String[] args) {
        final PrintAllSubSequences printAllSubSequences = new PrintAllSubSequences();
        System.out.println(printAllSubSequences.printAllSubSequences("abc"));
    }


}
