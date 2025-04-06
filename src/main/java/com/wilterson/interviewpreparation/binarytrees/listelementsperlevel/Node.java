package com.wilterson.interviewpreparation.binarytrees.listelementsperlevel;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {

    private int value;
    private Node leftChild;
    private Node rightChild;

    public Node(int value) {
        this.value = value;
        leftChild = null;
        rightChild = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, leftChild, rightChild);
    }
}

