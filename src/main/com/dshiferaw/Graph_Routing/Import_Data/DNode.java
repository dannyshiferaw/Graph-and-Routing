package com.dshiferaw.Graph_Routing.Import_Data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DNode {

    @JsonProperty("NodeID")
    private int nodeId;
    @JsonProperty("Latitude")
    private float lat;
    @JsonProperty("Longitude")
    private float lon;

    public int getNodeId() {
        return nodeId;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }
}
