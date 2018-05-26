/**
 *
 *
 * Implementation of undirected weighted graph using Adjacency List
 *
 * Written By: Daniel Shiferaw
 * May 25th, 2018
 */

package com.dshiferaw.Graph_Routing.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class UndirectedGraph implements Graph{

    public UndirectedGraph(int numOfNodes) {
        this.numOfNodes = numOfNodes ;
        this.numOfEdges = 0;
        this.nodes = new ArrayList<>(this.numOfNodes + 1);

        for (int i = 0; i <= this.numOfNodes; i++) {
            this.nodes.add(i, new ArrayList<>());
        }
    }

    /** All Nodes */
    private ArrayList<ArrayList<Edge>> nodes;

    /** Total number of nodes */
    private int numOfNodes;

    /** Total number of edges */
    private int numOfEdges;

    /** returns the total number of nodes */
    @Override
    public int N() {
        return numOfNodes;
    }

    /** returns the total number of edges */
    @Override
    public int E() {
        return numOfEdges;
    }

    /** Undirected Edge
     * @param edge
     */
    @Override
    public void addEdge(Edge edge) {
        int to = edge.either(), from = edge.other(to);
        nodes.get(from).add(edge);
        nodes.get(to).add(edge);
        numOfEdges++;

    }

    @Override
    public Iterable<Edge> getAdjacents(int n) {
        return nodes.get(n);
    }

    /** Returns all edges except self loops */
    @Override
    public Iterable<Edge> edges() {
        List<Edge> edges = new LinkedList<>();
        for (int n = 1; n <= numOfNodes; n++) {
            for (Edge e: getAdjacents(n)) {
                if (e.other(n) != n) {
                    edges.add(e);
                }
            }
        }
        return edges;
    }

}
