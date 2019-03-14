package com.linxb.railroad.center.utils;

import com.linxb.railroad.center.Environment;

/**
 * @Description output printer.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class ConsoleUtil {

    public static void output(int index, int value) {
        System.out.println(String.format("Output #%s: %s",
                index, (value == Environment.N) ? Environment.NO_ROUTE_TIPS : value + ""));
    }

    public static void input() {
        for (int i = 0; i < Problems.size(); i++) {
            System.out.println(Problems.get(i));
        }
    }
}
