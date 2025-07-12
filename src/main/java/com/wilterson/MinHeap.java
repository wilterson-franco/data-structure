package com.wilterson;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinHeap {

    private Queue<Integer> minHeap = new PriorityQueue<>();

    public void add(int num) {
        minHeap.add(num);
    }

    public Integer peek() {
        return minHeap.peek();
    }

    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    public Integer poll() {
        return minHeap.poll();
    }
}
