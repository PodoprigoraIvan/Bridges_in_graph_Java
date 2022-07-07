package com.github.podoprigoraivan.swingapp.SaveAndLoad;

import com.github.podoprigoraivan.swingapp.graph.DrawableGraph;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Saver {


    protected ArrayList<ArrayList<Integer>> allList = new ArrayList<ArrayList<Integer>>();

    private File file;
    private DrawableGraph graph;
    private ArrayList<Integer> listEdge;
    private ArrayList<Integer> listCoor = new ArrayList<>();

    public Saver(DrawableGraph graph, File file){
        this.graph = graph;
        this.file = file;

    }

    public void save()
    {
        JSONObject json = new JSONObject();

        try {
        for (int i = 0; i < graph.vertexAmount(); i++){
            listEdge = graph.getVertexAdjacencyList(i);
            listCoor.add(graph.getX(i));
            listCoor.add(graph.getY(i));
            allList.add(listEdge);
            allList.add(listCoor);
            json.put(String.valueOf(i), allList);
            listCoor.clear();
            allList.clear();
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
