package algorithm;

import graph.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BridgesFinder {
    private static boolean[] used;
    private static int[] tin;
    private static int[] fup;
    private static int timer;

    public BridgesFinder(Graph graph) {
        used = new boolean[graph.VertexAmount()];
        tin = new int[graph.VertexAmount()];
        fup = new int[graph.VertexAmount()];
        timer = 0;
    }

    public static void Dfs(int v, int p, Graph graph, ArrayList<int[]> bridges_list) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        for (int i = 0; i < graph.GetVertexAdjacencyList(v).size(); ++i) {
            Integer to = graph.GetVertexAdjacencyList(v).get(i);
            if (to == p)  continue;
            if (used[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                Dfs(to, v, graph, bridges_list);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]){
                    bridges_list.add(new int[]{v, to});
                }
            }
        }
    }

    public ArrayList<int[]> FindBridges(Graph graph){
        Arrays.fill(used, false);
        ArrayList<int[]> bridges_list = new ArrayList<>();
        for (int i = 0; i < graph.VertexAmount(); ++i){
            if (!used[i]){
                Dfs(i, -1, graph, bridges_list);
            }
        }
        return bridges_list;
    }
}
