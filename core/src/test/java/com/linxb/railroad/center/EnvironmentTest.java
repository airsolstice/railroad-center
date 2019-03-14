package com.linxb.railroad.center;

import com.linxb.railroad.center.entity.Edge;
import com.linxb.railroad.center.entity.Node;
import org.junit.Test;

public class EnvironmentTest {

    @Test
    public void testEnvironmentInit1() {
        String input1 = "AB2, CD3,AC2";// formatted
        new Environment(input1, 5);// print matrix
    }

    @Test
    public void testEnvironmentInit2() {
        try {
            String input1 = "AB2, CD3,,AC2";// unformatted. contains empty.
            new Environment(input1, 5);// throw a exception.
        } catch (Exception e) {
            System.out.println("normal result is throwing a exception.");
            e.printStackTrace();
        }

    }

    @Test
    public void testEnvironmentInit3() {
        try {
            String input1 = "AB2, CD3s,AC2";// unformatted, length is not equal to 3.
            new Environment(input1, 5);// throw a exception.
        } catch (Exception e) {
            System.out.println("normal result is throwing a exception.");
            e.printStackTrace();
        }
    }

    @Test
    public void testEnvironmentInit4() {
        try {
            String input1 = "AB2.CD3.AC2";// unformatted, separator is not equal to `,`.
            new Environment(input1, 5);// throw a exception.
        } catch (Exception e) {
            System.out.println("normal result is throwing a exception.");
            e.printStackTrace();
        }
    }

    @Test
    public void testEnvironmentInit5() {
        try {
            String input = "AB6,BC3,AC4,CD6";
            int stops = 3;// total of stops is 3, but has A, B, C, D 4 stops.
            new Environment(input, stops);// throw a exception.
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testEnvironmentInit6() {
        String input = "Ab6,Bc3,AC4,CD6";// ignore case.
        int stops = 4;
        new Environment(input, stops);
    }

    @Test
    public void testGetSpecialRoutDistance1() {
        String input = "AB6,BC3,AC4,CD6";
        int stops = 4;
        Environment env = new Environment(input, stops);
        System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance("A-B-C"));
    }

    @Test
    public void testGetSpecialRoutDistance2() {
        try {
            String input = "AB6,BC3,AC4,CD6";
            int stops = 4;
            Environment env = new Environment(input, stops);
            System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance("A,B,C"));// separator must be `-`, thus, throw a exception.
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetSpecialRoutDistance3() {
        try {
            String input = "AB6,BC3,AC4,CD6";
            int stops = 4;
            Environment env = new Environment(input, stops);
            System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance("AB-C"));// length of each split value must be 1, thus, throw a exception.
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetSpecialRoutDistance4() {
        String input = "AB6,BC3,AC4,CD6";
        int stops = 4;
        Environment env = new Environment(input, stops);
        System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance("A- B -C"));// can contain blank.
    }

    @Test
    public void testGetSpecialRoutDistance5() {
        try {
            String input = "AB6,BC3,AC4,CD6";
            int stops = 4;
            Environment env = new Environment(input, stops);
            System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance("A--C"));// cannot contain empty.
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }

    }

    @Test
    public void testGetSpecialRoutDistance6() {
        String input = "AB6,BC3,AC4,CD6";
        int stops = 4;
        Environment env = new Environment(input, stops);

        Edge[] edges = new Edge[]{new Edge("A", "B"), new Edge("B", "C")};
        System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance(edges));
    }

    @Test
    public void testGetSpecialRoutDistance7() {
        try {
            String input = "AB6,BC3,AC4,CD6";
            int stops = 4;
            Environment env = new Environment(input, stops);

            Edge[] edges = new Edge[]{new Edge("A", "B"), null};
            System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance(edges));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetSpecialRoutDistance8() {
        try {
            String input = "AB6,BC3,AC4,CD6";
            int stops = 4;
            Environment env = new Environment(input, stops);

            Edge[] edges = new Edge[]{null, new Edge("B", "C")};
            System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance(edges));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetSpecialRoutDistance9() {
        try {
            String input = "AB6,BC3,AC4,CD6";
            int stops = 4;
            Environment env = new Environment(input, stops);
            Edge[] edges = null;
            System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance(edges));// arr could not be null.
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetSpecialRoutDistance10() {
        String input = "AB6,BC3,AC4,CD6";
        int stops = 4;
        Environment env = new Environment(input, stops);
        Edge[] edges = new Edge[]{new Edge("a", "B"), new Edge("b", "C")};// ignore case.
        System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance(edges));
    }


    @Test
    public void testGetSpecialRoutDistance11() {
        String input = "AB6,BC3,AC4,CD6";
        int stops = 4;
        Environment env = new Environment(input, stops);
        System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance(new Node("A"),
                new Node("B"), new Node("C")));
    }

    @Test
    public void testGetSpecialRoutDistance12() {
        String input = "AB6,BC3,AC4,CD6";
        int stops = 4;
        Environment env = new Environment(input, stops);
        System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance(new Node("A"),
                new Node("b"), new Node("c")));// ignore case.
    }

