package com.wilterson.customset;

public interface Bucket {

    void add(Object element);

    int size();

    boolean remove(Object element);

    boolean contains(Object element);
}
