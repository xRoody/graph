package my.ex;

import my.ex.graph.MathGraph;
import my.ex.graph.Vertex;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String...args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        MathGraph mathGraph=MathGraph.init();
        while (true){
            System.out.println("1 - Загрузить граф из списка\n2 - Загрузить граф из матрицы\n3 - Добавить ребро\n4 - Добавить вершину\n5 - Удалить ребро\n6 - удалить вершину\n7 - посмотреть список всех вершин\n8 - узнать есть ли ребро\n9 - узнать вес ребра\n10 - сохранить как список\n11 - сохранить как матрицу\n0 - выход");
            switch (scanner.nextInt()){
                case 1->{
                    System.out.println("Введите путь к файлу");
                    scanner.nextLine();
                    System.out.print("путь: ");
                    mathGraph=MathGraph.initByList(scanner.nextLine());
                }
                case 2->{
                    System.out.println("Введите путь к файлу");
                    scanner.nextLine();
                    System.out.print("путь: ");
                    mathGraph=MathGraph.initByMatrix(scanner.nextLine());
                }
                case 3->{
                    System.out.println("Введите номер первой вершины");
                    int start = scanner.nextInt();
                    System.out.println("Введите номер второй вершины");
                    int end = scanner.nextInt();
                    System.out.println("Введите вес ребрар");
                    double weight = scanner.nextDouble();
                    mathGraph.addEdge(start, end, weight);
                    System.out.println("Ребро" + start +"--("+weight+")-->" + end +" добавлено");
                }
                case 4->{
                    int id=mathGraph.addVertex(new Vertex());
                    System.out.println("Вершина с номером " + id + "добавлена");
                }
                case 5->{
                    System.out.println("Введите номер первой вершины");
                    int start = scanner.nextInt();
                    System.out.println("Введите номер второй вершины");
                    int end = scanner.nextInt();
                    mathGraph.deleteEdge(start, end);
                    System.out.println("Ребро" + start +"---->" + end +" удалено");
                }
                case 6->{
                    System.out.println("Введите номер вершины");
                    int start = scanner.nextInt();
                    mathGraph.deleteVertex(start);
                    System.out.println("Вершина с номером " + start + " удалена");
                }
                case 7->{
                    System.out.println(mathGraph.getAllVertexes().stream().map(x->x.getId()).toList());
                }
                case 8->{
                    System.out.println("Введите номер первой вершины");
                    int start = scanner.nextInt();
                    System.out.println("Введите номер второй вершины");
                    int end = scanner.nextInt();
                    System.out.println(mathGraph.isConnected(start, end));
                }
                case 9->{
                    System.out.println("Введите номер первой вершины");
                    int start = scanner.nextInt();
                    System.out.println("Введите номер второй вершины");
                    int end = scanner.nextInt();
                    System.out.println(mathGraph.getWeight(start, end));
                }
                case 10->{
                    System.out.println("Введите путь к файлу");
                    scanner.nextLine();
                    System.out.print("путь: ");
                    mathGraph.saveAsList(scanner.nextLine());
                }
                case 11->{
                    System.out.println("Введите путь к файлу");
                    scanner.nextLine();
                    System.out.print("путь: ");
                    mathGraph.saveAsMatrix(scanner.nextLine());
                }
                case 0->{
                    return;
                }
            }
        }
    }
}
