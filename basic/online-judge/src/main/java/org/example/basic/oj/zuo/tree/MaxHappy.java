package org.example.basic.oj.zuo.tree;

/**
 * 给定一棵多叉树，以该树表示部门员工的层级关系。每个节点的值是该员工来参加聚会的快乐值。
 * 现在要举办一个聚会，如果当前节点的员工来了，那么它的直属员工就都不会来参加。
 * 求最大快乐值。
 *
 * 注意：happy1会有大量的重复计算，happy2会更好。多次执行后统计的调用次数大约是10:1，在树足够大的情况下会更明显。
 *
 * @author anyuan
 * @since 2021-08-13 15:02
 */
class MaxHappy {

    static int process1_call_count = 0;
    static int process2_call_count = 0;

    public static int maxHappy1(MultiTreeNode root) {
        return Math.max(process1(root, true), process1(root, false));
    }

    /**
     * 问题拆分
     * 两种大情况：当前节点来 与 当前节点不来
     * 以每个节点为头的树的最大快乐值，即MAX(当前节点来时的总快乐值,当前节点不来时的快乐值)
     * <p>
     * 如果当前节点来，则其快乐值就是所有直接子节点不来的最大快乐值的和 + 当前节点的快乐值；
     * 如果当前节点不来，则其快乐值就是所有直接子节点来的最大快乐值的和。
     *
     * @param current
     * @param currentCome
     * @return
     */
    private static int process1(MultiTreeNode current, boolean currentCome) {
        process1_call_count++;
        if (current == null) {
            return 0;
        }

        if (currentCome) {
            // 当前节点来，则所有子节点都必须不能来
            return current.val + current.children.stream()
                    .mapToInt(child -> process1(child, false))
                    .sum();
        } else {
            // 当前节点不来，则子节点可以自行选择来或者不来
            return current.children.stream()
                    .mapToInt(child -> Math.max(process1(child, false), process1(child, true)))
                    .sum();
        }
    }

    public static int maxHappy2(MultiTreeNode root) {
        final Info info = process2(root);
        return Math.max(info.happyWhenCome, info.happyWhenNotCome);
    }

    static class Info {
        int happyWhenCome;
        int happyWhenNotCome;

        public Info(int happyWhenCome, int happyWhenNotCome) {
            this.happyWhenCome = happyWhenCome;
            this.happyWhenNotCome = happyWhenNotCome;
        }
    }

    /**
     * 计算当前节点来和不来情况下的值
     *
     * @param current
     * @return
     */
    private static Info process2(MultiTreeNode current) {
        process2_call_count++;
        if (current == null) {
            return new Info(0,0);
        }
        int happyWhenCome = current.val;
        int happyWhenNotCome = 0;
        for (MultiTreeNode child : current.children) {
            final Info info = process2(child);
            happyWhenNotCome += Math.max(info.happyWhenCome, info.happyWhenNotCome);
            happyWhenCome += info.happyWhenNotCome;
        }
        return new Info(happyWhenCome, happyWhenNotCome);
    }


    /*-------------------------------------------------------------------------*/

    // for test
    public static MultiTreeNode genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        MultiTreeNode boss = new MultiTreeNode((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(MultiTreeNode e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            MultiTreeNode next = new MultiTreeNode((int) (Math.random() * (maxHappy + 1)));
            e.children.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    /*-----copied for test start*/

    public static int maxHappy_test(MultiTreeNode boss) {
        if (boss == null) {
            return 0;
        }
        return process_test(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process_test(MultiTreeNode cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (MultiTreeNode next : cur.children) {
                ans += process_test(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.val;
            int p2 = 0;
            for (MultiTreeNode next : cur.children) {
                p1 += process_test(next, true);
                p2 += process_test(next, false);
            }
            return Math.max(p1, p2);
        }
    }
    /*-----copied  for test end*/


    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
//        int testTimes = 100000;
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            MultiTreeNode boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy2(boss) != maxHappy1(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
        System.out.println(process1_call_count);
        System.out.println(process2_call_count);
    }

}
