/**
 * Dijkstra Algorithm Implementation based on
 * Adjacent List Graph Representation
 *
 * Written By: Daniel Shiferaw
 * May 25th, 2018
 */

package com.dshiferaw.Graph_Routing.Graph_Processing;

import com.dshiferaw.Graph_Routing.Graph.Edge;
import com.dshiferaw.Graph_Routing.Graph.Graph;

import java.util.*;

public class Dijkstra {

    public class Node implements Comparable<Node> {

        public Node(int n, float weight) {
            this.n = n;
            this.weight = weight;
        }

        private int n;
        private float weight;

        /** Returns the vertex of this node */
        public int n() {
            return n;
        }

        /** Returns the weight of this node */
        public float weight() {
            return weight;
        }

        /**
         * Compares two nodes using weight, or index if both have the same weight
         * @param o
         * @return 0 if both have the same n and weight,
         *         -1 if this nodes weight is less, or both have same weight
         *             and this node's n is less,
         *          1 otherwise
         */
        @Override
        public int compareTo(Node o) {
            int cmp = Float.compare(weight, o.weight());
            if (cmp == 0) {
                return Integer.compare(n, o.n());
            }
            return cmp;
        }

    }

    public Dijkstra(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.visited_Nodes = new HashSet<>();
        this.path = new HashMap<>();
        this.distTo = new HashMap<>();
    }

    private Graph graph;
    private int source;
    private HashSet<Integer> visited_Nodes;
    private HashMap<Integer, Integer> path;
    private HashMap<Integer, Float> distTo;

    /**
     * Breadth First Search
     */
    public void dijkstra() {
        TreeSet<Node> pq = new TreeSet<>();
        for (int i = 1; i <= graph.N(); i++) {
            distTo.put(i, Float.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0f);
        pq.add(new Node(source, 0.0f));
        visited_Nodes.add(source);
        while(!pq.isEmpty()) {
            Node from = pq.pollFirst();
            for (Edge edge: graph.getAdjacents(from.n())) {
                int to = edge.other(from.n());
                float oldWeight = distTo.get(to);
                float newWeight = edge.weight() + from.weight();
                //if there is a shorter distance to a node
                //delete the previous one, and add the newupdate
                if (oldWeight > newWeight && !visited_Nodes.contains(to)) {
                    distTo.put(to, newWeight);
                    path.put(to, from.n());
                    Node updatedNode = new Node(to, newWeight);
                    Node oldNode = new Node(to, oldWeight);
                    pq.remove(oldNode);
                    pq.add(updatedNode);
                }
            }
        }

    }

    /**
     * Returns true if there exists a path to a node,
     * or if a given node is the source node
     */
    public boolean hasPathTo(int n) {
        return path.get(n) != null || n == source;
    }

    /**
     * Returns a path that leads to a node
     * @param n
     * @return path
     */
    public List<Integer> getPathTo(int n) {
        Stack<Integer> p = new Stack<>();
        while(n != source) {
            p.push(n);
            n = path.get(n);
        }
        return p;
    }

    /**
     * Returns the total distance or cost it takes to get to a node
     * @param n
     * @return weight
     */
    public float getDistTo(int n) {
        if (hasPathTo(n)) {
            return distTo.get(n);
        }
        return -1;
    }
}
