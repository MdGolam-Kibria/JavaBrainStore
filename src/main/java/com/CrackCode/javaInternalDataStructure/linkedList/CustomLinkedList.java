package com.CrackCode.javaInternalDataStructure.linkedList;


class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class CustomLinkedList<T> {
    int size = 0;
    Node<T> first = null;
    Node<T> last = null;


    public boolean add(T data) {
        Node<T> currentNode = new Node<>(data);
        if (first == null) {
            first = currentNode;
            last = currentNode;
        } else {
            currentNode.prev = last;
            last.next = currentNode;
            last = currentNode;
        }
        size++;
        return true;
    }


    public boolean add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> currentNode = new Node<>(data);

        if (index == 0) {
            if (first == null) {
                first = currentNode;
                last = currentNode;
            } else {
                first.prev = currentNode;
                currentNode.next = first;
                first = currentNode;
            }
        } else if (index == size) {
            last.next = currentNode;
            currentNode.prev = last;
            last = currentNode;
        } else {
            Node<T> beforeNode = findByIndex(index - 1);
            Node<T> afterNode = beforeNode.next;

            beforeNode.next = currentNode;
            currentNode.prev = beforeNode;

            currentNode.next = afterNode;
            afterNode.prev = currentNode;
        }

        size++;
        return true;
    }

    public boolean remove(int index) {
        if (index == 0) first = first.next;
        if (index < 0 || index > (size - 1)) throw new IndexOutOfBoundsException();
        if (index == (size - 1)) last = last.prev;

        Node<T> expectedNode = findByIndex(index);

        Node<T> beforeNode = expectedNode.prev;
        Node<T> afterNode = expectedNode.next;

        beforeNode.next = expectedNode.next;
        afterNode.prev = beforeNode;

        size--;
        return true;
    }


    private Node<T> findByIndex(int index) {
        if (index == 0) return first;
        if (index > (size - 1)) throw new IndexOutOfBoundsException();
        if (index == (size - 1)) return last;
        Node<T> expectedNode = first;
        for (int i = 0; i < index; i++) {
            expectedNode = expectedNode.next;
        }
        return expectedNode;
    }

    public void addFirst(T data) {
        Node<T> currentNode = new Node<>(data);
        if (first == null) {
            first = currentNode;
            last = currentNode;
        } else {
            first.prev = currentNode;
            currentNode.next = first;
            first = currentNode;
        }
        size++;
    }

    public boolean addLast(T data) {
        Node<T> currentNode = new Node<>(data);
        if (first == null) {
            first = currentNode;
            last = currentNode;
        } else {
            currentNode.prev = last;
            last.next = currentNode;
            last = currentNode;
        }
        size++;
        return true;
    }

    public static void main(String[] args) {
        CustomLinkedList<Integer> linkedListExample = new CustomLinkedList<>();
        linkedListExample.add(10);
        linkedListExample.add(20);
        linkedListExample.add(30);
        linkedListExample.addFirst(5);
        linkedListExample.addLast(35);
        linkedListExample.add(1, 7);
        System.out.println("Remove 20 = " + linkedListExample.remove(3));//remove 20
        while (linkedListExample.first != null) {
            System.out.print(linkedListExample.first.data + " ");
            linkedListExample.first = linkedListExample.first.next;
        }
        System.out.println("\nCurrent linkedList Size : " + linkedListExample.size);
    }
}
