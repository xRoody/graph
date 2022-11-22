package my.ex.graph;

import java.util.Objects;

public class Edge {
    private Vertex vertex;
    private double width;

    public Edge(Vertex vertex, double width) {
        this.vertex = vertex;
        this.width = width;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.width, width) == 0 && Objects.equals(vertex, edge.vertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, width);
    }
}
