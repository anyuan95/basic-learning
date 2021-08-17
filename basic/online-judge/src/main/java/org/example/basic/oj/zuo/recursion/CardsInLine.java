package org.example.basic.oj.zuo.recursion;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-17 17:49
 */
public class CardsInLine {

    public int win1(int[] cards) {
        /* 补充边界条件 */
        final int[][] firstDp = new int[cards.length][cards.length];
        final int[][] secondDp = new int[cards.length][cards.length];
        for (int[] ints : firstDp) {
            Arrays.fill(ints, -1);
        }
        for (int[] ints : secondDp) {
            Arrays.fill(ints, -1);
        }

        int first = process_firstHand(cards, 0, cards.length - 1, firstDp, secondDp);
        int second = process_secondHand(cards, 0, cards.length - 1, firstDp, secondDp);
        return Math.max(first, second);
    }

    /**
     * 先手能够获得的最大分值
     *
     * @param cards
     * @param startIndex
     * @param endIndex
     * @param dp
     * @return
     */
    public int process_firstHand(int[] cards, int startIndex, int endIndex, int[][] firstDp, int[][] secondDp) {
        if (firstDp[startIndex][endIndex] != -1) {
            return firstDp[startIndex][endIndex];
        }
        int answer = 0;
        if (startIndex == endIndex) {
            answer = cards[startIndex];
        } else {
            // 先手先拿一张之后，就轮到我在L+1,R上后手了
            int p1 = cards[startIndex] + process_secondHand(cards, startIndex + 1, endIndex, firstDp, secondDp);
            int p2 = cards[endIndex] + process_secondHand(cards, startIndex, endIndex - 1, firstDp, secondDp);
            // 由于我是先手，所以我取max
            answer = Math.max(p1, p2);
        }
        firstDp[startIndex][endIndex] = answer;
        return answer;
    }

    public int process_secondHand(int[] cards, int startIndex, int endIndex, int[][] firstDp, int[][] secondDp) {
        if (secondDp[startIndex][endIndex] != -1) {
            return secondDp[startIndex][endIndex];
        }
        int answer = 0;
        if (startIndex == endIndex) {
            // 只剩一张，肯定被先手拿走，后手只能拿0
            answer = 0;
        } else {
            // 对手先手拿走了L，则后手就要从L+1,R中尽量得到最大值
            int p1 = process_firstHand(cards, startIndex + 1, endIndex, firstDp, secondDp);
            // 同理，对手先手拿走了R
            int p2 = process_firstHand(cards, startIndex, endIndex - 1, firstDp, secondDp);
            // 我是后手，只能接受对手的决定，所以会被选择尽量小的值
            answer = Math.min(p1, p2);
        }
        secondDp[startIndex][endIndex] = answer;
        return answer;
    }


    /**
     * 总结，生成dp表规则：
     * 假设现在有先手表first和后手表second。是两个L*R的数组，由于一定满足L<=R，所以只有一半的表需要填写，即从[0,0]~[length,length]的部分以及该线右上的部分。
     * 整理规则后可有：
     * 1.对于first表的[i,j]，值 = Math.max( second[i+1,j] + cards[i], second[i,j-1] + cards[j] )
     * 2.对于second表的[i,j]，值 = Math.min( first[i+1,j], first[i,j+1] )
     *
     * @param cards
     * @return
     */
    public int win2(int[] cards) {
        final int[][] firstDp = new int[cards.length][cards.length];
        final int[][] secondDp = new int[cards.length][cards.length];
        final int length = cards.length;
        for (int i = 0; i < length; i++) {
            firstDp[i][i] = cards[i];
//            secondDp[i][i] = 0;
        }
        // 逐条对角线进行填充
        for (int startCol = 1; startCol < length; startCol++) {
            int row = 0, col = startCol;
            while (col < length) {
                firstDp[row][col] = Math.max(secondDp[row + 1][col] + cards[row], secondDp[row][col - 1] + cards[col]);
                secondDp[row][col] = Math.min(firstDp[row + 1][col], firstDp[row][col - 1]);
                row++;
                col++;
            }
        }
        return Math.max(firstDp[0][length - 1], secondDp[0][length - 1]);
    }


    public static void main(String[] args) {
        final CardsInLine cardsInLine = new CardsInLine();
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(cardsInLine.win1(arr));
    }

}
