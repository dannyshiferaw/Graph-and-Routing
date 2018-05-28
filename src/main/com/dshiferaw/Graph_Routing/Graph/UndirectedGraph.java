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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class UndirectedGraph implements Graph{

    public UndirectedGraph(int numOfNodes) {
        this.numOfNodes = numOfNodes ;
        this.numOfEdges = 0;
        this.nodes = new ArrayList<>(this.numOfNodes);
        this.node_map = new HashMap<>();

        for (int i = 0; i < this.numOfNodes; i++) {
            this.nodes.add(i, new ArrayList<>());
        }
    }

    public UndirectedGraph() {
        this.numOfNodes = 0;
        this.numOfEdges = 0;
        this.nodes = new ArrayList<>();
        this.node_map = new HashMap<>();
    }

    /** All Nodes */
    private ArrayList<ArrayList<Edge>> nodes;

    private HashMap<Integer, Integer> node_map;

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


    /** add a node */
    public void addNode(int n) {
        if(!node_map.containsKey(n)) {
            nodes.add(new ArrayList<>());
            int index  = nodes.size() - 1;
            node_map.put(n, index);
            numOfNodes++;
        }
    }

    /** Returns all node */
    @Override
    public Iterable<Integer> nodes() {
        return node_map.keySet();
    }

    /** Undirected Edge
     * @param edge
     */
    @Override
    public void addEdge(Edge edge) {
//        int to = edge.either(), from = edge.other(to);
//        nodes.get(from).add(edge);
//        nodes.get(to).add(edge);
//        numOfEdges++;
        int to = edge.either(), from = edge.other(to);
        nodes.get(getIndex(from)).add(edge);
        nodes.get(getIndex(to)).add(edge);
        numOfEdges++;

    }

    /**
     * Returns adjacent nodes of a given node
     * @param n
     * @return neighbouring nodes
     */
    @Override
    public Iterable<Edge> getAdjacents(int n) {
        return nodes.get(getIndex(n));
    }

    /** Returns all edges except self loops */
    @Override
    public Iterable<Edge> edges() {
        List<Edge> edges = new LinkedList<>();
        for (int n = 0; n < numOfNodes; n++) {
            for (Edge e: getAdjacents(getIndex(n))) {
                if (e.other(n) != n) {
                    edges.add(e);
                }
            }
        }
        return edges;
    }

    /**
     * Returns an index of a node
     * @param n
     * @return index
     */
    public int getIndex(int n) {
        if(node_map.containsKey(n)) {
            return node_map.get(n);
        }
        throw new RuntimeException("Invalid Node");
    }

}
