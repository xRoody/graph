package my.ex.graph;

import java.util.*;

public class Vertex {
    private List<Edge> edges=new ArrayList<>();

    public boolean isConnected(Vertex vertex){
        return edges.contains(vertex);
    }

    public Edge getEdge(Vertex vertex){
        if (!isConnected(vertex)) throw new RuntimeException();
        return edges.stream().filter(x->x.getVertex().equals(vertex)).findFirst().get();
    }

    public int edgeCount(){
        return edges.size();
    }

    public void addEdge(Vertex vertex, double width){
        Edge e=new Edge(vertex, width);
        edges.add(e);
    }

    public void destroy(){
        for (Edge e:edges){
            e.getVertex().edges.remove(new Edge(this, e.getWidth()));
        }
    }

    public void deleteEdge(Vertex vertex) {
        for (int i=0; i<edges.size(); i++){
            if (edges.get(i).getVertex().equals(vertex)) edges.remove(i);
        }
    }
}
