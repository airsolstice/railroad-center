package com.linxb.railroad.center.entity;

/**
 * @Description edge represents a route between two towns.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class Edge implements Comparable<Edge> {

    /**
     * starting node.
     */
    private Node starting;
    /**
     * ending node.
     */
    private Node ending;
    /**
     * weight of the edge, it indicates how long between two nodes. if 2 nodes is unreachable, weight is N.
     */
    private int weighting;

    private int index;

    public Edge(String starting, String ending) {
        this(new Node(starting), new Node(ending));
    }

    public Edge(Node starting, Node ending) {
        this(starting, ending, 0);
    }

    public Edge(String starting, String ending, int weighting) {
        this(new Node(starting), new Node(ending), weighting);
    }

    public Edge(Node starting, Node ending, int weighting) {
        this.starting = starting;
        this.ending = ending;
        this.weighting = weighting;
    }

    public Edge(int index, int weighting){
        this.weighting = weighting;
        this.index = index;
    }

    public Node getStarting() {
        return starting;
    }

    public void setStarting(Node starting) {
        this.starting = starting;
    }

    public Node getEnding() {
        return ending;
    }

    public void setEnding(Node ending) {
        this.ending = ending;
    }

    public int getWeighting() {
        return weighting;
    }

    public void setWeighting(int weighting) {
        this.weighting = weighting;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "starting=" + starting +
                ", ending=" + ending +
                ", weighting=" + weighting +
                '}';
    }

    @Override
    public int compareTo(Edge e) {
        if (this.weighting > e.getWeighting()) {
            return 1;
        }

        if (this.weighting < e.getWeighting()) {
            return -1;
        }
        return 0;
    }
}
