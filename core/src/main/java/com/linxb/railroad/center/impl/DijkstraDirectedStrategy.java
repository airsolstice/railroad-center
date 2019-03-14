package com.linxb.railroad.center.impl;

import com.linxb.railroad.center.DirectedStrategy;
import com.linxb.railroad.center.Environment;
import com.linxb.railroad.center.Heap;
import com.linxb.railroad.center.entity.Edge;
import com.linxb.railroad.center.entity.Node;
import com.linxb.railroad.center.utils.AssertUtil;
import com.linxb.railroad.center.utils.GraphUtil;
import com.linxb.railroad.center.utils.StringUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Description directed strategy implement.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class DijkstraDirectedStrategy implements DirectedStrategy {

    private Edge[] allEdges;// whole graph needed edges.
    private int[][] matrix;// whole graph of dijkstra.
    private int size;// total of size.

    /**
     * used for Java SPI.
     */
    public DijkstraDirectedStrategy() {
    }

    /**
     * construction.
     *
     * @param allEdges
     * @param stops
     */
    public DijkstraDirectedStrategy(Edge[] allEdges, int stops) {
        init(allEdges, stops);
    }

    @Override
    public void init(Edge[] allEdges, int size) {
        this.allEdges = allEdges;
        this.size = size;
        this.matrix = toMatrix(allEdges, size);
        if (Environment.DEBUG_ENABLE)
            GraphUtil.print(matrix, size);
    }

    @Override
    public int[][] toMatrix(Edge[] allEdges, int stops) {
        int n = Environment.N;
        AssertUtil.check(allEdges != null, "graph edges array is NULL.");
        AssertUtil.check(stops > 0, "whole graph matrix length could not be 0.");
        int[][] matrix = new int[stops][stops];

        for (int i = 0; i < stops; i++) {
            for (int j = 0; j < stops; j++) {
                matrix[i][j] = n;
            }
        }

        for (Edge e : allEdges) {
            AssertUtil.checkNull(e);
            int startingIndex = StringUtil.mapLetter2Index(e.getStarting().getTownName());
            AssertUtil.check(stops > startingIndex, "out of bound.");
            int endingIndex = StringUtil.mapLetter2Index(e.getEnding().getTownName());
            AssertUtil.check(stops > endingIndex, "out of bound.");
            int weighting = e.getWeighting();
            matrix[startingIndex][endingIndex] = weighting;
        }

        return matrix;
    }

    /////////////////////////////////////////////////////////////////////////////

    /**
     * the efficiency of algorithm is O(n^2).
     *
     * @param starting
     * @return
     */
    @Deprecated
    private int[] getShortestDistancesByTraversal(Node starting) {
        AssertUtil.check(size > 0, "need buildHeap.");
        AssertUtil.check(matrix != null && matrix.length == size, "need buildHeap.");
        int startingIndex = StringUtil.mapLetter2Index(starting.getTownName());
        int n = Environment.N;

        // previous nodes array.
        int[] preNode = new int[size];
        // the shortest getDistance records array.
        int[] minDist = new int[size];
        // whether or not find the shortest getDistance.
        boolean[] find = new boolean[size];
        //point
        int near = 0;

        // buildHeap arr.
        for (int i = 0; i < minDist.length; i++) {
            preNode[i] = i;
            minDist[i] = matrix[startingIndex][i];
            find[i] = false;
        }

        for (int v = 1; v < size; v++) {
            // for each, the nearest node and the shortest distance were obtained.
            int min = n;
            for (int j = 0; j < size; j++) {
                if (!find[j] && minDist[j] < min) {
                    min = minDist[j];
                    near = j;
                }
            }
            find[near] = true;

            // according to near node, fix the precursor node and distance to starting node.
            for (int k = 0; k < size; k++) {
                if (!find[k] && (min + matrix[near][k]) < minDist[k]) {
                    preNode[k] = near;
                    minDist[k] = min + matrix[near][k];
                }
            }
        }
        // print result.
        if (Environment.DEBUG_ENABLE)
            GraphUtil.print(startingIndex, minDist);

        return minDist;
    }

    /**
     * the efficiency of algorithm is O(nlogn).
     *
     * @param startingNode
     * @return
     */
    public int[] getShortDistancesByHeap(Node startingNode) {
        int starting = StringUtil.mapLetter2Index(startingNode.getTownName());
        int[] distances = new int[size];
        boolean[] passes = new boolean[size];
        for (int i = 0; i < size; ++i) {
            // init distance of starting node to all nodes.
            distances[i] = matrix[starting][i];
            passes[i] = false;
        }

        // loop n-1 times.
        for (int i = 1; i < size; i++) {
            if (!passes[i]) {
                dijkstra(distances, passes);
            }
        }

        return distances;
    }

    public void dijkstra(int[] distance, boolean[] passes) {
        int n = Environment.N;
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.getWeighting() - o2.getWeighting();
            }
        });
        // 将邻接表导入最小堆中
        for (int i = 0; i < size; i++) {
            int w = distance[i];
            if (w == n) {
                continue;
            }
            pq.offer(new Edge(i, w));
        }

        int next, dis;
        Edge e = pq.poll();
        while (e != null && passes[e.getIndex()]) {
            e = pq.poll();
        }
        if (e == null)
            return;
        // 获得下一个点的位置
        next = e.getIndex();
        // 获取起点到下一个点的距离
        dis = e.getWeighting();
        // 矫正数据
        for (int i = 0; i < size; i++) {
            int d = distance[i];
            int min = dis + matrix[next][i];
            distance[i] = min < d ? min : d;
        }
        passes[next] = true;

    }

    @Override
    public int[] getShortestDistances(Node starting) {
//        return getShortestDistancesByTraversal(starting);
        return getShortDistancesByHeap(starting);
    }

    @Override
    public int getSpecialRoutDistance(Edge... edges) {
        AssertUtil.checkNull(edges);
        return GraphUtil.computeSpecialRouteDistance(matrix, edges);
    }

    @Override
    public String[] getRoutesBetweenTwoNodes(Node starting, Node ending) {

        AssertUtil.checkNull(starting);
        AssertUtil.checkNull(ending);
        int startingIndex = StringUtil.mapLetter2Index(starting.getTownName());
        int endingIndex = StringUtil.mapLetter2Index(ending.getTownName());
        // check ending node whether or not be reachable.
        boolean reachable = false;
        for (int i = 0; i < size; i++) {
            reachable |= (matrix[i][endingIndex] != Environment.N);
        }
        if (!reachable) {
            return new String[0];
        }

        List<String> result = new ArrayList<>();
        recursive(startingIndex, endingIndex, "", result);

        return result.toArray(new String[result.size()]);
    }

    /**
     * recursive method, finding the reachable node.
     *
     * @param currentNode
     * @param endingNode
     * @param route
     * @param result
     */
    private void recursive(int currentNode, int endingNode, String route, List<String> result) {

        int n = Environment.N;
        // limit
        if (currentNode == endingNode && !StringUtil.isBlank(route)) {
            result.add(route + StringUtil.mapIndex2Letter(endingNode));
            return;
        }

        String r = route + StringUtil.mapIndex2Letter(currentNode) + "-";
        // for each, find next node.
        int w;
        for (int i = 0; i < size; i++) {
            // weight is not equals to N, indicate that next reachable node.
            w = matrix[currentNode][i];
            // if weight is N, or already pass this node.
            if (w == n || route.contains(StringUtil.mapIndex2Letter(currentNode))) {
                continue;
            }
            // recursive
            recursive(i, endingNode, r, result);
        }
    }


    /**
     * @param edges
     * @param distance
     * @return
     */
    public int getTheDurationOfGivenRoute(Edge[] edges, int distance, int unit) {
        // nodes = edges.length -1
        assert edges != null && edges.length > 0;
        assert distance > 0;
        assert  unit > 0;

        int nodes = edges.length - 1;
        // nodes * 2 + distance * 1;

        return nodes * 2 * unit + distance * unit;
    }
}
