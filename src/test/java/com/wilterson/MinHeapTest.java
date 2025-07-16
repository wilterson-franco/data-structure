package com.wilterson;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class MinHeapTest {

    @Test
    void buildTreeWithIncompleteLast() {

        // given
        int[] input = readInput(new ByteArrayInputStream("4,1,3,2".getBytes()));
        MinHeap minHeap = new MinHeap(16);

        // when
        build(minHeap, input);

        // then
        assertThat(minHeap.peek()).isEqualTo(1);

        assertThat(Arrays.toString(destroy(minHeap))).isEqualTo("[1, 2, 3, 4]");
    }

    @Test
    void buildTreeWithCompleteLast() {

        // given
        int[] input = readInput(new ByteArrayInputStream("4,1,3,2,5".getBytes()));
        MinHeap minHeap = new MinHeap(16);

        // when
        build(minHeap, input);

        // then
        assertThat(minHeap.peek()).isEqualTo(1);

        assertThat(Arrays.toString(destroy(minHeap))).isEqualTo("[1, 2, 3, 4, 5]");
    }

    @Test
    void buildTreeWithSmallerInTheEnd() {

        // given
        int[] input = readInput(new ByteArrayInputStream("4,3,2,5,1".getBytes()));
        MinHeap minHeap = new MinHeap(16);

        // when
        build(minHeap, input);

        // then
        assertThat(minHeap.peek()).isEqualTo(1);

        assertThat(Arrays.toString(destroy(minHeap))).isEqualTo("[1, 2, 3, 4, 5]");
    }

    @Test
    void buildTreeWithBiggerToSmaller() {

        // given
        int[] input = readInput(new ByteArrayInputStream("5,4,3,2,1".getBytes()));
        MinHeap minHeap = new MinHeap(16);

        // when
        build(minHeap, input);

        // then
        assertThat(minHeap.peek()).isEqualTo(1);

        assertThat(Arrays.toString(destroy(minHeap))).isEqualTo("[1, 2, 3, 4, 5]");
    }

    @Test
    void buildTreeWithOneElementThenRemoveIt() {

        // given
        int[] input = readInput(new ByteArrayInputStream("5".getBytes()));
        MinHeap minHeap = new MinHeap(16);

        // when
        build(minHeap, input);

        // then
        assertThat(minHeap.peek()).isEqualTo(5);

        assertThat(Arrays.toString(destroy(minHeap))).isEqualTo("[5]");
    }

    @Test
    void buildTreeWithSevenElementsThenRemoveAll() {

        // given
        int[] input = readInput(new ByteArrayInputStream("1,2,3,4,5,6,7".getBytes()));
        MinHeap minHeap = new MinHeap(16);

        // when
        build(minHeap, input);

        // then
        assertThat(minHeap.peek()).isEqualTo(1);

        assertThat(Arrays.toString(destroy(minHeap))).isEqualTo("[1, 2, 3, 4, 5, 6, 7]");
    }

    @Test
    void buildTreeWithFourteenAscendedElementsThenRemoveAll() {

        // given
        int[] input = readInput(new ByteArrayInputStream("1,2,3,4,5,6,7,8,9,10,11,12,13,14".getBytes()));
        MinHeap minHeap = new MinHeap(16);

        // when
        build(minHeap, input);

        // then
        assertThat(minHeap.peek()).isEqualTo(1);

        assertThat(Arrays.toString(destroy(minHeap))).isEqualTo("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]");
    }

    @Test
    void buildTreeWithFourteenDescendedElementsThenRemoveAll() {

        // given
        int[] input = readInput(new ByteArrayInputStream("14,13,12,11,10,9,8,7,6,5,4,3,2,1".getBytes()));
        MinHeap minHeap = new MinHeap(16);

        // when
        build(minHeap, input);

        // then
        assertThat(minHeap.peek()).isEqualTo(1);

        assertThat(Arrays.toString(destroy(minHeap))).isEqualTo("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]");
    }

    private static int[] readInput(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        List<String> inputList = List.of(scanner.nextLine().replace(" ", "").split(","));
        return inputList.stream().mapToInt(Integer::parseInt).toArray();
    }

    private void build(MinHeap minHeap, int[] input) {
        for (int num : input) {
            minHeap.add(num);
        }
    }

    private int[] destroy(MinHeap minHeap) {

        List<Integer> removedItems = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            removedItems.add(minHeap.poll());
        }

        return removedItems.stream().mapToInt(i -> i).toArray();
    }
}