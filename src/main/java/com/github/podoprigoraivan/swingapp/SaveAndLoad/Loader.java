package com.github.podoprigoraivan.swingapp.SaveAndLoad;


import com.github.podoprigoraivan.swingapp.graph.DrawableGraph;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;


public class Loader {
    private DrawableGraph drawableGraph;
    private File file;

    public Loader(DrawableGraph Graph, File file){
        Graph.clear();
        this.drawableGraph = Graph;
        this.file = file;
    }


    public void Load() throws Exception {
        try (FileReader reader = new FileReader(file)) {
            int fileLen = (int) file.length();
            char[] chars = new char[fileLen];
            reader.read(chars);
            String s = String.valueOf(chars);
            JSONObject jsonObject = new JSONObject(s);
            for (int i = 0; i < jsonObject.length(); i++){
                JSONArray jsonArray = jsonObject.getJSONArray(String.valueOf(i));
                drawableGraph.addVertex(jsonArray.getJSONArray(1).getInt(0) , jsonArray.getJSONArray(1).getInt(1));
            }
            for (int i = 0; i < jsonObject.length(); i++){
                JSONArray jsonArray = jsonObject.getJSONArray(String.valueOf(i));
                for (int j = 0; j < jsonArray.getJSONArray(0).length(); j++) {
                    drawableGraph.addEdge(i, jsonArray.getJSONArray(0).getInt(j));
                }
            }
        }
    }
}