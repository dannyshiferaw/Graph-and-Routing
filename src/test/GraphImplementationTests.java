/**
 * This file test the implementation of Graph
 *
 * It adds several nodes and edges, and checks if they are
 * accurately presented.
 */



import com.dshiferaw.Graph_Routing.Graph.Edge;
import com.dshiferaw.Graph_Routing.Graph.Graph;
import com.dshiferaw.Graph_Routing.Graph.UndirectedGraph;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import static org.junit.Assert.*;


public class GraphImplementationTests {

    @Test
    public void testGraphImplementation() {
        try {
            FileReader inputFileReader = new FileReader("/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/inputs/GraphInput01.txt");
            BufferedReader inputBufferReader = new BufferedReader(inputFileReader);
            Scanner inputScanner = new Scanner(inputBufferReader);

            FileReader outputFileReader = new FileReader("/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/outputs/GraphOutput01.txt");
            BufferedReader outputBufferReader = new BufferedReader(outputFileReader);
            Scanner outputScanner = new Scanner(outputBufferReader);


            int numOfTests = inputScanner.nextInt();
            while (numOfTests > 0) {
                int numOfNodes = inputScanner.nextInt();
                int numOfEdges = inputScanner.nextInt();

                Graph graph = new UndirectedGraph();

                //load nodes
                for(int i = 1; i <= numOfNodes; i++) {
                    graph.addNode(i);
                }

                for(int i = 0; i < numOfEdges; i++) {
                    int from = inputScanner.nextInt();
                    int to = inputScanner.nextInt();
                    float weight = inputScanner.nextInt();
                    graph.addEdge(new Edge(from, to, weight));
                }
                
                inputScanner.nextInt();


                int expectedNumOfNodes = outputScanner.nextInt();
                int expectedNumOfEdges = outputScanner.nextInt();

                assertEquals(graph.N(), expectedNumOfNodes);
                assertEquals(graph.E(), expectedNumOfEdges);

                System.out.println("Test " + numOfTests + ": Successful!" + "   " +
                        graph.N() + " Nodes && " + graph.E() + " Edges");

                numOfTests--;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
