package my.ex;

import my.ex.graph.MathGraph;
import my.ex.graph.Vertex;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String...args) throws IOException {
        /*MathGraph graph=MathGraph.init();
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
        graph.saveAsList("src/main/resources/test1");*/
        MathGraph.initByList("src/main/resources/test1").saveAsMatrix("src/main/resources/test2");
        System.out.println(MathGraph.initByMatrix("src/main/resources/test2"));
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("1 - Добавить ребро\n2 - Добавить вершину\n3 - Удалить ребро\n4 - удалить вершину\n5 - посмотреть список всех вершин\n6 - узнать есть ли ребро\n7 - узнать вес ребра\n0 - выход");

            switch (Integer.parseInt(scanner.next())){
                case 1->{

                }
                case 2->{

                }
                case 3->{

                }
                case 4->{

                }
                case 5->{

                }
                case 6->{

                }
                case 7->{

                }
                case 0->{

                }
            }
        }
    }
}
