package com.wilterson;

public class MinHeap {

    private final int[] minHeap;
    private int size;


    public MinHeap(int maxSize) {

        minHeap = new int[maxSize];
        size = 0;

        for (int i = 0; i < maxSize; i++) {
            minHeap[i] = Integer.MIN_VALUE;
        }
    }

    public void add(int num) {

        minHeap[size] = num;

        size++;

        if (size > 1) {
            heapfyDown(size - 1);
        }
    }

    public Integer peek() {
        return minHeap[0] != Integer.MIN_VALUE ? minHeap[0] : null;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public Integer poll() {

        int removedNum = minHeap[0];

        int lastIndex = size - 1;

        minHeap[0] = minHeap[lastIndex];
        minHeap[lastIndex] = Integer.MIN_VALUE;
        size--;

        heapfyUp(0);

        return removedNum;
    }

    private void heapfyUp(int index) {

        if (isLeaf(index)) {
            return;
        }

        int childMinValueIndex;
        int leftChildIndex = leftChildIndex(index);

        if (hasRightChild(index)) {
            int rightChildIndex = rightChildIndex(index);
            childMinValueIndex = minHeap[leftChildIndex] < minHeap[rightChildIndex] ? leftChildIndex : rightChildIndex;
        } else {
            childMinValueIndex = leftChildIndex;
        }

        if (minHeap[childMinValueIndex] < minHeap[index]) {
            swap(childMinValueIndex, index);
        }

        heapfyUp(childMinValueIndex);
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < size;
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private void heapfyDown(int index) {

        int parentIndex = parentIndex(index);

        if (minHeap[index] < minHeap[parentIndex]) {

            swap(index, parentIndex);

            if (parentIndex > 0) {
                heapfyDown(parentIndex);
            }
        }
    }

    private void swap(int index, int parentIndex) {
        int tmp = minHeap[parentIndex];
        minHeap[parentIndex] = minHeap[index];
        minHeap[index] = tmp;
    }

    private int leftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int rightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private boolean isLeaf(int index) {
        return index >= (size / 2);
    }
}
