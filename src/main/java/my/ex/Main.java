package my.ex;

import my.ex.graph.MathGraph;
import my.ex.graph.Vertex;

import java.io.IOException;

public class Main {
    public static void main(String...args) throws IOException {
        /*
        MathGraph graph=MathGraph.init();
        graph.addVertex(new Vertex());//1
        graph.addVertex(new Vertex());//2
        graph.addVertex(new Vertex());//3
        graph.addVertex(new Vertex());//4
        graph.addVertex(new Vertex());//5
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 2);
        graph.addEdge(1, 5, 3);
        graph.save("src/main/resources/test");*/
        System.out.println(MathGraph.init("src/main/resources/test"));
    }
}
