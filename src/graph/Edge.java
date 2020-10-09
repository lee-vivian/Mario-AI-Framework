package graph;

public class Edge {
    public Node src;
    public Node dest;

    public Edge(Node src, Node dest) {
        this.src = src;
        this.dest = dest;
    }

    public Node getSrc() {
        return this.src;
    }

    public Node getDest() {
        return this.dest;
    }

    @Override
    public String toString() {
        return this.src.toString() + " => " + this.dest.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Edge &&
                this.src.equals(((Edge) other).getSrc())  &&
                this.dest.equals(((Edge) other).getDest());
    }

    @Override
    public int hashCode() {
        String[] components = {this.src.getValue(), this.dest.getValue()};
        String edgeAsString = String.join(",", components);
        return edgeAsString.hashCode();
    }

}
