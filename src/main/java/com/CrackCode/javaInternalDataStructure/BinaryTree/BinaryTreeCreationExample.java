package com.CrackCode.javaInternalDataStructure.BinaryTree;

import java.util.InputMismatchException;
import java.util.Scanner;

class Node<T> {
    T data;
    Node<T> left;
    Node<T> right;

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}


public class BinaryTreeCreationExample {
    static final int terminationValue = -1;

    static Node<Integer> create() {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> root = null;
        System.out.println("Enter Value : ");
        Integer currentData = null;
        try {
            currentData = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Wrong Input!! Please Enter Integer Value");
            System.exit(0);
        }
        if (currentData == terminationValue) {
            //if we terminate the node by set the [terminationValue] the node value will be null
            return null;
        }
        root = new Node<>(currentData);
        System.out.println("Enter Left child of  : " + root.data);
        root.left = create();
        System.out.println("Enter Right child of  : " + root.data);
        root.right = create();
        return root;
    }

    public static void main(String[] args) {
        Node<Integer> createBinaryTree = create();

        System.out.println("okay");
    }
}
