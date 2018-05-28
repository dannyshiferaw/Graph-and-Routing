import com.dshiferaw.Graph_Routing.Graph.Edge;
import com.dshiferaw.Graph_Routing.Graph.Graph;
import com.dshiferaw.Graph_Routing.Graph.UndirectedGraph;
import com.dshiferaw.Graph_Routing.Graph_Processing.BFS;
import com.dshiferaw.Graph_Routing.Graph_Processing.Dijkstra;
import org.junit.Test;
import scala.Int;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class DijkstraTests {

    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        HashMap<String, Integer> parse;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }


        public FastReader(String input) {
            try {
                br = new BufferedReader(new FileReader(input));
                parse = new HashMap<>();
                for(int i = 0; i < 2600; i ++) {
                    parse.put(Integer.toString(i), i);
                }
            }
            catch (Exception e) {

            }

        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            String n = next();
            if (parse.containsKey(n)) return parse.get(n);
            return Integer.parseInt(n);
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }

    @Test
    public void test1() {
        String input = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/inputs/DijkstraTestsInput01.txt";
        String output = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/outputs/DijkstraTestsOutput01.txt";

        Map<String, List<Integer>> result = testDijkstra(input, output);
        assertEquals(result.get("solution"), result.get("answer"));
        System.out.println("Test 1: Successful!");
    }

    @Test
    public void test2() {
        String input = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/inputs/DijkstraTestsInput02.txt";
        String output = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/outputs/DijkstraTestsOutput02.txt";

        Map<String, List<Integer>> result = testDijkstra(input, output);
        assertEquals(result.get("solution"), result.get("answer"));
        System.out.println("Test 2: Successful!");
    }

    @Test
    public void test3() {
        String input = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/inputs/DijkstraTestsInput03.txt";
        String output = "/Users/daniel.shiferaw/Documents/Projects/Uber/Graph/src/test/outputs/DijkstraTestsOutput03.txt";

        Map<String, List<Integer>> result = testDijkstra(input, output);
        assertEquals(result.get("solution"), result.get("answer"));
        System.out.println("Test 3: Successful!");
    }


    private Map<String, List<Integer>> testDijkstra(String input, String output) {
        Map<String, List<Integer>> result = new HashMap<>();
        List<Integer> answer = new LinkedList<>();
        List<Integer> solution = new LinkedList<>();
        try {
            FileReader inputFileReader = new FileReader(input);
            //BufferedReader inputBufferReader = new BufferedReader(inputFileReader);
            FastReader inputBufferReader = new FastReader(input);


            FileReader outputFileReader = new FileReader(output);
            BufferedReader outputBufferReader = new BufferedReader(outputFileReader);
            Scanner outputScanner = new Scanner(outputBufferReader);



            int numOfTests = inputBufferReader.nextInt();
            while (numOfTests > 0) {
                int numOfNodes = inputBufferReader.nextInt();
                int numOfEdges = inputBufferReader.nextInt();

                int[][] safe = new int[numOfNodes][numOfNodes];


                Graph graph = new UndirectedGraph();
                for (int i = 1; i <= numOfNodes; i++) {
                    graph.addNode(i);
                }
                for (int i = 0; i < numOfEdges; i++) {
                    int from = inputBufferReader.nextInt();
                    int to = inputBufferReader.nextInt();
                    int weight = inputBufferReader.nextInt();
                    if (!(safe[from][to] != 0 && (safe[from][to] < weight || safe[to][from] < weight))) {
                        Edge edge = new Edge(from, to, weight);
                        graph.addEdge(edge);
                        safe[from][to] = weight;
                        safe[to][from] = weight;
                    }
                }

                int source = inputBufferReader.nextInt();

                Dijkstra djs = new Dijkstra(graph, source);

                djs.dijkstra();

                answer = new ArrayList<>();
                for(Integer n: graph.nodes()) {
                    if (n != source) {
                        answer.add((int) djs.getDistTo(n));
                    }
                }

                solution = new ArrayList<>();
                String soln = outputScanner.nextLine();
                Scanner lineScanner = new Scanner(soln);
                while (lineScanner.hasNext()) {
                    solution.add(lineScanner.nextInt());
                }

                result.put("solution", solution);
                result.put("answer", answer);

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
