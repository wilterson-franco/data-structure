package com.wilterson.customset;

public interface CustomSet<T> {

    void add(T element);

    boolean remove(T element);

    boolean contain(T element);

    int size();
}
