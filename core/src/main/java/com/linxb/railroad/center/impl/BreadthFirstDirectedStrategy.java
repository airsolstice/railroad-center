package com.linxb.railroad.center.impl;

import com.linxb.railroad.center.DirectedStrategy;
import com.linxb.railroad.center.entity.Edge;
import com.linxb.railroad.center.entity.Node;

/**
 * @Description directed strategy implement.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class BreadthFirstDirectedStrategy implements DirectedStrategy {

    @Override
    public void init(Edge[] allEdges, int size) {}

    @Override
    public int[][] toMatrix(Edge[] edges, int stops) {
        return new int[0][];
    }

    @Override
    public int[] getShortestDistances(Node starting) {
        return new int[0];
    }

    @Override
    public int getSpecialRoutDistance(Edge... edges) {
        return 0;
    }

    @Override
    public String[] getRoutesBetweenTwoNodes(Node starting, Node ending) {
        return new String[0];
    }
}
