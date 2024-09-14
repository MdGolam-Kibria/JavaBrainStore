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


public class BinaryTreeCreationWithPrePostInorderExample {
    static final int terminationValue = -1;

    static Node<Integer> create() {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> root = null;
        System.out.println("Enter Value : ");
        Integer currentData = null;
        try {
            currentData = scanner.nextInt();
        } catch (InputMismatchException e) {
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


    static void preOrder(Node<Integer> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    static void inOrder(Node<Integer> root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data);
        inOrder(root.right);
    }

    static void postOrder(Node<Integer> root) {
        if (root == null) {
            return;

        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data);
    }

    public static void main(String[] args) {
        Node<Integer> root = create();
        System.out.println("Successfully created binary tree");
        System.out.println("Going to parse data inorder   = left root right. preorder  = root left right. postorder = left right root.");
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }
}
