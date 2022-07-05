package com.github.podoprigoraivan.swingapp.graph;

import java.util.ArrayList;

public class Graph {
    protected ArrayList<ArrayList<Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new ArrayList<>();
    }

    public void AddVertex() {
        adjacencyList.add(new ArrayList<>());
    }

    public int VertexAmount() {
        return adjacencyList.size();
    }

    public ArrayList<Integer> GetVertexAdjacencyList(int num) {
        return adjacencyList.get(num);
    }

    public void AddEdge(int v1, int v2){
        if (IsEdgeInGraph(v1, v2)) return;
        adjacencyList.get(v1).add(v2);
        adjacencyList.get(v2).add(v1);
    }

    public boolean IsEdgeInGraph(int v1, int v2) {
        if (v1 == v2 || v1 >= this.VertexAmount() || v2 >= this.VertexAmount()) {
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
