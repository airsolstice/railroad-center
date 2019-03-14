package com.linxb.railroad.center.entity;

/**
 * @Description node represents a town.
 * @Date 2019/02/28
 * @Author linxb
 * @Email airsolstice@outlook.com
 */
public class Node {
    /**
     * name of town.
     */
    private String townName;
    /**
     * whether or not is a starting node.
     */
    private boolean isStarting;
    /**
     * whether or not is a ending node.
     */
    private boolean isEnding;

    public Node() {
    }

    public Node(String townName) {
        this.townName = townName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public boolean isStarting() {
        return isStarting;
    }

    public void setStarting(boolean starting) {
        isStarting = starting;
    }

    public boolean isEnding() {
        return isEnding;
    }

    public void setEnding(boolean ending) {
        isEnding = ending;
    }

    @Override
    public String toString() {
        return "townName='" + townName + '\'';
    }
}
