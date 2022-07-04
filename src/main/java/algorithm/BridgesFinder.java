package algorithm;

import graph.*;
import java.util.ArrayList;

public class BridgesFinder {
    public static int maxN = 1000;
    public static boolean[] used = new boolean[maxN];
    public static int[] tin = new int[maxN];
    public static int[] fup = new int[maxN];
    public static int timer = 0;

    public static ArrayList<ArrayList<Integer>> converter(ArrayList<Vertex> vertex_list, ArrayList<Edge> edges_list) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(vertex_list.size()+1);
        for(int i = 0; i < vertex_list.size()+1; i++) {
            graph.add(new ArrayList());
        }
        for(Edge edge: edges_list){
            graph.get(edge.first.num).add(edge.second.num);
            graph.get(edge.second.num).add(edge.first.num);
        }
        return graph;
    }

    public static void dfs(int v, int p, ArrayList<ArrayList<Integer>> graph, ArrayList<Vertex> vertex_list, ArrayList<Edge> bridges_list) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        for (int i = 0; i < graph.get(v).size(); ++i) {
            Integer to = graph.get(v).get(i);
            if (to == p)  continue;
            if (used[to] == true)
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs(to, v, graph, vertex_list, bridges_list);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]){
                    for (Vertex v_start : vertex_list){
                        for (Vertex v_end: vertex_list){
                            if (v_start.num == v && v_end.num == to){
                                bridges_list.add(new Edge(v_start, v_end));
                            }
                        }
                    }
                }
            }
        }
    }

    public static ArrayList<Edge> findBridges(ArrayList<Vertex> vertex_list, ArrayList<Edge> edges_list){
        for (int i = 0; i < used.length; i++){
            used[i] = false;
        }
		ArrayList<ArrayList<Integer>> graph = converter(vertex_list, edges_list);
        ArrayList<Edge> bridges_list = new ArrayList<>();
        for (int i = 0; i < graph.size(); ++i)
            if (used[i] == false)
                dfs(i, -1, graph, vertex_list, bridges_list);
        return bridges_list;
    }
}
