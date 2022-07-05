package com.github.podoprigoraivan.swingapp.random;

import com.github.podoprigoraivan.swingapp.graph.*;
import java.util.ArrayList;

public class RandomGraphGenerator {
    private DrawableGraph Graph;
    private double maxV;
    private double maxE;
    private int maxY;
    private int maxX;



    public RandomGraphGenerator(DrawableGraph Graph, double maxV, double maxE, int maxY, int maxX){
        this.Graph = Graph;
        this.maxV = maxV;
        this.maxE = maxE;
        this.maxY = maxY;
        this.maxX = maxX;
    }

    public void Generate() {

        int V = (int) (Math.random()*maxV + 1);

        int E = (int) (Math.random() * maxE);
        while (!(E <= V * (V - 1) / 2)) {
            E = (int) (Math.random() * maxE);
        }

        for (int i = 1; i <= V; i++){
            Graph.AddVertex((int) (Math.random()*maxX), (int) (Math.random()*maxY));
        }

        for (int i = 0; i < E; i++) {
            int indexVertex1 = (int)(Math.random()*V);
            int indexVertex2 = (int)(Math.random()*V);

            if ((indexVertex1 != indexVertex2) && !Graph.IsEdgeInGraph(indexVertex1, indexVertex2)){
                Graph.AddEdge(indexVertex1, indexVertex2);
            } else {
                i--;
            }
        }

    }
}