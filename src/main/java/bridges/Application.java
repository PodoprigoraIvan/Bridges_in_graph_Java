package bridges;
import graphcomponents.*;
import java.util.ArrayList;

public class Application {
    public static int maxN = 100;
    public static boolean[] used = new boolean[maxN];
    public static int[] tin = new int[maxN];
    public static int[] fup = new int[maxN];
    public static int timer = 0;

    public static ArrayList<ArrayList<Integer>> converter(ArrayList<Vertex> vertex_list, ArrayList<Edge> edges_list) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(vertex_list.size());
        for(int i = 0; i < vertex_list.size(); i++) {
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

    public static ArrayList<Edge> findBridges(ArrayList<Vertex> vertex_list, ArrayList<Edge> edges_list){ArrayList<ArrayList<Integer>> graph = converter(vertex_list, edges_list);
        ArrayList<Edge> bridges_list = new ArrayList<>();
        for (int i = 0; i < graph.size(); ++i)
            if (used[i] == false)
                dfs(i, -1, graph, vertex_list, bridges_list);
        return bridges_list;
    }

    public static void main( String[] args ) {
        ArrayList<Vertex> vertex_list = new ArrayList<>();
        ArrayList<Edge> edges_list = new ArrayList<>();
        Vertex v0 = new Vertex(100, 100, 0);
        Vertex v1 = new Vertex(100, 50, 1);
        Vertex v2 = new Vertex(50, 100, 2);
        vertex_list.add(v0);
        vertex_list.add(v1);
        vertex_list.add(v2);
        edges_list.add(new Edge(v0, v1));
        edges_list.add(new Edge(v1, v2));


        ArrayList<Edge> bridges_list = findBridges(vertex_list, edges_list);


        for (Edge e : bridges_list){
            System.out.printf("%d %d   ", e.first.num, e.second.num);
        }
    }
}
