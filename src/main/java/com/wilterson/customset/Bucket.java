package com.wilterson.customset;

public interface Bucket {

    void add(Object element);

    int size();

    int indexOf(Object element);

    boolean remove(Object element);

    boolean remove(int index);
}
