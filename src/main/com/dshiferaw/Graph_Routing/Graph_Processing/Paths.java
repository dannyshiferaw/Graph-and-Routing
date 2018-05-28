package com.dshiferaw.Graph_Routing.Graph_Processing;


import java.util.List;

public interface Paths {

    /** Returns true if there is a path to a node */
    boolean hasPathTo(int n);

    /** Returns a path to a node */
    List<Integer> getPathTo(int n);

    /** Returns a distance or cost it takes to get to a node */
    float getDistTo(int n);
}
