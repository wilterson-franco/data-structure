package com.wilterson.customset;

public class LinkedListBuket implements Bucket {

    private Node head;
    private int size;

    @Override
    public void add(Object data) {

        head = new Node(data, head);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contain(Object data) {

        boolean found = false;
        int i = 0;

        while (!found && i < size) {
            if (head.getData().equals(data)) {
                found = true;
            }
            i++;
        }
        return found;
    }

    @Override
    public boolean remove(Object data) {
        size--;
        return false;
    }
}
