package com.wilterson.interviewpreparation.binarytrees.listelementsperlevel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import lombok.Getter;

@Getter
public class Tree {

    private Node root;
    Map<TreeLevel, List<Integer>> numbersByLevel;

    public void add(int number) {
        if (root == null) {
            root = new Node(number);
            return;
        }
        add(number, root);
    }

    private void add(int number, Node node) {

        if (number < node.getValue()) {
            if (node.getLeftChild() == null) {
                node.setLeftChild(new Node(number));
            } else {
                add(number, node.getLeftChild());
            }
        } else if (number > node.getValue()) {
            if (node.getRightChild() == null) {
                node.setRightChild(new Node(number));
            } else {
                add(number, node.getRightChild());
            }
        }
    }

    public static void visit(Node node, int level, BiConsumer<Integer, Integer> doTask) {

        if (node == null) {
            return;
        }

        doTask.accept(level, node.getValue());

        visit(node.getLeftChild(), level + 1, doTask);
        visit(node.getRightChild(), level + 1, doTask);
    }

    public void listByLevel(TreeLevel treeLevel, Integer number) {

        if (numbersByLevel == null) {
            List<Integer> numbers = new LinkedList<>();
            numbers.add(number);
            numbersByLevel = new HashMap<>();
            numbersByLevel.put(treeLevel, numbers);
            return;
        }

        if (numbersByLevel.containsKey(treeLevel)) {
            numbersByLevel.get(treeLevel).add(number);
            return;
        }

        List<Integer> numbers = new LinkedList<>();
        numbers.add(number);
        numbersByLevel.put(treeLevel, numbers);
    }
}