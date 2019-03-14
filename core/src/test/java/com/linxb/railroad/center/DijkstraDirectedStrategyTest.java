package com.linxb.railroad.center;

import com.linxb.railroad.center.entity.Edge;
import com.linxb.railroad.center.entity.Node;
import com.linxb.railroad.center.impl.DijkstraDirectedStrategy;
import com.linxb.railroad.center.utils.GraphUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DijkstraDirectedStrategyTest {

    @Test
    public void testDijkstraDirectedStrategyInit() {

        /**
         *
         * AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
         *
         *    A  B  C  D  E
         * A: N, 5, N, 5, 7
         * B: N, N, 4, N, N
         * C: N, N, N, 8, 2
         * D: N, N, 8, N, 6
         * E: N, 3, N, N, N
         *
         */

        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };

        new DijkstraDirectedStrategy(edges, 5);
    }


    public void init() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };

    }

    @Test
    public void testToMatrix() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };

        int stops = 5;
        DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);
        int[][] matrix = strategy.toMatrix(edges, stops);
        GraphUtil.print(matrix, stops);
    }

    @Test
    public void testToMatrix1() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };

        try {
            int stops = 4;// less than actual number of stops.
            DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);
            int[][] matrix = strategy.toMatrix(edges, stops);
            GraphUtil.print(matrix, stops);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testToMatrix2() {
        try {
            int stops = 5;
            // check null.
            DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(null, stops);
            int[][] matrix = strategy.toMatrix(null, stops);
            GraphUtil.print(matrix, stops);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetShortestDistance2AllNodes() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };

        int stops = 5;
        // check null.
        DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);
        int[] distance = strategy.getShortestDistances(new Node("A"));
        for (int d : distance) {
            System.out.println("" + d);
        }
    }


    @Test
    public void testGetShortestDistance2AllNodes1() {

        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };

        try {
            int stops = 5;
            // check null.
            DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);
            int[] distance = strategy.getShortestDistances(new Node("F"));// the node is not exist.
            for (int d : distance) {
                System.out.println("" + d);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetSpecialRoutDistance() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };

        int stops = 5;
        // check null.
        DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);
        Edge[] route = new Edge[]{new Edge("A", "B"), new Edge("B", "C")};
        int distance = strategy.getSpecialRoutDistance(route);// the node is not exist.
        System.out.println("" + distance);
    }


    @Test
    public void testGetSpecialRoutDistance1() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };

        try {
            int stops = 5;
            // check null.
            DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);

            Edge[] route = new Edge[]{new Edge("A", "B"), null};// check null
            int distance = strategy.getSpecialRoutDistance(route);
            System.out.println("" + distance);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetSpecialRoutDistance2() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };

        try {
            int stops = 5;
            // check null.
            DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);
            Edge[] route = new Edge[]{new Edge("1", "B"), new Edge("1", "C")};// unformatted string
            int distance = strategy.getSpecialRoutDistance(route);
            System.out.println("" + distance);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetRoutesBetweenTwoNodes() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };
        int stops = 5;
        DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);
        String[] routes = strategy.getRoutesBetweenTwoNodes(new Node("B"), new Node("D"));
        for (String r : routes) {
            System.out.println(r);
        }
    }

    @Test
    public void testGetRoutesBetweenTwoNodes1() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };
        try {
            int stops = 5;
            DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);
            String[] routes = strategy.getRoutesBetweenTwoNodes(new Node("-1"), new Node("D"));// unformatted node.
            for (String r : routes) {
                System.out.println(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }


    @Test
    public void testHeap() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };
        try {
            int stops = 5;
            DijkstraDirectedStrategy strategy = new DijkstraDirectedStrategy(edges, stops);
            strategy.getShortDistancesByHeap(new Node("A"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    private DijkstraDirectedStrategy strategy = null;

    @Before
    public void before() {
        Edge[] edges = new Edge[]{
                new Edge("A", "B", 5),
                new Edge("B", "C", 4),
                new Edge("C", "D", 8),
                new Edge("D", "C", 8),
                new Edge("D", "E", 6),
                new Edge("A", "D", 5),
                new Edge("C", "E", 2),
                new Edge("E", "B", 3),
                new Edge("A", "E", 7)
        };
        int stops = 5;
        strategy = new DijkstraDirectedStrategy(edges, stops);
    }

    @Test
    public void shouldGetTheDurationOfGivenRoute() {
        assert strategy != null;
        // given
        int unit = 1;// hour min, sec
        Edge[] edges = new Edge[]{
                new Edge("A", "D"),
                new Edge("D", "C")
        };
        // when
        int distance  = strategy.getSpecialRoutDistance(edges);// AB5, CD4

        // distance * 1 + nodes *2;
        int duration = strategy.getTheDurationOfGivenRoute(edges, distance, unit);

        // then
        Assert.assertEquals(15, duration);
    }

}
