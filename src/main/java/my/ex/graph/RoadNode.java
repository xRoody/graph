package my.ex.graph;

public class RoadNode {
    private double weight;
    private RoadNode prev;
    private Vertex vertex;

    public RoadNode(Vertex vertex) {
        this(0.0, null, vertex);
    }

    public RoadNode(double weight, RoadNode prev, Vertex vertex) {
        this.weight = weight;
        this.prev = prev;
        this.vertex = vertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public RoadNode getPrev() {
        return prev;
    }

    public void setPrev(RoadNode prev) {
        this.prev = prev;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }
}
