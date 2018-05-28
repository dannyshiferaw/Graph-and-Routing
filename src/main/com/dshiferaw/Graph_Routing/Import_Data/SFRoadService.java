package com.dshiferaw.Graph_Routing.Import_Data;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** Collected sample nodes and edges from the
 * San Francisco Raod Network Data set
 */
public class SFRoadService {


    private static RoadService sf = new Retrofit.Builder()
            .baseUrl("https://gist.github.com/BenjaminMalley/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(RoadService.class);

    /**
     * Loads a sample nodes and edges from SF road network data set
     * @return Map Object
     * Usage:
     *         Map<String, Object> map = SFRoadService.load()
     *         LinkedList<Node> nodes = (List<Node>) map.get("Nodes")
     *         LinkedList<Edge> edges = (List<Edge>) map.get("Edges")

     */
    public static Map<String, Object> load() {
        List<DNode> nodes = new LinkedList<>();
        List<DEdge> edges = new LinkedList<>();
        Map<String, Object> complete_data = new HashMap<>();
        try {
            Response<Map<String, DEdge>> loadEdges = sf.getEdges().execute();
            if (loadEdges.isSuccessful()) {
                edges.addAll(loadEdges.body().values());
            }
            Response<Map<String, DNode>> loadedNodes = sf.getNodes().execute();
            if (loadedNodes.isSuccessful()) {
                nodes.addAll((loadedNodes.body().values()));
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        complete_data.put("Nodes", nodes);
        complete_data.put("Edges", edges);
        return complete_data;

    }


}
