package com.dshiferaw.Graph_Routing.Graph;

/** Graph Implementation */
public interface Graph {

    /** Returns number of nodes */
    int N();

    /** Returns number of edges */
    int E();

    /** Add a ndoe */
    void addNode(int n);

    /** Adds an edge between nodes */
    void addEdge(Edge edge);

    /** Returns adjacent nodes of a node */
    Iterable<Edge> getAdjacents(int n);

    /** Returns all the nodes */
    Iterable<Integer> nodes();

    /** Returns all the edges in the graph */
    Iterable<Edge> edges();

    /** Returns index of a node */
    int getIndex(int n);
}
