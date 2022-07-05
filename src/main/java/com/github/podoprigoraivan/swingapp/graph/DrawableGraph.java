package com.github.podoprigoraivan.swingapp.graph;

import java.util.ArrayList;

public class DrawableGraph extends Graph {
    private ArrayList<Integer> coordinatesX;
    private ArrayList<Integer> coordinatesY;

    public DrawableGraph() {
        coordinatesX = new ArrayList<>();
        coordinatesY = new ArrayList<>();
    }

    public void AddVertex(int x, int y){
        super.AddVertex();
        coordinatesX.add(x);
        coordinatesY.add(y);
    }

    public int GetX(int vertex_num){
        return coordinatesX.get(vertex_num);
    }

    public int GetY(int vertex_num) {
        return coordinatesY.get(vertex_num);
    }

    public void clear() {
        coordinatesX.clear();
        coordinatesY.clear();
    }
}
