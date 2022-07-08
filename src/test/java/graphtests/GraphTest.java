package graphtests;
import com.github.podoprigoraivan.swingapp.graph.Graph;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class GraphTest {
    Graph graph = new Graph();

    @Test
    public void addVertex() {
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        int actual = graph.vertexAmount();
        int expected = 5;
        assertEquals(actual, expected);
    }

    @Test
    public void vertexAmount() {
        graph.addVertex();
        graph.addVertex();
        int actual = graph.vertexAmount();
        int expected = 2;
        assertEquals(actual, expected);
    }

    @Test
    public void getVertexAdjacencyList() {
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(2, 0);
        graph.addEdge(2, 1);
        ArrayList<Integer> actual = graph.getVertexAdjacencyList(2);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        assertEquals(actual, expected);
    }

    @Test
    public void addEdge() {
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(1, 2);
        boolean actual = graph.isEdgeInGraph(1, 2);
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void isEdgeInGraph() {
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(0, 2);
        boolean actual = graph.isEdgeInGraph(0, 2);
        boolean expected = true;
        assertEquals(actual, expected);
    }
}
