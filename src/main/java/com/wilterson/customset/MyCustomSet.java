package com.wilterson.customset;

/*
 * Develop a class that implements a Set data structure. A Set is a collection containing no duplicate
 * elements and is primarily used to test whether a member is contained within, rather than retrieving
 * a particular member. Please implement the add(element), remove(element), contains(element), and len
 * methods using arrays -- use of dict is not allowed.
 */

import java.util.ArrayList;
import java.util.List;

public class MyCustomSet<T> implements CustomSet<T> {

    private static final int NUMBER_BUCKETS = 4;
    private final List<Bucket> buckets;

    public MyCustomSet() {

        buckets = new ArrayList<>(NUMBER_BUCKETS);

        for (int i = 0; i < NUMBER_BUCKETS; i++) {
            buckets.add(new ArrayBucket());
        }
    }

    public MyCustomSet(Class<Bucket> bucketClass) {

        buckets = new ArrayList<>(NUMBER_BUCKETS);

        try {
            for (int i = 0; i < NUMBER_BUCKETS; i++) {
                buckets.add(bucketClass.getDeclaredConstructor(null).newInstance());
            }
        } catch (Exception exception) {
            throw new RuntimeException("Can't construct MyCustomSet");
        }
    }

    public int size() {
        return buckets
                .stream()
                .mapToInt(Bucket::size)
                .sum();
    }

    public void add(T element) {

        if (contain(element)) {
            return;
        }

        getBucketForElement(element).add(element);
    }

    @Override
    public boolean remove(T element) {

        if (getBucketForElement(element).indexOf(element) == -1) {
            return false;
        }

        return getBucketForElement(element).remove(element);
    }

    @Override
    public boolean contain(T element) {
        return getBucketForElement(element).indexOf(element) != -1;
    }

    private int findBucketIndex(T element) {
        return (NUMBER_BUCKETS - 1) & element.hashCode();
    }

    private Bucket getBucketForElement(T element) {
        int bucketIndex = findBucketIndex(element);
        return buckets.get(bucketIndex);
    }
}


