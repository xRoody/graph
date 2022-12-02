package my.ex.graph;

import java.util.*;

public class Vertex {
    private Integer id;
    private Map<Vertex,Edge> edges=new HashMap<>();

    public Vertex(Integer id) {
        this.id = id;
    }

    public Vertex() {
    }

    public boolean isConnected(Vertex vertex){
        return edges.containsKey(vertex);
    }

    public Optional<Edge> getEdge(Vertex vertex){
        return Optional.of(edges.get(vertex));
    }

    public int edgeCount(){
        return edges.size();
    }

    public void addEdge(Vertex vertex, double width){
        if (!edges.containsKey(vertex)) {
            edges.put(vertex, new Edge(vertex, width));
            vertex.edges.put(this, new Edge(this, width));
        }
    }

    public void destroy(){
        for (Vertex v:edges.keySet()){
            v.edges.remove(this);
        }
    }

    public void deleteEdge(Vertex vertex) {
        edges.remove(vertex);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges.values());
    }


    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder(id+"{\n");
        for (Vertex v:edges.keySet()){
            builder.append("\t").append(v.id).append("(").append(edges.get(v).getWidth()).append("),\n");
        }
        builder.append("\n}");
        return builder.toString();
    }
}
