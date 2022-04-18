package org.example.basic.oj.leetcode.Q1345;

import java.util.*;

class Solution {
    public int _minJumps(int[] arr) {
        if (arr.length == 1) {
            return 0;
        } else if (arr.length == 2) {
            return 1;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        final int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                map.get(arr[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(arr[i], list);
            }
        }
        ArrayList<Integer>[] graph = new ArrayList[n];
        graph[0] = new ArrayList<>();
        graph[0].add(1);
        graph[0].addAll(map.get(arr[0]));
        graph[0].remove((Object) 0);
        graph[n - 1] = new ArrayList<>();
        graph[n - 1].add(n - 2);
        for (int i = 1; i < n - 1; i++) {
            final ArrayList<Integer> list = new ArrayList<>();
            list.add(i - 1);
            list.add(i + 1);
            list.addAll(map.get(arr[i]));
            list.remove((Object) i);
            graph[i] = list;
        }
        // bfs
        int jump = 1;
        Queue<Integer> queue = new LinkedList<>(graph[0]);
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final Integer currentIndex = queue.poll();
                if (currentIndex == n - 1) {
                    return jump;
                }
                queue.addAll(graph[currentIndex]);
            }
            jump++;
        }
        return jump;
    }

    public int minJumps(int[] arr) {
        final int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            final List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList();
        queue.add(0);
        dist[0] = 0;
        while (!queue.isEmpty()) {
            final Integer poll = queue.poll();
            int step = dist[poll];
            if (poll == n - 1) {
                return step;
            }
            if (poll + 1 < n && dist[poll + 1] == Integer.MAX_VALUE) {
                queue.add(poll + 1);
                dist[poll + 1] = step + 1;
            }
            if (poll - 1 >= 0 && dist[poll - 1] == Integer.MAX_VALUE) {
                queue.add(poll - 1);
                dist[poll - 1] = step + 1;
            }
            if (map.containsKey(arr[poll])) {
                final List<Integer> list = map.get(arr[poll]);
                for (Integer i : list) {
                    if (dist[i] == Integer.MAX_VALUE) {
                        queue.add(i);
                        dist[i] = step + 1;
                    }
                }
            }
            map.remove(arr[poll]);
        }
        return -1;
    }
}

