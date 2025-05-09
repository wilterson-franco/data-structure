package com.wilterson.customset;

/*
 * Develop a class that implements a Set data structure. A Set is a collection containing no duplicate
 * elements and is primarily used to test whether a member is contained within, rather than retrieving
 * a particular member. Please implement the add(element), remove(element), contains(element), and len
 * methods using arrays -- use of dict is not allowed.
 */

public class MyCustomSet<T> implements CustomSet<T> {

    private final Bucket bucket;

    public MyCustomSet() {
        bucket = new ArrayBucket();
    }

    public MyCustomSet(Class<Bucket> bucketClass) {
        try {
            this.bucket = bucketClass.getDeclaredConstructor(null).newInstance();
        } catch (Exception exception) {
            throw new RuntimeException("Can't construct MyCustomSet");
        }
    }

    public int size() {
        return bucket.size();
    }

    public void add(T element) {

        if (contain(element)) {
            return;
        }

        bucket.add(element);
    }

    @Override
    public boolean remove(T element) {

        if (bucket.indexOf(element) == -1) {
            return false;
        }

        return bucket.remove(element);
    }

    @Override
    public boolean contain(T element) {
        return bucket.indexOf(element) != -1;
    }
}


