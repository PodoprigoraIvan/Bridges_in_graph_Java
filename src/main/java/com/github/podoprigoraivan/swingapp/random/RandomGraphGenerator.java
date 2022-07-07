package com.github.podoprigoraivan.swingapp.random;

import com.github.podoprigoraivan.swingapp.graph.*;

public class RandomGraphGenerator {
    private DrawableGraph graph;
    private double maxVerticesAmount;
    private double maxEdgesAmount;
    private int maxY;
    private int maxX;

    public RandomGraphGenerator(DrawableGraph Graph, double maxVerticesAmount, double maxEdgesAmount, int maxY, int maxX){
        this.graph = Graph;
        this.maxVerticesAmount = maxVerticesAmount;
        this.maxEdgesAmount = maxEdgesAmount;
        this.maxY = maxY;
        this.maxX = maxX;
    }

    public void generate() {
        graph.clear();
        int V = (int) (Math.random() * maxVerticesAmount + 1);
        int E = (int) (Math.random() * maxEdgesAmount);
        while (!(E <= V * (V - 1) / 2)) {
            E = (int) (Math.random() * maxEdgesAmount);
        }
        for (int i = 0; i < V; i++){
            graph.addVertex((int) (Math.random()*maxX), (int) (Math.random()*maxY));
        }
        for (int i = 0; i < E; i++) {
            int indexVertex1 = (int)(Math.random() * V);
            int indexVertex2 = (int)(Math.random() * V);

            if ((indexVertex1 != indexVertex2) && !graph.isEdgeInGraph(indexVertex1, indexVertex2)){
                graph.addEdge(indexVertex1, indexVertex2);
            } else {
                i--;
            }
        }
    }
}