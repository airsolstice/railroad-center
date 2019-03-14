package com.linxb.railroad.center;

import com.linxb.railroad.center.entity.Edge;
import com.linxb.railroad.center.entity.Node;

/**
 * @Description graph strategy interface.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public interface DirectedStrategy {

    /**
     * strategy buildHeap, it will establish a whole graph matrix where a node represents a town
     * and an edge represents a route between two towns. the weighting of the edge represents
     * the distance between the two towns.
     *
     * @param allEdges a whole graph needed edges.
     * @param size    size of stops(towns).
     */
    void init(Edge[] allEdges, int size);

    /**
     * convert edge array to a matrix.
     *
     * @param allEdges a whole graph needed edges.
     * @param stops    total of stops(towns).
     * @return matrix
     */
    int[][] toMatrix(Edge[] allEdges, int stops);

    /**
     * get the shortest distance from starting node to all nodes.
     *
     * @param starting starting node.
     * @Return minDst return the shortest distance of starting node to all node.
     */
    int[] getShortestDistances(Node starting);

    /**
     * get distance of a special route plan.
     *
     * @param edges specified edges.
     * @return distance if return N, it indicates there is no matched route in graph.
     */
    int getSpecialRoutDistance(Edge... edges);

    /**
     * get routes between 2 special nodes.
     *
     * @return routes
     */
    String[] getRoutesBetweenTwoNodes(Node starting, Node ending);
}
