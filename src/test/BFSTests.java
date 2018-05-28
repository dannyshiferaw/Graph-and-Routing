import com.dshiferaw.Graph_Routing.Graph.Edge;
import com.dshiferaw.Graph_Routing.Graph.Graph;
import com.dshiferaw.Graph_Routing.Graph.UndirectedGraph;
import com.dshiferaw.Graph_Routing.Graph_Processing.BFS;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Test Breadth First Search Implementation
 */


public class BFSTests {

    @Test
    public void testBFS() {
        try {
            FileReader inputFileReader = new FileReader("/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/inputs/BFSTestsInput01.txt");
            BufferedReader inputBufferReader = new BufferedReader(inputFileReader);
            Scanner inputScanner = new Scanner(inputBufferReader);

            FileReader outputFileReader = new FileReader("/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/outputs/BFSTestsOutput01.txt");
            BufferedReader outputBufferReader = new BufferedReader(outputFileReader);
            Scanner outputScanner = new Scanner(outputBufferReader);

            final int constant_weight = 6;
            int numOfTests = inputScanner.nextInt();
            while (numOfTests > 0) {
                int numOfNodes = inputScanner.nextInt();
                int numOfEdges = inputScanner.nextInt();

                Graph graph = new UndirectedGraph();
                //load nodes
                for(int i = 1; i <= numOfNodes; i++) {
                    graph.addNode(i);
                }
                //load edges
                for(int i = 0; i < numOfEdges; i++) {
                    int from = inputScanner.nextInt();
                    int to = inputScanner.nextInt();
                    graph.addEdge(new Edge(from, to, constant_weight));
                }
                int source = inputScanner.nextInt();

                BFS bfs = new BFS(graph, source);
                bfs.bfs();

                ArrayList<Integer> answer = new ArrayList<>();
                for(Integer n: graph.nodes()) {
                    if(n != source) {
                        answer.add((int)bfs.getDistTo(n));
                    }
                }

                ArrayList<Integer> solution = new ArrayList<>();
                while(outputScanner.hasNext()) {
                    solution.add(outputScanner.nextInt());
                }

                assertEquals(answer, solution);

                numOfTests--;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
