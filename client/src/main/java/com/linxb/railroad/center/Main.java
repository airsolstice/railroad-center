package com.linxb.railroad.center;

import com.linxb.railroad.center.entity.Node;
import com.linxb.railroad.center.utils.ConsoleUtil;
import com.linxb.railroad.center.utils.FileUtil;
import com.linxb.railroad.center.utils.StringUtil;

import java.util.Map;
import java.util.Scanner;

/**
 * railroad center program.
 *
 * @author linxb
 * @date 2019/02/28
 * @email airsolstice@outlook.com
 */
public class Main {

    /**
     * entrance of program.
     *
     * @param args
     */
    public static void main(String[] args) {
        // read config.
        Map<String, Object> conf = FileUtil.parseConfig("graph.txt", "=");
        String graph = (String) conf.get("graph");
        int size = Integer.valueOf((String) conf.get("size"));

        // buildHeap environment of railroad center.
        Environment env = new Environment(graph, size);

        System.out.println("Test Input:");
        ConsoleUtil.input();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nplease input 1-10, choose print result, input 0 indicates print all results:");
            int index = scanner.nextInt();
            System.out.println("Test Output:");
            boolean flag = true;
            switch (index) {
                case 0:
                    flag = false;
                case 1:
                    ConsoleUtil.output(1, env.getSpecialRouteDistance("A-B-C"));
                    if(flag){
                        break;
                    }
                case 2:
                    ConsoleUtil.output(2, env.getSpecialRouteDistance("A-D"));
                    if(flag){
                        break;
                    }
                case 3:
                    ConsoleUtil.output(3, env.getSpecialRouteDistance("A-D-C"));
                    if(flag){
                        break;
                    }
                case 4:
                    ConsoleUtil.output(4, env.getSpecialRouteDistance("A-E-B-C-D"));
                    if(flag){
                        break;
                    }
                case 5:
                    ConsoleUtil.output(5, env.getSpecialRouteDistance("A-E-D"));
                    if(flag){
                        break;
                    }
                case 6:
                    ConsoleUtil.output(6, env.getSpecialRoutDistanceWithinSteps(new Node("C"), new Node("C"), 3));
                    if(flag){
                        break;
                    }
                case 7:
                    ConsoleUtil.output(7, env.getRepeatableRoutesWithoutSteps(new Node("A"), new Node("C"), 4));
                    if(flag){
                        break;
                    }
                case 8:
                    String staring8 = "A";
                    String ending8 = "C";
                    ConsoleUtil.output(8,
                            env.getShortestDistance2AllNodes(new Node(staring8))[StringUtil.mapLetter2Index(ending8)]);
                    if(flag){
                        break;
                    }
                case 9:
                    String staring9 = "B";
                    String ending9 = "B";
                    ConsoleUtil.output(9,
                            env.getShortestDistance2AllNodes(new Node(staring9))[StringUtil.mapLetter2Index(ending9)]);
                    if(flag){
                        break;
                    }
                case 10:
                    String starting10 = "C";
                    String ending10 = "C";
                    int distance = 30;
                    ConsoleUtil.output(10,
                            env.getRepeatableRoutesWithinDistance(new Node(starting10), new Node(ending10), distance));
                    if(flag){
                        break;
                    }
            }
        }
    }
}
