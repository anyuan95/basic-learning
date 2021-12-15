package org.example.basic.oj.leetcode.Q911;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 给你两个整数数组 persons 和 times 。在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。
 * <p>
 * 对于发生在时刻 t 的每个查询，需要找出在 t 时刻在选举中领先的候选人的编号。
 * <p>
 * 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 * <p>
 * 实现 TopVotedCandidate 类：
 * <p>
 * TopVotedCandidate(int[] persons, int[] times) 使用 persons 和 times 数组初始化对象。
 * int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
 *
 * @author anyuan
 * @date 2021-12-11 23:49
 */
class TopVotedCandidate {

    private TreeMap<Integer, Integer> topVoted;

    public TopVotedCandidate(int[] persons, int[] times) {
        final int n = persons.length;
        this.topVoted = new TreeMap<>();
        // 0:编号，1:权重，2:放入时间
        final MaxHeap heap = new MaxHeap((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[2] - o2[2];
            }
            return o1[1] - o2[1];
        });
        for (int i = 0; i < n; i++) {
            // 在每个有投票的时间点记录一下这个时间点的最大值
            heap.add(persons[i], times[i]);
            topVoted.put(times[i], heap.peekMax());
        }
    }

    public int q(int t) {
        return topVoted.get(topVoted.floorKey(t));
    }

    private static class MaxHeap {
        int[][] heap;
        int heapSize;
        Comparator<int[]> comparator;
        Map<Integer, Integer> personMap;

        // 0:编号，1:票数，2:放入时间
        private MaxHeap(Comparator<int[]> comparator) {
            this.comparator = comparator;
            heap = new int[5000][];
            heapSize = 0;
            personMap = new HashMap<>();
        }

        private void add(int person, int voteTime) {
            int heapIndex;
            if (personMap.containsKey(person)) {
                heapIndex = personMap.get(person);
                final int[] info = heap[heapIndex];
                info[1]++;
                info[2] = voteTime;
            } else {
                heapIndex = heapSize;
                heap[heapIndex] = new int[]{person, 1, voteTime};
                personMap.put(person, heapIndex);
                heapSize++;
            }
            // 重新整理堆
            // 刚加入的值是在heapIndex位置的
            // 这个值只会变得更大，所以只需要向上就行了
            heapInsert(heap, heapIndex);
        }

        private void resign(int person) {
            heapInsert(heap, personMap.get(person));
            heapify(heap, personMap.get(person), heapSize);
        }

        /**
         * floatUp
         *
         * @param index
         */
        private void heapInsert(int[][] heap, int index) {
            int parentNodeIndex = (index - 1) / 2;
            while (comparator.compare(heap[index], heap[parentNodeIndex]) > 0) {
                swap(heap, index, parentNodeIndex);
                index = parentNodeIndex;
                parentNodeIndex = (index - 1) / 2;
            }
        }

        /**
         * 向下沉降
         *
         * @param index
         */
        private void heapify(int[][] heap, int index, int maxSize) {
            int leftIndex = (index << 1) + 1;
            while (leftIndex < maxSize) {
                int rightIndex = leftIndex + 1;
                int largestIndex = rightIndex < maxSize && comparator.compare(heap[leftIndex], heap[rightIndex]) < 0
                        ? rightIndex : leftIndex;
                largestIndex = comparator.compare(heap[largestIndex], heap[index]) > 0 ? largestIndex : index;
                if (largestIndex == index) {
                    break;
                }
                swap(heap, index, largestIndex);
                index = largestIndex;
                leftIndex = (index << 1) + 1;
            }
        }

        protected void swap(int[][] array, int index1, int index2) {
            int person1 = array[index1][0];
            int person2 = array[index2][0];
            if (index1 == index2) return;
            personMap.put(person1, index2);
            personMap.put(person2, index1);
            int[] temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }

        /**
         * 取出最大票数的人
         * 与正常的弹出不同的是，这里弹出不需要移除值，也不需要调整位置
         */
        private int peekMax() {
            return heap[0][0];
        }
    }

    public static void main(String[] args) {
        final TopVotedCandidate topVotedCandidate1 = new TopVotedCandidate(new int[]{0,1,1,0,0,1,0}, new int[]{0,5,10,15,20,25,30});
        System.out.println(topVotedCandidate1.q(3));
        System.out.println(topVotedCandidate1.q(12));
        System.out.println(topVotedCandidate1.q(25));
        System.out.println(topVotedCandidate1.q(15));
        System.out.println(topVotedCandidate1.q(24));
        System.out.println(topVotedCandidate1.q(8));
        final TopVotedCandidate topVotedCandidate2 = new TopVotedCandidate(new int[]{0, 0, 1, 1, 2}, new int[]{0, 67, 69, 74, 87});
        System.out.println(topVotedCandidate2.q(4));
        System.out.println(topVotedCandidate2.q(62));
        System.out.println(topVotedCandidate2.q(100));
        System.out.println(topVotedCandidate2.q(88));
        System.out.println(topVotedCandidate2.q(70));
        System.out.println(topVotedCandidate2.q(73));
        System.out.println(topVotedCandidate2.q(22));
        System.out.println(topVotedCandidate2.q(75));
        System.out.println(topVotedCandidate2.q(29));
        System.out.println(topVotedCandidate2.q(10));
        final TopVotedCandidate topVotedCandidate3 = new TopVotedCandidate(new int[]{0,1,2,3,0}, new int[]{11,14,81,83,87});
        System.out.println(topVotedCandidate3.q(23));
        System.out.println(topVotedCandidate3.q(61));
        System.out.println(topVotedCandidate3.q(11));
        System.out.println(topVotedCandidate3.q(88));
        System.out.println(topVotedCandidate3.q(78));
        System.out.println(topVotedCandidate3.q(55));
        System.out.println(topVotedCandidate3.q(73));
        System.out.println(topVotedCandidate3.q(94));
        System.out.println(topVotedCandidate3.q(89));
        System.out.println(topVotedCandidate3.q(41));
    }

}
