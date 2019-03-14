package com.linxb.railroad.center;

import com.linxb.railroad.center.entity.Edge;
import com.linxb.railroad.center.entity.Node;
import com.linxb.railroad.center.impl.DijkstraDirectedStrategy;
import com.linxb.railroad.center.utils.AssertUtil;
import com.linxb.railroad.center.utils.GraphUtil;
import com.linxb.railroad.center.utils.StringUtil;

import java.util.*;

/**
 * @Description entrance of strategy design mode.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class Environment {

    /**
     * default output string, if there is no matched route in graph.
     */
    public static final String NO_ROUTE_TIPS = "NO SUCH ROUTE";
    /**
     * infinite flag.
     */
    public static final int N = 1000;
    /**
     * debug enable flag
     */
    public static final boolean DEBUG_ENABLE = false;
    /**
     * strategy oriented interface.
     */
    private DirectedStrategy strategy;

    /**
     * @param strategy customer strategy.
     */
    public Environment(DirectedStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * proxy method of strategy, get distance of the special route plan.
     * must input formatted string, string must be split by '-', string can contain blank, but not be empty,
     * length of one split string must be 1, thw each split value is node name. the string ignore case.
     *
     * @param input user input formatted string.
     * @return distance
     */
    public int getSpecialRouteDistance(String input) {
        return getSpecialRouteDistance(GraphUtil.toEdges(input));
    }

    /**
     * input formatted string, string must be split by ',', string can contain blank, but not be empty,
     * length of one split string must be 3, the first char is starting node name, the second char is ending node name,
     * the third char is value of weight. the string ignore case.
     *
     * @param input user input content.
     * @param stops user input stop number.
     */
    public Environment(String input, int stops) {

        // Java SPI
        ServiceLoader<DirectedStrategy> loader = ServiceLoader.load(DirectedStrategy.class);
        Iterator<DirectedStrategy> it = loader.iterator();

        while (it.hasNext()) {
            DirectedStrategy strategy = it.next();
            if (strategy instanceof DijkstraDirectedStrategy) {
                this.strategy = strategy;
                break;
            }
        }

        // check
        AssertUtil.checkNull(strategy);
        // buildHeap strategy.
        // establish a whole graph.
        this.strategy.init(GraphUtil.toAllEdges(input), stops);
    }

    /**
     * proxy method of strategy, get distance of the special route plan.
     * the element of edges could not null.
     *
     * @param edges user specified edges.
     * @return distance
     */
    public int getSpecialRouteDistance(Edge[] edges) {
        return strategy.getSpecialRoutDistance(edges);
    }

    /**
     * proxy method of strategy, get distance of the special route plan.
     *
     * @param nodes user specified nodes.
     * @return distance
     */
    public int getSpecialRouteDistance(Node... nodes) {
        return getSpecialRouteDistance(GraphUtil.toEdges(nodes));
    }


    /**
     * proxy method of strategy, get matched number of route.
     *
     * @param starting starting node.
     * @param ending   ending node.
     * @param steps    limited steps, must be large than 0.
     * @return number of routes.
     */
    public int getSpecialRoutDistanceWithinSteps(Node starting, Node ending, int steps) {

        AssertUtil.check(steps > 0, "steps must be large than 0.");
        // get all routes between 2 nodes.
        String[] result = strategy.getRoutesBetweenTwoNodes(starting, ending);
        if (DEBUG_ENABLE)
            GraphUtil.print(result);
        int count = 0;
        for (String res : result) {
            if (StringUtil.count(res, "-") <= steps) {
                count++;
            }
        }

        return count;
    }

    /**
     * get routes between 2 special nodes without n steps.
     *
     * @param starting
     * @param ending
     * @param steps must be large than 0.
     * @return number of routes.
     */
    public int getRepeatableRoutesWithoutSteps(Node starting, Node ending, int steps) {
        AssertUtil.check(steps > 0, "steps must be large than 0.");
        String[] arr1 = strategy.getRoutesBetweenTwoNodes(starting, ending);
        List<String> result = new ArrayList<>();
        for (String s1 : arr1) {
            int c1 = StringUtil.count(s1, "-");
            if (steps == c1) {
                result.add(s1);
            } else if (steps > c1) {// try replenish steps
                String[] arr2 = strategy.getRoutesBetweenTwoNodes(ending, ending);
                for (String s2 : arr2) {
                    int c2 = StringUtil.count(s2, "-");
                    if (c1 + c2 == steps) {
                        result.add(s1 + s2.substring(1));
                    }
                }
            }
        }

        if (DEBUG_ENABLE)
            GraphUtil.print(result.toArray(new String[result.size()]));

        return result.size();
    }

    /**
     *
     * @param starting
     * @param ending
     * @param distance distance must be large than 0.
     * @return
     */
    public int getRepeatableRoutesWithinDistance(Node starting, Node ending, int distance) {
        AssertUtil.check(distance > 0, "distance must be large than 0.");
        List<String> result = new ArrayList<>();
        String[] arr1 = strategy.getRoutesBetweenTwoNodes(starting, ending);
        int[] dist1 = getDistances(arr1);
        String[] arr2 = strategy.getRoutesBetweenTwoNodes(ending, ending);
        int[] dist2 = getDistances(arr2);

        for (int i = 0; i < arr1.length; i++) {
            if (dist1[i] < distance) {
                result.add(arr1[i]);
            }

            int length2 = arr2.length;
            for (int j = 0; j < length2; j++) {
                int d = dist1[i] + dist2[j];
                String s2 = arr2[j];
                while (d < distance) {
                    result.add(arr1[i] + s2.substring(1, s2.length()));
                    d += dist2[j];
                }
            }
        }

        if (DEBUG_ENABLE)
            GraphUtil.print(result.toArray(new String[result.size()]));

        return result.size();
    }

    /**
     * get distance of all route.
     *
     * @param arr user input array, element must be formatted string,
     *             the length of string must be 1, must be split by `-`, the value is node name,
     * @return
     */
    public int[] getDistances(String[] arr) {
        int[] dist = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            int d = getSpecialRouteDistance(s);
            dist[i] = d;
        }
        return dist;
    }

    /**
     * proxy method of strategy.
     *
     * @param starting
     * @return
     */
    public int[] getShortestDistance2AllNodes(Node starting) {
        return strategy.getShortestDistances(starting);
    }

}
