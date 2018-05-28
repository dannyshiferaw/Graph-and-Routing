/**
 * Breadth First Search Implementation based on
 * Adjacent List Graph Representation
 *
 * Written By: Daniel Shiferaw
 * May 25th, 2018
 */

package com.dshiferaw.Graph_Routing.Graph_Processing;

import com.dshiferaw.Graph_Routing.Graph.Edge;
import com.dshiferaw.Graph_Routing.Graph.Graph;

import java.util.*;

public class BFS {

    public BFS(Graph graph, int source) {
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
    public void bfs() {
        Queue<Integer> fringe = new ArrayDeque<>();
        fringe.add(source);
        visited_Nodes.add(source);
        distTo.put(source, 0.0f);
        while(!fringe.isEmpty()) {
            int from = fringe.remove();
            for (Edge edge: graph.getAdjacents(from)) {
                int to = edge.other(from);
                if(!visited_Nodes.contains(to)) {
                    path.put(to, from);
                    distTo.put(to, distTo.get(from) + edge.weight());
                    visited_Nodes.add(to);
                    fringe.add(to);

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

    public List<Integer> getPathTo(int n) {
        Stack<Integer> p = new Stack<>();
        if (hasPathTo(n)) {
            while(n != source) {
                p.push(n);
                n = path.get(n);
            }
            p.add(source);
        }
        return p;
    }

    public float getDistTo(int n) {
        if (hasPathTo(n)) {
            return distTo.get(n);
        }
        return -1;
    }
}