    @Test
    public void testGetSpecialRoutDistance13() {
        String input = "AB6,BC3,AC4,CD6";
        int stops = 4;
        Environment env = new Environment(input, stops);
        System.out.println("expect is 9, actual is " + env.getSpecialRouteDistance());// if no parameter, return 0.
    }

    @Test
    public void testGetSpecialRoutDistanceWithinSteps1() {
        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        int stops = 5;
        Environment env = new Environment(input, stops);
        // result is A-B-C, A-D-C.
        System.out.println("expect is 2, actual is "
                + env.getSpecialRoutDistanceWithinSteps(new Node("A"), new Node("C"), 2));
    }

    @Test
    public void testGetSpecialRoutDistanceWithinSteps2() {
        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        int stops = 5;
        Environment env = new Environment(input, stops);
        // return 0, because A-A is not reachable..
        System.out.println("expect is 2, actual is "
                + env.getSpecialRoutDistanceWithinSteps(new Node("A"), new Node("A"), 2));
    }

    @Test
    public void testGetSpecialRoutDistanceWithinSteps3() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // check null.
            System.out.println("expect is 2, actual is "
                    + env.getSpecialRoutDistanceWithinSteps(null, new Node("c"), 2));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetSpecialRoutDistanceWithinSteps4() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // steps must be large than 0.
            System.out.println("expect is 2, actual is "
                    + env.getSpecialRoutDistanceWithinSteps(new Node("A"), new Node("c"), 0));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetRepeatableRoutesWithoutSteps1() {
        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        int stops = 5;
        Environment env = new Environment(input, stops);
        // steps must be large than 0.
        System.out.println("expect is 3, actual is "
                + env.getRepeatableRoutesWithoutSteps(new Node("A"), new Node("C"), 4));
    }

    @Test
    public void testGetRepeatableRoutesWithoutSteps2() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // steps must be large than 0.
            System.out.println("expect is 2, actual is "
                    + env.getRepeatableRoutesWithoutSteps(new Node("A"), new Node("c"), 0));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetRepeatableRoutesWithoutSteps3() {
        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        int stops = 5;
        Environment env = new Environment(input, stops);
        // ignore case.
        System.out.println("expect is 2, actual is "
                + env.getRepeatableRoutesWithoutSteps(new Node("A"), new Node("c"), 3));
    }

    @Test
    public void testGetRepeatableRoutesWithoutSteps4() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // check null.
            System.out.println("expect is 2, actual is "
                    + env.getRepeatableRoutesWithoutSteps(null, new Node("c"), 3));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void getRepeatableRoutesWithinDistance1() {
        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        int stops = 5;
        Environment env = new Environment(input, stops);
        // check null.
        System.out.println("expect is 7, actual is "
                + env.getRepeatableRoutesWithinDistance(new Node("C"), new Node("C"), 30));
    }

    @Test
    public void getRepeatableRoutesWithinDistance2() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // check null.
            System.out.println("expect is 7, actual is "
                    + env.getRepeatableRoutesWithinDistance(null, new Node("C"), 30));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void getRepeatableRoutesWithinDistance3() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // distance must be large than 0.
            System.out.println("expect is 7, actual is "
                    + env.getRepeatableRoutesWithinDistance(new Node("C"), new Node("C"), 0));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetDistances1() {

        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        int stops = 5;
        Environment env = new Environment(input, stops);
        // except is 9 nad 12
        for (int d : env.getDistances(new String[]{"A-B-C", "B-C-D"})) {
            System.out.println(d);
        }

    }

    @Test
    public void testGetDistances2() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // must be input formatted string.
            // except is 9 nad 12
            for (int d : env.getDistances(new String[]{"A1-B-C", "B-C-D"})) {
                System.out.println(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetDistances3() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // must be input formatted string.
            // except is 9 nad 12
            for (int d : env.getDistances(new String[]{"A,B-C", "B-C-D"})) {
                System.out.println(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

    @Test
    public void testGetShortestDistance2AllNodes1() {
        String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        int stops = 5;
        Environment env = new Environment(input, stops);
        // must be input formatted string.
        for (int d : env.getShortestDistance2AllNodes(new Node("A"))) {
            System.out.println(d);
        }
    }

    @Test
    public void testGetShortestDistance2AllNodes2() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // must be input formatted string.
            // input blank string.
            for (int d : env.getShortestDistance2AllNodes(new Node(""))) {
                System.out.println(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");

        }
    }
    @Test
    public void testGetShortestDistance2AllNodes3() {
        try {
            String input = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
            int stops = 5;
            Environment env = new Environment(input, stops);
            // must be input formatted string.
            // input numeric.
            for (int d : env.getShortestDistance2AllNodes(new Node("1"))) {
                System.out.println(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("normal result is throwing a exception.");
        }
    }

}
