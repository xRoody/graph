package my.ex.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MathGraph {
    private Map<Integer, Vertex> vertexMap = new HashMap<>();
    private Integer counter = 1;

    private static class BasicGraphParser {
        private static BasicGraphParser instant;

        private BasicGraphParser() {
        }

        public static BasicGraphParser getInstant() {
            if (instant == null) {
                instant = new BasicGraphParser();
            }
            return instant;
        }

        public MathGraph readList(String filePath) throws IOException {
            File f = new File(filePath);
            if (!f.exists()) throw new IllegalStateException();
            try (Scanner scanner = new Scanner(f).useDelimiter(";")) {
                Map<Integer, Vertex> vertexMap = new HashMap<>();
                int maxId = 1;
                while (scanner.hasNext()) {
                    String cur = scanner.next();
                    String[] s = cur.split("\\{");
                    Integer id = Integer.parseInt(s[0].trim());
                    maxId = id;
                    if (!vertexMap.containsKey(id)) {
                        vertexMap.put(id, new Vertex(id));
                    }
                    Vertex v = vertexMap.get(id);
                    for (String ss : s[1].split(",")) {
                        if (!ss.contains("}")) {
                            id = Integer.parseInt(ss.split("\\(")[0].trim());
                            if (id > maxId) maxId = id;
                            if (!vertexMap.containsKey(id)) {
                                vertexMap.put(id, new Vertex(id));
                            }
                            Vertex v1 = vertexMap.get(id);
                            double weight = Double.parseDouble(ss.substring(ss.indexOf('(') + 1, ss.indexOf(')')));
                            v.addEdge(v1, weight);
                        }
                    }
                }
                return new MathGraph(vertexMap, maxId);
            }
        }

        public void writeList(MathGraph mathGraph, String filePath) throws IOException {
            List<Vertex> vertexes = mathGraph.getAllVertexes();
            File f = new File(filePath);
            if (!f.exists()) f.createNewFile();
            try (FileWriter writer = new FileWriter(f)) {
                StringBuilder builder = new StringBuilder();
                for (Vertex v : vertexes) {
                    builder.append(v).append(";\n");
                }
                builder.delete(builder.length() - 2, builder.length());
                writer.write(builder.toString());
            }
        }

        public void writeMatrix(MathGraph graph, String path) throws IOException {
            File f = new File(path);
            if (!f.exists()) f.createNewFile();
            try (FileWriter writer = new FileWriter(f)) {
                StringBuilder builder = new StringBuilder("  ");
                List<Integer> keys = graph.vertexMap.keySet().stream().sorted().toList();
                for (Integer i : keys) {
                    builder.append(i).append("    ");
                }
                builder.append("\n");
                for (Vertex v : graph.vertexMap.values().stream().sorted(Comparator.comparingInt(Vertex::getId)).toList()) {
                    builder.append(v.getId()).append(" ");
                    for (Integer i : keys) {
                        if (v.getId().equals(i)) builder.append("0");
                        else if (v.isConnected(graph.vertexMap.get(i)))
                            builder.append(v.getEdge(graph.vertexMap.get(i)).getWidth());
                        else builder.append("-");
                        builder.append(" | ");
                    }
                    builder.append("\n");
                }
                writer.write(builder.toString());
            }
        }

        public MathGraph readMatrix(String path) throws IOException {
            File f = new File(path);
            try (Scanner s = new Scanner(f)) {
                Map<Integer, Vertex> vertexes = Arrays.stream(s.nextLine().trim().split(" "))
                        .filter(x -> x.length() > 0)
                        .map(x -> new Vertex(Integer.parseInt(x)))
                        .collect(HashMap::new, (map, vertex) -> map.put(vertex.getId(), vertex), HashMap::putAll);
                for (int i = 1; i <= vertexes.keySet().size(); i++) {
                    int j = 1;
                    for (Double d : Arrays.stream(s.nextLine().split(" \\| ")).filter(x -> x.length() > 0).map(x -> {
                        if (x.contains(" ")) x = x.substring(x.indexOf(" ") + 1);
                        if (x.contains("-")) return -1.0;
                        return Double.parseDouble(x);
                    }).toList()) {
                        if (d > 0.0) vertexes.get(i).addEdge(vertexes.get(j), d);
                        j++;
                    }
                }
                return new MathGraph(vertexes, vertexes.keySet().size());
            }
        }
    }

    private MathGraph() {
    }

    private MathGraph(Map<Integer, Vertex> vertexMap, Integer counter) {
        this.vertexMap = vertexMap;
        this.counter = counter;
    }

    public static MathGraph init() {
        return new MathGraph();
    }

    public static MathGraph initByList(String filePath) throws IOException {
        return BasicGraphParser.getInstant().readList(filePath);
    }

    public static MathGraph initByMatrix(String filePath) throws IOException {
        return BasicGraphParser.getInstant().readMatrix(filePath);
    }

    public void saveAsList(String filePath) throws IOException {
        BasicGraphParser.getInstant().writeList(this, filePath);
    }

    public void saveAsMatrix(String filePath) throws IOException {
        BasicGraphParser.getInstant().writeMatrix(this, filePath);
    }

    public void addVertex(Vertex vertex) {
        vertex.setId(counter++);
        vertexMap.put(vertex.getId(), vertex);
    }

    public void deleteVertex(Integer id) {
        if (!vertexMap.containsKey(id)) throw new RuntimeException();
        vertexMap.remove(id).destroy();
    }

    public void addEdge(Integer start, Integer end, double width) {
        if (!vertexMap.containsKey(start) || !vertexMap.containsKey(end)) throw new RuntimeException();
        vertexMap.get(start).addEdge(vertexMap.get(end), width);
    }

    public void deleteEdge(Integer start, Integer end) {
        if (!vertexMap.containsKey(start) || !vertexMap.containsKey(end)) throw new RuntimeException();
        vertexMap.get(start).deleteEdge(vertexMap.get(end));
    }

    public int vertexCount() {
        return vertexMap.keySet().size();
    }

    public int edgeCount() {
        return vertexMap.values().stream().mapToInt(Vertex::edgeCount).sum();
    }

    public boolean isConnected(Integer start, Integer end) {
        return vertexMap.get(start).isConnected(vertexMap.get(end));
    }

    public double getWeight(Integer start, Integer end) {
        return vertexMap.get(start).getEdge(vertexMap.get(end)).getWidth();
    }

    public List<Vertex> getAllVertexes() {
        return new ArrayList<>(vertexMap.values());
    }

    @Override
    public String toString() {
        return vertexMap.toString();
    }
}
