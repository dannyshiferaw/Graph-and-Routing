package com.dshiferaw.Graph_Routing;


import com.dshiferaw.Graph_Routing.Graph.Edge;
import com.dshiferaw.Graph_Routing.Graph.Graph;
import com.dshiferaw.Graph_Routing.Graph.UndirectedGraph;
import com.dshiferaw.Graph_Routing.Graph_Processing.Dijkstra;
import com.dshiferaw.Graph_Routing.Import_Data.DEdge;
import com.dshiferaw.Graph_Routing.Import_Data.DNode;
import com.dshiferaw.Graph_Routing.Import_Data.SFRoadService;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;


import java.util.*;

public class main {


    public static void main(String[] args) throws Exception {

        //load data
        Map<String, Object> data = SFRoadService.load();

        //Nodes
        @SuppressWarnings(value = "unchecked")
        List<DNode> nodes = (List) data.get("Nodes");
        //Edges
        @SuppressWarnings(value = "unchecked")
        List<DEdge> edges = (List) data.get("Edges");



        //populate the local graph with the data
        Graph graph = new UndirectedGraph();
        //add nodes
        for (DNode dnode: nodes) {
            graph.addNode(dnode.getNodeId());
        }
        //add edges
        for (DEdge edge: edges) {
            graph.addEdge(new Edge(edge.getStartNodeId(), edge.getEndNodeId(), edge.getDist()));
        }

        Dijkstra djk = new Dijkstra(graph, 10);
        djk.dijkstra();                 //run dijkstra given a source
        List<Integer> p = djk.getPathTo(83);        //shortest path between source and destination

        visualize(nodes, edges, p);

    }

    private static void visualize(List<DNode> nodes, List<DEdge> edges, List<Integer> path) {
        //styleSheet for graph visualization
        String styleSheet =
                "node {" +
                        "	fill-color: black;" +
                        "}" +
                        "node.marked {" +
                        "	fill-color: red;" +
                        "}";


        //visualization graph
        org.graphstream.graph.Graph g = new SingleGraph("San Francisco Road Network");
        g.addAttribute("ui.stylesheet", styleSheet);
        g.setAutoCreate(true);
        g.setStrict(false);

        //load Nodes
        for(DNode node: nodes) {
            g.addNode(Integer.toString(node.getNodeId()));
        }

        //load Edges
        for(DEdge edge: edges) {
            g.addEdge(Integer.toString(edge.getEdgeId()),
                    edge.getStartNodeId(), edge.getEndNodeId());
        }

        //display graph
        g.display();

        //display shortest path on graph
        sleep();
        Iterator<Integer> p =  path.iterator();
        while(p.hasNext()) {
            Node next = g.getNode(p.next());
            next.addAttribute("ui.class", "marked");
            sleep();
        }


    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
