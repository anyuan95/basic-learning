package org.example.basic.oj.zuo.graph.mst;

import org.example.basic.oj.zuo.graph.model.Edge;
import org.example.basic.oj.zuo.graph.model.Graph;
import org.example.basic.oj.zuo.graph.model.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Prim算法
 * <p>
 * 1.可以从任意节点出发来新招最小生成树
 * 2.某个点加入到被选取的点中，解锁这个点出发的所有新的边
 * 3.在所有解锁的边中选最小的边，然后看这个边会不会形成环
 * - 如果会，不要当前边，继续考察剩下的解锁的边中最小的，重复3
 * - 如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2
 * 4.直到所有点都被选取，就得到了最小生成树
 * <p>
 * 注意：注意可能有森林的情况，即最后会生成多颗不相连的最小生成树。
 *
 * @author anyuan
 * @since 2021-08-16 17:02
 */
public class Prim {

    private Set<Edge> primMST(Graph graph) {
        Set<Edge> answer = new HashSet<>();
        // 将图的所有边根据权重放到小顶堆中
        PriorityQueue<Edge> minEdgeHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        HashSet<Node> visitedNodes = new HashSet<>();
        for (Node node : graph.getNodes().values()) {
            if (!visitedNodes.contains(node)) {
                visitedNodes.add(node);
                minEdgeHeap.addAll(node.getEdges());

                while (!minEdgeHeap.isEmpty()) {
                    Edge minWeightEdge = minEdgeHeap.poll();
                    Node toNode = minWeightEdge.getTo();

                    // 如果最短的边的to节点已经处理过了，继续
                    if (!visitedNodes.contains(toNode)) {
                        answer.add(minWeightEdge);
                        visitedNodes.add(toNode);
                        minEdgeHeap.addAll(toNode.getEdges());
                    }
                }
            }
            // 此处根据提供的图是否[仅能组成一个连通图]决定，如果给的图是森林型，则需要遍历所有节点，此处不能break；
//            break;
        }
        return answer;
    }
}
