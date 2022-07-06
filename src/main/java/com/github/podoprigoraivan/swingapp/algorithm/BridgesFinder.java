package com.github.podoprigoraivan.swingapp.algorithm;

import com.github.podoprigoraivan.swingapp.graph.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BridgesFinder {
    private static boolean[] used;
    private static int[] tin;
    private static int[] fup;
    private static int timer;
    private Graph graph;

    public BridgesFinder(Graph graph) {
        used = new boolean[graph.vertexAmount()];
        tin = new int[graph.vertexAmount()];
        fup = new int[graph.vertexAmount()];
        this.graph = graph;
        timer = 0;
    }

    private static void dfs(int v, int p, Graph graph, ArrayList<int[]> bridges_list) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        for (int i = 0; i < graph.getVertexAdjacencyList(v).size(); ++i) {
            Integer to = graph.getVertexAdjacencyList(v).get(i);
            if (to == p)  continue;
            if (used[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs(to, v, graph, bridges_list);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    bridges_list.add(new int[]{v, to});
                }
            }
        }
    }

    public ArrayList<int[]> findBridges(){
        Arrays.fill(used, false);
        ArrayList<int[]> bridges_list = new ArrayList<>();
        for (int i = 0; i < graph.vertexAmount(); ++i) {
            if (!used[i]) {
                dfs(i, -1, graph, bridges_list);
            }
        }
        return bridges_list;
    }
}
