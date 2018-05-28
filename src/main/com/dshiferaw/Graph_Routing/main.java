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
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import static org.graphstream.algorithm.Toolkit.*;


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
                        "   text-size: 20px; " +
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

        //add pins at start and end
        SpriteManager spriteManager = new SpriteManager(g);
        Sprite s1 = spriteManager.addSprite("startNode");
        Sprite s2 = spriteManager.addSprite("endNode");
        double[] s1Position = nodePosition(g.getNode(path.get(0)));
        double[] s2Position = nodePosition(g.getNode(path.get(path.size()-1)));
        s1.setPosition(s1Position[0], s1Position[1], s1Position[2]);
        s2.setPosition(s2Position[0], s2Position[1], s2Position[2]);

        g.getNode(path.get(0)).addAttribute("ui.label", path.get(0));
        g.getNode(path.get(path.size()-1)).addAttribute("ui.label", path.get(path.size() -1 ));



        //display shortest path on graph
        Iterator<Integer> p =  path.iterator();
        while(p.hasNext()) {
            Node next = g.getNode(p.next());
            next.addAttribute("ui.class", "marked");
            sleep();
        }


    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
