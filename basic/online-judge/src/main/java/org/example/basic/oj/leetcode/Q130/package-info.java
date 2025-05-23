/**
 *
 * 本题有三种实现方式：
 * 1.DFS方式，应该是最容易理解的方式了。如果一个O节点被X节点包围，那么它一定不会与边界上的O连通。
 *   相同地，可以得到：与边界上O连通的所有O都不会是要找的O节点。
 *   故可以通过感染方式，找到所有边界上的O及与其相连的O，将这些O修改为M。
 *   此时board中剩余的所有O一定是被包围的O，现在将这些O改为X，将所有M调整回O。此时得到的board就是目标board。
 * 2.BFS方式，实际上BFS方式的思路和DFS是一致的。都是通过感染方式，也是从边界开始向内部找所有连通的O，将其感染，
 *   然后将所有要找的O调整成X，然后恢复第一部分感染的所有由边界出发的O。
 * 3.并查集方式。
 *   此处用了一个小技巧：做一个虚拟节点，使得边界上的所有O节点的父节点都是这个虚拟节点。
 *   然后将所有与边界上O关联的O节点进行关联。这时所有没有被包围的O节点就全都是虚拟节点的子节点了。
 *   最后再对board进行一次遍历，所有父节点不是虚拟节点的O节点，就都是我们要找的被包围的O节点。
 *
 * @author anyuan
 * @since 2021-09-09 10:59
 */
package org.example.basic.oj.leetcode.Q130;