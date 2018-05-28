package com.dshiferaw.Graph_Routing.Import_Data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DEdge {
    @JsonProperty("EdgeId")
    private int edgeId ;
    @JsonProperty("StartNodeId")
    private int startNodeId;
    @JsonProperty("EndNodeId")
    private int endNodeId;
    @JsonProperty("L2Distance")
    private float dist;

    public int getEdgeId() {
        return edgeId;
    }

    public int getStartNodeId() {
        return startNodeId;
    }

    public int getEndNodeId() {
        return endNodeId;
    }

    public float getDist() {
        return dist;
    }

    @Override
    public String toString() {
        return edgeId + " ";
    }
}
