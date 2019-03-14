package com.linxb.railroad.center.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description problems provider.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class Problems {

    private static final int SIZE = 10;
    private static final List<String> PROBLEMS = new ArrayList<>(SIZE);

    static {
        PROBLEMS.add(0, "1.The distance of the route A-B-C.");
        PROBLEMS.add(1, "2.The distance of the route A-D.");
        PROBLEMS.add(2, "3.The distance of the route A-D-C.");
        PROBLEMS.add(3, "4.The distance of the route A-E-B-C-D.");
        PROBLEMS.add(4, "5.The distance of the route A-E-D.");
        PROBLEMS.add(5, "6.The number of trips starting at C and ending at C with a maximum of 3 stops.");
        PROBLEMS.add(6, "7.The number of trips starting at A and ending at C with exactly 4 stops.");
        PROBLEMS.add(7, "8.The length of the shortest route (in terms of getDistance to travel) from A to C.");
        PROBLEMS.add(8, "9.The length of the shortest route (in terms of getDistance to travel) from B to B.");
        PROBLEMS.add(9, "10.The number of different routes from C to C with a getDistance of less than 30. ");
    }

    public static String get(int index) {
        AssertUtil.check(index < SIZE, "out of bound.");
        return PROBLEMS.get(index);
    }

    public static int size() {
        return SIZE;
    }

}
