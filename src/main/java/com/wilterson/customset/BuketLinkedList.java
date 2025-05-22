package com.wilterson.customset;

import java.util.Optional;

public class BuketLinkedList implements Bucket {

    private Node head;
    private int size;

    @Override
    public void add(Object data) {

        Node node = Node.builder()
                .data(data)
                .next(head)
                .previous(null)
                .build();

        if (head != null) {
            head.setPrevious(node);
        }

        head = node;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object data) {
        return findNode(data).isPresent();
    }

    @Override
    public boolean remove(Object element) {

        Optional<Node> optionalNode = findNode(element);

        if (optionalNode.isEmpty()) {
            return false;
        }

        Node node = optionalNode.get();

        if (node == head) {
            head = node.getNext();
            node.setNext(null);
        } else {
            node.getPrevious().setNext(node.getNext());
        }

        size--;

        return true;
    }

    private Optional<Node> findNode(Object element) {

        if (head == null) {
            return Optional.empty();
        }

        Node node = head;

        do {
            if (node.getData().equals(element)) {
                return Optional.of(node);
            }
            node = node.getNext();

        } while (node != null);

        return Optional.empty();
    }
}
