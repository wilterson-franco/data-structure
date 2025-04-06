package com.wilterson.interviewpreparation.binarytrees.listelementsperlevel;

public class InterviewPreparation {

    private InputController inputController;

    public InterviewPreparation(InputController inputController) {
        this.inputController = inputController;
    }

    public static void main(String[] args) {

        InputController ic = new InputController(System.in);
        InterviewPreparation interviewPreparation = new InterviewPreparation(ic);

        Tree tree = interviewPreparation.buildTree();
        Tree.visit(tree.getRoot(), 0, (l, x) -> tree.listByLevel(new TreeLevel(l), x));
//        Tree.visit(tree.getRoot(), 0, (l, x) -> System.out.println("Level %d: %d".formatted(l, x)));

        tree.getNumbersByLevel().forEach((k, v) -> {
            String join = String.join(", ", v.stream().map(Object::toString).toList());
            System.out.printf("Level %d: %s%n", k.level(), join);
        });
    }

    private Tree buildTree() {
        Tree tree = new Tree();
        inputController.captureInput().forEach(tree::add);
        return tree;
    }
}
