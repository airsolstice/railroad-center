package com.linxb.railroad.center.utils;


import com.linxb.railroad.center.Environment;
import com.linxb.railroad.center.entity.Edge;
import com.linxb.railroad.center.entity.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description directed strategy implement.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class GraphUtil {

    /**
     * print the matrix.
     *
     * @param matrix
     * @param stops
     */
    public static void print(int[][] matrix, int stops) {
        AssertUtil.checkNull(matrix);
        System.out.println(String.format("establish a whole graph, there are %s stops:", stops));
        GraphUtil.traversal(matrix, (w, x, y) -> {
            System.out.printf("" + (w == Environment.N ? "N" : w) + ", ");
            if (y + 1 == matrix.length) {
                System.out.println();
            }
        });
    }

    /**
     * for each print the shortest distance of all nodes.
     *
     * @param startingIndex
     * @param minDist
     */
    public static void print(int startingIndex, int[] minDist) {
        for (int i = 0; i < minDist.length; i++) {
            String w = minDist[i] == Environment.N ? Environment.NO_ROUTE_TIPS : minDist[i] + "";
            System.out.println(StringUtil.mapIndex2Letter(startingIndex) + "-...-"
                    + StringUtil.mapIndex2Letter(i) + ", dis = " + w);
        }
    }

    /**
     * print route plan.
     *
     * @param arr
     */
    public static void print(String[] arr) {
        for (String s : arr) {
            System.out.println("Route: " + s);
        }
    }

    /**
     * traversal callback interface
     */
    interface TraversalCallable {
        void traversal(int weight, int x, int y);
    }

    /**
     * traversal method, need to implement interface.
     *
     * @param matrix
     * @param callable
     */
    public static void traversal(int[][] matrix, TraversalCallable callable) {
        AssertUtil.checkNull(callable);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                callable.traversal(matrix[i][j], i, j);
            }
        }
    }

    /**
     * compute distance of special route plan.
     *
     * @param matrix
     * @param edges
     * @return
     */
    public static int computeSpecialRouteDistance(int[][] matrix, Edge[] edges) {
        int n = Environment.N;
        int distance = 0;
        for (Edge e : edges) {
            AssertUtil.checkNull(e);
            int x = getIndex(e.getStarting().getTownName().trim());
            int y = getIndex(e.getEnding().getTownName().trim());
            int w = matrix[x][y];
            if (w == n) {
                return w;
            }

            distance += w;
        }

        return distance;
    }

    /**
     * @param letter
     * @return
     */
    public static int getIndex(String letter) {
        return StringUtil.mapLetter2Index(letter);
    }

    /**
     * convert string input to edge array.
     *
     * @param input string input
     * @return edges        arr
     */
    public static Edge[] toAllEdges(String input) {// like AB5,BC3
        // split string, it will be equal-length
        String[] arr = StringUtil.splitEqualLength(input, ",", 3);
        int edgeArrLength = arr.length;
        Edge[] edges = new Edge[edgeArrLength];

        for (int i = 0; i < edgeArrLength; i++) {
            String s = arr[i].trim();
            String startingTownName = s.charAt(0) + "";
            String endingTownName = s.charAt(1) + "";
            int weighting = Integer.valueOf(s.charAt(2) + "");
            Edge e = new Edge(startingTownName, endingTownName, weighting);
            edges[i] = e;
        }

        return edges;
    }

    /**
     * convert node array to edge array.
     *
     * @param nodes need be calculated nodes.
     * @return edges    arr
     */
    public static Edge[] toEdges(Node... nodes) {
        List<Edge> edges = new ArrayList<>();
        Node pre = null;
        for (Node n : nodes) {
            if (pre == null) {
                pre = n;
                continue;
            }

            Edge e = new Edge(pre, n);
            edges.add(e);
            pre = n;
        }

        Edge[] arr = new Edge[edges.size()];
        edges.toArray(arr);

        return arr;
    }

    /**
     * convert node array to edge array.
     *
     * @param input user formatted input.
     * @return edges    arr
     */
    public static Edge[] toEdges(String input) {// like A-B-c
        String[] arr = StringUtil.splitEqualLength(input, "-", 1);
        AssertUtil.check(arr.length > 1, "input invalid.");
        Edge[] edges = new Edge[arr.length - 1];

        Node pre = null;
        int index = 0;
        for (String s : arr) {
            if (pre == null) {
                pre = new Node(s);
                continue;
            }
            Node n = new Node(s);
            Edge e = new Edge(pre, n);
            edges[index++] = e;
            pre = n;
        }
        return edges;
    }

    public static Edge[] toEdges(int[] arr, int startIndex) {
        AssertUtil.checkNull(arr);
        Edge[] edges = new Edge[arr.length];

        for (int i = 0; i < arr.length; i++) {
            String startingName;
            String endingName;
            startingName = StringUtil.mapIndex2Letter(startIndex);
            endingName = StringUtil.mapIndex2Letter(i);

            Edge e = new Edge(startingName, endingName, arr[i]);
            edges[i] = e;
        }
        return edges;
    }

}
