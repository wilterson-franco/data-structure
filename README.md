# Custom Set

Develop a class that implements a Set data structure. A Set is a collection containing no duplicate elements and is primarily used to test whether a member is contained within, rather than retrieving a particular member. Please implement the add(element), remove(element), contains(element), and  __len__ methods using arrays -- use of dict is not allowed

## Characteristics Of A Set
- Unique items
- Permits nulls
- Items are not sorted either ordered
- It's not thread safe

## Solution 1
- At the constructor, create a fixed number of buckets (it must be a power of 2)
  - Each bucket is an array of T
- The add() method must
  - use the element's hashcode to determine in which bucket it must be placed
      bucketIndex = (n-1) & element's hashcode
  - use the element's equals method to determine if the element already exist. If so, skip it

public class MyHashSet<T> {

    Bucket<T>[] buckets;

    public MyHashSet(int totalBuckets) {
        this.totalBuckets = totalBuckets;
        this.buckets = new Buckets<>[totalBuckets];
    }

    public void add(T element) {

        if (contain(element)) {
            return;
        }

        int index = elementBucket(element);

        if (buckets[index] == null) {
            buckets[index] = new Bucket<T>()
        }

        buckets[index].add(element);
    }

    puclic boolean contain(T element) {
        int index = elementBucket(element);

        if (buckets[index] == null) {
            return false;
        }

        return buckets[index].contain(element);
    }

    public T remove(T element) {

        if (!contain(element)) {
            return null;
        }

        return buckets[index].remove(element);
    }

    public int size() {
        return Arrays.asList(buckets).stream().reduce(0, (a, b) -> a.size() + b.size());
    }

    private int elementBucket(T element) {
        return (totalBuckets -1) & element.hashCode();
    }

    class Bucket<U> {
        private List<U> elements;

        public Bucket() {
            this.elements = new ArrayList<>();
        }

        public void add(U element) {
            elements.add(element);
        }

        public boolean contain(U element) {
            return elements.contain(element);
        }

        public U remove(U element) {
            return elements.delete(e);
        }
    }

}