package com.wilterson.interviewpreparation.binarytrees.listelementsperlevel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TreeTest {

    private Tree tree;

    @Test
    void givenNumberBiggerThan_whenAddedToTree_thenShouldGoRight() {

        // given
        tree = new Tree();

        // when
        tree.add(1);
        tree.add(2);

        // then
        assertThat(tree.getRoot()).isNotNull().isEqualTo(new Node(1));
        assertThat(tree.getRoot().getLeftChild()).isNull();
        assertThat(tree.getRoot().getRightChild()).isNotNull().isEqualTo(new Node(2));
    }

    @Test
    void givenNumberSmallerThan_whenAddedToTree_thenShouldGoLeft() {

        // given
        tree = new Tree();

        // when
        tree.add(2);
        tree.add(1);

        // then
        assertThat(tree.getRoot()).isNotNull().isEqualTo(new Node(2));
        assertThat(tree.getRoot().getRightChild()).isNull();
        assertThat(tree.getRoot().getLeftChild()).isNotNull().isEqualTo(new Node(1));
    }

    @Test
    void givenNumberEqualTo_whenAddedToTree_thenShouldBeSkipped() {

        // given
        tree = new Tree();

        // when
        tree.add(1);
        tree.add(1);

        // then
        assertThat(tree.getRoot()).isNotNull().isEqualTo(new Node(1));
        assertThat(tree.getRoot().getLeftChild()).isNull();
        assertThat(tree.getRoot().getRightChild()).isNull();
    }
}