package my.ex.graph;

import my.ex.parser.BasicGraphParser;

import java.util.HashMap;
import java.util.Map;

public class MathGraph {
    private Map<Integer, Vertex> vertexMap=new HashMap();
    private Integer counter=1;
    private static BasicGraphParser basicGraphParser=new BasicGraphParser();


    private MathGraph(){}

    public static MathGraph init(){
        return new MathGraph();
    }

    public static MathGraph init(String filePath){
        return basicGraphParser.read(filePath);
    }

    public void save(String filePath){
        basicGraphParser.write(this, filePath);
    }

    public void addVertex(Vertex vertex){
        vertexMap.put(counter++, vertex);
    }

    public void deleteVertex(Integer id){
        if (!vertexMap.containsKey(id)) throw new RuntimeException();
        vertexMap.remove(id).destroy();
    }

    public void addEdge(Integer start, Integer end, double width){
        if (!vertexMap.containsKey(start) || !vertexMap.containsKey(end)) throw new RuntimeException();
        vertexMap.get(start).addEdge(vertexMap.get(end), width);
    }

    public void deleteEdge(Integer start, Integer end){
        if (!vertexMap.containsKey(start) || !vertexMap.containsKey(end)) throw new RuntimeException();
        vertexMap.get(start).deleteEdge(vertexMap.get(end));
    }

    public int vertexCount(){
        return vertexMap.keySet().size();
    }

    public int edgeCount(){
        return vertexMap.values().stream().mapToInt(Vertex::edgeCount).sum();
    }

    public boolean isConnected(Integer start, Integer end){
        return vertexMap.get(start).isConnected(vertexMap.get(end));
    }

    public double getWeight(Integer start, Integer end){
        return vertexMap.get(start).getEdge(vertexMap.get(end)).getWidth();
    }
}
