package com.github.podoprigoraivan.swingapp.graph;

import java.util.ArrayList;

public class DrawableGraph extends Graph {
    private ArrayList<Integer> coordinatesX;
    private ArrayList<Integer> coordinatesY;

    public ArrayList<int[]> bridgesList;

    public DrawableGraph() {
        coordinatesX = new ArrayList<>();
        coordinatesY = new ArrayList<>();
    }

    public void AddVertex(){}

    public void AddVertex(int x, int y){
        super.AddVertex();
        coordinatesX.add(x);
        coordinatesY.add(y);
    }

    public int GetX(int vertexNum){
        return coordinatesX.get(vertexNum);
    }

    public int GetY(int vertexNum) {
        return coordinatesY.get(vertexNum);
    }

    public void changeX(int vertexNum, int newX) {
        coordinatesX.set(vertexNum, newX);
    }

    public void changeY(int vertexNum, int newY) {
        coordinatesY.set(vertexNum, newY);
    }

    public void clear() {
        super.clear();
        coordinatesX.clear();
        coordinatesY.clear();
        if (bridgesList != null) {
            bridgesList.clear();
        }
    }
}
