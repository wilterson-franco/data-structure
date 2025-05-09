package com.wilterson.customset;

import java.util.LinkedList;
import java.util.List;

class ListBucket implements Bucket {

    private final List<Object> elements;

    public ListBucket() {
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
    public int indexOf(Object element) {
        return elements.indexOf(element);
    }

    @Override
    public boolean remove(Object element) {
        return elements.remove(element);
    }

    @Override
    public boolean remove(int index) {
        elements.remove(index);
        return true;
    }
}
