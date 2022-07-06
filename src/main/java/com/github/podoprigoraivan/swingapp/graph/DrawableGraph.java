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

    public void addVertex(){}

    public void addVertex(int x, int y){
        super.addVertex();
        coordinatesX.add(x);
        coordinatesY.add(y);
    }

    public int getX(int vertexNum){
        return coordinatesX.get(vertexNum);
    }

    public int getY(int vertexNum) {
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
