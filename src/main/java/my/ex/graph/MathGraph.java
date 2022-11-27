package my.ex.graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MathGraph {
    private Map<Integer, Vertex> vertexMap=new HashMap<>();
    private Integer counter=1;

    private static class BasicGraphParser {
        private static BasicGraphParser instant;

        private BasicGraphParser() {
        }

        public static BasicGraphParser getInstant(){
            if (instant==null){
                instant=new BasicGraphParser();
            }
            return instant;
        }

        public MathGraph read(String filePath) throws IOException {
            File f=new File(filePath);
            if (!f.exists()) throw new IllegalStateException();
            try(Scanner scanner=new Scanner(f).useDelimiter(";")){
                Map<Integer, Vertex> vertexMap = new HashMap<>();
                int maxId=1;
                while (scanner.hasNext()){
                    String cur=scanner.next();
                    String[] s=cur.split("\\{");
                    Integer id=Integer.parseInt(s[0].trim());
                    maxId=id;
                    if (!vertexMap.containsKey(id)){
                        vertexMap.put(id,new Vertex(id));
                    }
                    Vertex v=vertexMap.get(id);
                    for (String ss:s[1].split(",")){
                        if(!ss.contains("}")){
                            id=Integer.parseInt(ss.split("\\(")[0].trim());
                            if (id>maxId) maxId=id;
                            if (!vertexMap.containsKey(id)){
                                vertexMap.put(id,new Vertex(id));
                            }
                            Vertex v1=vertexMap.get(id);
                            double weight=Double.parseDouble(ss.substring(ss.indexOf('(')+1, ss.indexOf(')')));
                            v.addEdge(v1,weight);
                        }
                    }
                }
                return new MathGraph(vertexMap, maxId);
            }
        }

        public void write(MathGraph mathGraph, String filePath) throws IOException {
            List<Vertex> vertexes=mathGraph.getAllVertexes();
            File f=new File(filePath);
            if (!f.exists()) f.createNewFile();
            try(FileWriter writer=new FileWriter(f)){
                StringBuilder builder=new StringBuilder();
                for (Vertex v:vertexes){
                    builder.append(v).append(";\n");
                }
                builder.delete(builder.length()-2, builder.length());
                writer.write(builder.toString());
            }
        }
    }

    private MathGraph(){}

    private MathGraph(Map<Integer, Vertex> vertexMap, Integer counter) {
        this.vertexMap = vertexMap;
        this.counter = counter;
    }

    public static MathGraph init(){
        return new MathGraph();
    }

    public static MathGraph init(String filePath) throws IOException {
        return BasicGraphParser.getInstant().read(filePath);
    }


    public void save(String filePath) throws IOException {
        BasicGraphParser.getInstant().write(this, filePath);
    }

    public void addVertex(Vertex vertex){
        vertex.setId(counter++);
        vertexMap.put(vertex.getId(), vertex);
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

    public List<Vertex> getAllVertexes(){
        return new ArrayList<>(vertexMap.values());
    }

    @Override
    public String toString() {
        return vertexMap.toString();
    }
}
