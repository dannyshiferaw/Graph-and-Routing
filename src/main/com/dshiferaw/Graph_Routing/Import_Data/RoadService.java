package com.dshiferaw.Graph_Routing.Import_Data;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.Map;

public interface RoadService {
    @GET("https://gist.githubusercontent.com/BenjaminMalley/9eadf45dbe11ba9c3ac34c45f905cfe8/raw/2c363711b601fa39a5d0071f10158b86217e530f/nodes.json")
    Call<Map<String, DNode>> getNodes();

    @GET("https://gist.github.com/BenjaminMalley/9eadf45dbe11ba9c3ac34c45f905cfe8/raw/2c363711b601fa39a5d0071f10158b86217e530f/edges.json")
    Call<Map<String, DEdge>> getEdges();
}
