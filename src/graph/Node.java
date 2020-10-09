package graph;

public class Node {

    public String value;

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "(" + this.value + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Node && this.value.equals(((Node) other).getValue());
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
