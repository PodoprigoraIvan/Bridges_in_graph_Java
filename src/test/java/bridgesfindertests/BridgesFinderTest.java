package bridgesfindertests;

import static org.junit.Assert.assertEquals;
import com.github.podoprigoraivan.swingapp.graph.*;
import com.github.podoprigoraivan.swingapp.algorithm.*;
import org.junit.Test;
import java.util.ArrayList;

public class BridgesFinderTest {
    Graph graph = new Graph();

    @Test
    public void find1Bridges() {
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(0, 1);
        BridgesFinder bridgesFinder = new BridgesFinder(graph);
        ArrayList<int[]> actual = bridgesFinder.findBridges();
        ArrayList<int[]> expected = new ArrayList<>();
        expected.add(actual.get(0));
        assertEquals(actual.size(), expected.size());
        for(int i = 0; i < actual.size(); i++){
            assertEquals(actual.get(i), expected.get(i));
        }
    }

    @Test
    public void find2Bridges() {
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        BridgesFinder bridgesFinder = new BridgesFinder(graph);
        ArrayList<int[]> actual = bridgesFinder.findBridges();
        ArrayList<int[]> expected = new ArrayList<>();
        expected.add(actual.get(0));
        expected.add(actual.get(1));
        assertEquals(actual.size(), expected.size());
        for(int i = 0; i < actual.size(); i++){
            assertEquals(actual.get(i), expected.get(i));
        }
    }
}
