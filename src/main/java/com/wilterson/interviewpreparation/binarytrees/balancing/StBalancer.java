package com.wilterson.interviewpreparation.binarytrees.balancing;

import static java.lang.Math.abs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

public class StBalancer {

    public static void main(String[] args) {

        StBalancer balancer = new StBalancer();
        List<Node> nodes = balancer.captureInput(new ByteArrayInputStream("1\n4\n28\n5\n7\n10\n8\n2\n15\n89\n23\n0\n100\n43\n27\n6\n3\n9\nq\n".getBytes())).stream().map(Node::new).toList();

        Node root = nodes.get(0);

        for (int i = 1; i < nodes.size(); i++) {
            root.add(nodes.get(i));
        }

        List<Node> visitedNodes = new ArrayList<>();
        root.preOrderVisit(n -> balancer.add(n, visitedNodes));

        List<Node> sortedNodes = visitedNodes
                .stream()
                .sorted(Comparator.comparing(Node::getDigit))
                .toList();

        Node newRoot = balancer.buildBalancedTree(0, sortedNodes.size() - 1, sortedNodes);

        balancer.printTree(newRoot, "", false);
    }

    private void printTree(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            return;
        }

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.getDigit());

        // Prepare the next level of prefix
        String childPrefix = prefix + (isLeft ? "│   " : "    ");
        printTree(node.leftChild, childPrefix, true);
        printTree(node.rightChild, childPrefix, false);
    }

    private void add(Node node, List<Node> nodes) {
        nodes.add(node);
    }

    private Node buildBalancedTree(int beginning, int end, List<Node> sortedNodes) {

        if (end < beginning) {
            return null;
        }

        int middle = (beginning + end) / 2;

        Node middleNode = sortedNodes.get(middle);

        Node left = buildBalancedTree(beginning, middle - 1, sortedNodes);
        Node right = buildBalancedTree(middle + 1, end, sortedNodes);

        middleNode.attachLeftAndRight(left, right);

        return middleNode;
    }

    private List<Integer> captureInput(InputStream inputStream) {

        List<Integer> integers = new ArrayList<>();

        Scanner scan = new Scanner(inputStream);

        do {

            String input = scan.next();

            if (!isDigit(input)) {
                break;
            }

            integers.add(Integer.parseInt(input));

        } while (true);

        return integers;
    }

    private boolean isDigit(String input) {
        return input.chars().mapToObj(c -> ((char) c)).anyMatch(Character::isDigit);
    }
}

@Getter
class Node implements Comparable<Node> {

    private Integer digit;

    @Setter
    private int depth;

    Node leftChild;
    Node rightChild;

    public Node(Integer digit) {
        this.digit = digit;
    }

    public int compareTo(Node other) {
        return this.getDigit().compareTo(other.getDigit());
    }

    public void preOrderVisit(Consumer<Node> consumer) {

        consumer.accept(this);

        if (this.leftChild != null) {
            leftChild.preOrderVisit(consumer);
        }

        if (this.rightChild != null) {
            rightChild.preOrderVisit(consumer);
        }
    }

    public void attachLeftAndRight(Node left, Node right) {
        this.leftChild = left;
        this.rightChild = right;
    }

    public void add(Node newNode) {

        int compare = newNode.compareTo(this);

        if (compare == 0) {
            return;
        }

        if (compare < 0) {

            if (this.leftChild != null) {
                this.leftChild.add(newNode);
            } else {
                this.leftChild = newNode;
            }

            return;
        }

        if (this.rightChild != null) {
            this.rightChild.add(newNode);
        } else {
            this.rightChild = newNode;
        }
    }

    @Override
    public String toString() {
        return this.digit.toString();
    }
}
