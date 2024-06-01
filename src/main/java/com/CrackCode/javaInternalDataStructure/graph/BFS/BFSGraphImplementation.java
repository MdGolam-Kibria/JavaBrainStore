package com.CrackCode.javaInternalDataStructure.graph.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BFSGraphImplementation {
    private LinkedList<Integer>[] adjacencyList;

    public BFSGraphImplementation(Integer vertices) {
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

    }


    public void bfs(Integer source) {
        boolean[] visitedNodes = new boolean[adjacencyList.length];
        int[] parentNodes = new int[adjacencyList.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visitedNodes[source] = true;
        parentNodes[source] = -1;//here -1 means this is top root vertex from where we have stared our traversal.

        while (!queue.isEmpty()) {
            Integer currentParent = queue.poll();
            System.out.print(currentParent);

            for (Integer i : adjacencyList[currentParent]) {
                if (!visitedNodes[i]) {
                    visitedNodes[i] = true;
                    queue.add(i);
                    parentNodes[i] = currentParent;
                }
            }

        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER nUMBER OF VERTICES");
        int vertices = scanner.nextInt();
        System.out.println("enter the edges");
        int edges = scanner.nextInt();

        BFSGraphImplementation graphImpl = new BFSGraphImplementation(vertices);

        System.out.println("Enter the edges of source and destination");
        for (int i = 0; i < edges; i++) {
            System.out.println("Enter source : ");
            int source = scanner.nextInt();
            System.out.println("Enter destination : ");
            int destination = scanner.nextInt();
            graphImpl.insert(source, destination);
        }

        System.out.println("Enter source for bfs traversal : ");
        Integer source = scanner.nextInt();
        graphImpl.bfs(source);//@INFO For BFS traversal
    }
}
