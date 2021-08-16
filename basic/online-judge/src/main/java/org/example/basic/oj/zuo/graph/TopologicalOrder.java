package org.example.basic.oj.zuo.graph;

import org.example.basic.oj.zuo.graph.model.Graph;
import org.example.basic.oj.zuo.graph.model.Node;

import java.util.*;

/**
 * 拓扑排序
 * <p>
 * 必须是[有向图]而且[无环]。
 * 例如解决包依赖问题时的结构，仅当A包和B包编译完成后才能编译C包，仅当B包和C包编译完成后才能编译D包，以此类推。
 * 常见应用：事件排序、编译顺序
 * <p>
 * 算法：
 * 1.在图中找出所有入度为0的节点输出；
 * 2.从图中删除步骤1中找到的所有节点；
 * 3.重复1和2的操作；
 * 4.最终得到的输出结果顺序就是拓扑顺序；
 *
 * @author anyuan
 * @since 2021-08-16 11:10
 */
class TopologicalOrder {

    /**
     * 思路1：通过BFS方式，每次遍历入度为0的节点，然后调整其nexts节点的入度减一。直到没有入度为0的节点为止。
     *
     * @param graph
     * @return
     */
    public List<Node> topologicalOrderSort_BFS(Graph graph) {
        List<Node> answers = new ArrayList<>();
        HashMap<Node, Integer> realTimeInCountMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        // 把所有入度为0的节点加到队列中
        for (Node node : graph.getNodes().values()) {
            realTimeInCountMap.put(node, node.getInCount());
            if (node.getInCount() == 0) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            final Node current = queue.poll();
            answers.add(current);
            for (Node next : current.getNexts()) {
                realTimeInCountMap.computeIfPresent(current, (node, integer) -> integer - 1);
                if (realTimeInCountMap.get(next) == 0) {
                    queue.add(current);
                }
            }
        }
        return answers;
    }

    /**
     * 思路2：统计每个节点的点次，然后进行升序排序，结果就是拓扑序的。
     * 点次：从当前节点开始，沿着next方向，直到走到头，每经过一个节点一次，记作1点次。点次不关注是否会有重复节点，只关注经过节点的次数。
     *
     * @param graph
     * @return
     */
    public List<Node> topologicalOrderSort_NodePassTimes(Graph graph) {
        return null;
    }

    /**
     * 思路3：统计每个节点的最大深度，按照深度降序排序。
     *
     * @param graph
     * @return
     */
    public List<Node> topologicalOrderSort_MaxDepth(Graph graph) {
        return null;
    }

    public static void main(String[] args) {

    }

}
