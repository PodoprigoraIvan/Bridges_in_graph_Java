package random;

import graphcomponents.*;
import java.util.ArrayList;

public class RandomGraphCreator {

    public static boolean search(Vertex vertex1, ArrayList<Edge> edges_list, Vertex vertex2, int edges_list_size) {
        for (int i = 0; i < edges_list_size; i++){
            if (((edges_list.get(i).first == vertex1)&&(edges_list.get(i).second == vertex2))||
                    ((edges_list.get(i).second == vertex1)&&(edges_list.get(i).first == vertex2))){
                return true;
            }
        }
        return false;
    }

    public static void CreateRandomGraph(ArrayList<Vertex> vertex_list, ArrayList<Edge> edges_list, double maxV, double maxE, int maxY, int maxX) {

        int V = (int) (Math.random()*maxV + 1);

        int E = (int) (Math.random() * maxE);
        while (!(E <= V * (V - 1) / 2)) {
            E = (int) (Math.random() * maxE);
        }

        for (int i = 1; i <= V; i++){
            vertex_list.add(new Vertex((int) (Math.random()*maxX), (int) (Math.random()*maxY), i));
        }

        for (int i = 0; i < E; i++) {
            Vertex vertex1 = vertex_list.get((int)(Math.random()*V));
            Vertex vertex2 = vertex_list.get((int)(Math.random()*V));
            Edge edge1 = new Edge(vertex1, vertex2);
            if ((vertex1.num != vertex2.num) && !search(vertex1, edges_list, vertex2, edges_list.size())){
                edges_list.add(edge1);
            }
            else {
                i--;
            }
        }

    }
}