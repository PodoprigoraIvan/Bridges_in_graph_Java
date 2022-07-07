package com.github.podoprigoraivan.swingapp.SaveAndLoad;

import com.github.podoprigoraivan.swingapp.graph.DrawableGraph;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Saver {


    protected ArrayList<ArrayList<Integer>> AllList = new ArrayList<ArrayList<Integer>>();

    private File file;
    private DrawableGraph Graph;
    private ArrayList<Integer> ListEdge;
    private ArrayList<Integer> ListCoor = new ArrayList<>();

    public Saver(DrawableGraph Graph, File file){
        this.Graph = Graph;
        this.file = file;

    }

    public void Save()
    {
        JSONObject json = new JSONObject();

        try {
        for (int i = 0; i < Graph.vertexAmount(); i++){
            ListEdge = Graph.getVertexAdjacencyList(i);
            ListCoor.add(Graph.getX(i));
            ListCoor.add(Graph.getY(i));
            AllList.add(ListEdge);
            AllList.add(ListCoor);
            json.put(String.valueOf(i), AllList);
            ListCoor.clear();
            AllList.clear();
        }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(file)) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
