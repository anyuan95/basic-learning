package org.example.basic.oj.leetcode.Q824;

/**
 * @author anyuan
 * @date 2022-04-24 20:42
 */
class Solution {

    /**
     * 如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
     * 例如，单词 "apple" 变为 "applema" 。
     * 如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
     * 例如，单词 "goat" 变为 "oatgma" 。
     * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
     * 例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
     *
     * @param sentence
     * @return
     */
    public String toGoatLatin(String sentence) {
        final String[] pieces = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pieces.length; i++) {
            final String piece = pieces[i];
            final char[] chars = piece.toCharArray();
            if (isNonVowelLetter(chars[0])) {
                sb.append(chars);
            } else {
                sb.append(chars, 1, chars.length - 1).append(chars[0]);
            }
            sb.append("ma");
            for (int j = 0; j < i + 1; j++) {
                sb.append('a');
            }
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private boolean isNonVowelLetter(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.toGoatLatin("Z speak Goat Latin"));
    }

}
