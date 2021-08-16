package org.example.basic.oj.zuo.graph;

/**
 * 克鲁斯卡尔算法（Kruskal）
 *
 * ？实际上就是并查集？？？？
 *
 * 在无向图中，找到能够连通所有节点的路径长度总和最小值。（构成连通图的路径长度和）（最小生成树的权值和）
 *
 * @author anyuan
 * @since 2021-08-16 16:43
 */
public class Kruskal {

    /**
     * 思路1：
     * 使用并查集方式解决
     *
     * 将所有的边长度按照从小到大排序，逐个进行处理：
     * - 如果加入当前边后会出现环，则不要；
     * - 如果加入当前变后不会出现环，则添加当前边；
     * 直到构成连通图为止。
     *
     * @return
     */
    private int minWeight_UnionFindSet(/*TODO*/) {
        return -1;
    }
}
