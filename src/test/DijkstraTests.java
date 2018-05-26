import com.dshiferaw.Graph_Routing.Graph.Edge;
import com.dshiferaw.Graph_Routing.Graph.Graph;
import com.dshiferaw.Graph_Routing.Graph.UndirectedGraph;
import com.dshiferaw.Graph_Routing.Graph_Processing.BFS;
import com.dshiferaw.Graph_Routing.Graph_Processing.Dijkstra;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class DijkstraTests {

    @Test
    public void testDijkstra() {
        try {
            String[][] tests = new String[3][3];
            tests[0][0] = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/inputs/DijkstraTestsInput01.txt";
            tests[0][1] = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/outputs/DijkstraTestsOutput01.txt";
            tests[1][0] = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/inputs/DijkstraTestsInput02.txt";
            tests[1][1] = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/outputs/DijkstraTestsOutput02.txt";
            tests[2][0] = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/inputs/DijkstraTestsInput03.txt";
            tests[2][1] = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/outputs/DijkstraTestsOutput03.txt";


            for (int k = 0; k < tests.length; k++) {

                FileReader inputFileReader = new FileReader(tests[k][0]);
                BufferedReader inputBufferReader = new BufferedReader(inputFileReader);
                Scanner inputScanner = new Scanner(inputBufferReader);

                FileReader outputFileReader = new FileReader(tests[k][1]);
                BufferedReader outputBufferReader = new BufferedReader(outputFileReader);
                Scanner outputScanner = new Scanner(outputBufferReader);

                int numOfTests = inputScanner.nextInt();
                while (numOfTests > 0) {
                    int numOfNodes = inputScanner.nextInt();
                    int numOfEdges = inputScanner.nextInt();

                    Graph graph = new UndirectedGraph(numOfNodes);
                    for (int i = 0; i < numOfEdges; i++) {
                        int from = inputScanner.nextInt();
                        int to = inputScanner.nextInt();
                        int weight = inputScanner.nextInt();
                        graph.addEdge(new Edge(from, to, weight));
                    }
                    int source = inputScanner.nextInt();

                    Dijkstra djs = new Dijkstra(graph, source);
                    djs.dijkstra();

                    ArrayList<Integer> answer = new ArrayList<>();
                    for (int i = 1; i <= graph.N(); i++) {
                        if (i != source) {
                            answer.add((int) djs.getDistTo(i));
                        }
                    }
                    ArrayList<Integer> solution = new ArrayList<>();
                    String soln = outputScanner.nextLine();
                    Scanner lineScanner = new Scanner(soln);
                    while (lineScanner.hasNext()) {
                        solution.add(lineScanner.nextInt());
                    }


                    assertEquals(answer, solution);

                    System.out.println("Test " + numOfTests + ": Successful!");

                    numOfTests--;
                }

                System.out.println();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
