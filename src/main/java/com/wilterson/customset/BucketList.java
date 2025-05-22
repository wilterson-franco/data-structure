package com.wilterson.customset;

import java.util.LinkedList;
import java.util.List;

class BucketList implements Bucket {

    private final List<Object> elements;

    public BucketList() {
        elements = new LinkedList<>();
    }

    @Override
    public void add(Object element) {
        elements.add(element);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean remove(Object element) {
        return elements.remove(element);
    }

    @Override
    public boolean contains(Object element) {
        return elements.contains(element);
    }
}
