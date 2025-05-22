package com.wilterson.customset;

/*
 * Develop a class that implements a Set data structure. A Set is a collection containing no duplicate
 * elements and is primarily used to test whether a member is contained within, rather than retrieving
 * a particular member. Please implement the add(element), remove(element), contains(element), and len
 * methods using arrays -- use of dict is not allowed.
 */

import java.util.ArrayList;
import java.util.List;

public class CustomHashSet<T> implements CustomSet<T> {

    private static final int DEFAULT_CAPACITY = 4;
    private int capacity;

    private final List<Bucket> buckets;

    public CustomHashSet() {

        capacity = DEFAULT_CAPACITY;

        buckets = new ArrayList<>(DEFAULT_CAPACITY);

        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets.add(new BucketArray());
        }
    }

    public CustomHashSet(Class<Bucket> bucketClass) {

        capacity = DEFAULT_CAPACITY;

        buckets = new ArrayList<>(DEFAULT_CAPACITY);

        try {
            for (int i = 0; i < DEFAULT_CAPACITY; i++) {
                buckets.add(bucketClass.getDeclaredConstructor(null).newInstance());
            }
        } catch (Exception exception) {
            throw new RuntimeException("Can't construct MyCustomSet");
        }
    }


    @Override
    public void add(T element) {

        if (contains(element)) {
            return;
        }

        getBucketForElement(element).add(element);
    }

    @Override
    public boolean remove(T element) {
        return getBucketForElement(element).remove(element);
    }

    @Override
    public boolean contains(T element) {
        return getBucketForElement(element).contains(element);
    }

    @Override
    public int size() {
        return buckets
                .stream()
                .mapToInt(Bucket::size)
                .sum();
    }

    private Bucket getBucketForElement(T element) {
        return buckets.get(findBucketIndexForElement(element));
    }

    private int findBucketIndexForElement(T element) {
        return (capacity - 1) & element.hashCode();
    }
}