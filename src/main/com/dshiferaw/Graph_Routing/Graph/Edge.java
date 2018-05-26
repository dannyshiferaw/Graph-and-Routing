package com.dshiferaw.Graph_Routing.Graph;

public class Edge implements Comparable<Edge> {

    public Edge(int from, int to, float weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Edge(int from, int to) {
        this(from, to, 0);
    }

    private final int from;
    private final int to;
    private final float weight;

    /** Weight of this edge */
    public float weight() {
        return weight;
    }

    /** Either of the edge nodes */
    public int either() {
        return to;
    }

    /** The other end of the edge */
    public int other(int n) {
        if (n == from) return to;
        else if (n == to) return from;
        else throw new RuntimeException("Such Edge Doesn't Exist");
    }

    /** Compares this edge with another
     *
     * @param o
     * @return 0 if equal, > 0 if this edge is greater, < 0 otherwise
     */
    @Override
    public int compareTo(Edge o) {
        return Float.compare(weight, o.weight);
    }

    /** String representation of this edge */
    @Override
    public String toString() {
        return from + "- " + to;
    }
}
