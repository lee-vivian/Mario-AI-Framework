package graph;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class DiGraph {

    private Set<Node> nodes;
    private Map<Edge,List<Integer>> edges;  // { (src, dest) : [action1, action2, ..., weight] } weight = euclid dist

    public DiGraph() {
        this.nodes = new HashSet<>();
        this.edges = new HashMap<>();
    }

    public void addNode(String value) {
        this.nodes.add(new Node(value));
    }

    public void addEdge(String src, String dest, int actionId) {
        Node srcNode = new Node(src);
        Node destNode = new Node(dest);
        this.nodes.add(srcNode);
        this.nodes.add(destNode);
        Edge edge = new Edge(srcNode, destNode);

        List<Integer> actionsAndWeight;
        if (!this.edges.containsKey(edge)) {
            // Calculate Euclidean distance between Mario states for the weight
            String[] srcComponents = src.split(",");
            String[] destComponents = dest.split(",");
            float x1 = Float.parseFloat(srcComponents[0]);
            float y1 = Float.parseFloat(srcComponents[1]);
            float x2 = Float.parseFloat(destComponents[0]);
            float y2 = Float.parseFloat(destComponents[1]);
            int weight = (int) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
            actionsAndWeight = new ArrayList<>();
            actionsAndWeight.add(0, weight);

        } else {
            actionsAndWeight = this.edges.get(edge);
        }

        // Add the given input action to the edge
        actionsAndWeight.add(0, actionId);
        this.edges.put(edge, actionsAndWeight);
    }

    public void write(String filepath) {
        try {
            FileWriter writer = new FileWriter(filepath, true);
            for (Map.Entry<Edge, List<Integer>> entry : this.edges.entrySet()) {
                Edge edge = entry.getKey();
                List<Integer> actionsAndWeights = entry.getValue();
                List<Integer> actions = actionsAndWeights.subList(0, actionsAndWeights.size()-1);
                Integer weight = actionsAndWeights.get(actionsAndWeights.size()-1);
                writer.write(edge.toString() + ", actions: " + actions.toString() + ", weight: " + weight.toString() + "\n");
            }
            writer.close();
            System.out.println("File written to: " + filepath + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
