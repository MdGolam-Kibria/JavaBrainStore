package com.CrackCode.javaInternalDataStructure.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class GraphImplementation {
    private LinkedList<Integer>[] adjacencyList;

    public GraphImplementation(Integer vertices) {
        this.adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

    }

    private void insert(int source, int destination) {
        /*
         1 <--------------------->2
         |
         |
         3
         @INFO Description : in the above example vertices 1 connected with 2 also 2 connected with 1
         */
        adjacencyList[source].add(destination);
        adjacencyList[destination].add(source);

        System.out.print("added edg");
        System.out.print(adjacencyList[destination]);
        System.out.print(adjacencyList[source]);
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER nUMBER OF VERTICES");
        int vertices = scanner.nextInt();
        System.out.println("enter the edges");
        int edges = scanner.nextInt();

        GraphImplementation graphImpl = new GraphImplementation(vertices);

        System.out.println("Enter the edges of source and destination");
        for (int i = 0; i < edges; i++) {
            System.out.println("Enter source : ");
            int source = scanner.nextInt();
            System.out.println("Enter destination : ");
            int destination = scanner.nextInt();
            graphImpl.insert(source, destination);
        }
    }
}
