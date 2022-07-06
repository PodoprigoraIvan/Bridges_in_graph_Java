package com.github.podoprigoraivan.swingapp.graph;

import java.util.ArrayList;

public class Graph {
    protected ArrayList<ArrayList<Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new ArrayList<>();
    }

    public void addVertex() {
        adjacencyList.add(new ArrayList<>());
    }

    public int vertexAmount() {
        return adjacencyList.size();
    }

    public ArrayList<Integer> getVertexAdjacencyList(int num) {
        return adjacencyList.get(num);
    }

    public void addEdge(int v1, int v2){
        if (isEdgeInGraph(v1, v2) || v1 < 0 || v2 < 0 || v1 >= this.vertexAmount() || v2 >= this.vertexAmount()) return;
        adjacencyList.get(v1).add(v2);
        adjacencyList.get(v2).add(v1);
    }

    public boolean isEdgeInGraph(int v1, int v2) {
        if (v1 == v2 || v1 >= this.vertexAmount() || v2 >= this.vertexAmount() || v1 < 0 || v2 < 0) {
            return false;
        }
        for (int i = 0; i < adjacencyList.get(v1).size(); i++){
            if (adjacencyList.get(v1).get(i).equals(v2)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        adjacencyList.clear();
    }
}

