package com.linxb.railroad.center;

import com.linxb.railroad.center.entity.Edge;
import org.junit.Test;

public class HeapTest {

    @Test
    public void testInit() {

        Edge[] edges = new Edge[]{
                new Edge("A1", "B", 19),
                new Edge("B2", "C", 38),
                new Edge("C3", "D", 7),
                new Edge("D4", "C", 36),
                new Edge("D5", "E", 5),
                new Edge("A6", "D", 5),
                new Edge("C7", "E", 3),
                new Edge("E8", "B", 2),
                new Edge("D9", "B", 1),
                new Edge("C10", "B", 0),
                new Edge("C11", "A", 56)

        };

        for(Edge e : edges){
            System.out.print(e.toString() + ",");
        }
        System.out.println();
        Heap heap = new Heap();
//        heap.buildHeap(edges, Heap.Sort.ASC);
        heap.sort(edges, Heap.Sort.ASC);

        for(Edge e : edges){
            System.out.print(e.toString() + ",");
        }
        heap.sort(edges, Heap.Sort.DESC);


        for(Edge e : edges){
            System.out.print(e.toString() + ",");
        }

    }
}
