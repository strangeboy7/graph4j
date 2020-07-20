package com.github.strangeboy7.graph4j;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * indexed priority queue
 * @param <T> index type
 * @param <I> item in priority queue
 */
class IndexedPQ<T, I extends Comparable<I>> {
    private final Map<T, I> map = new HashMap<>();
    private PriorityQueue<I> pq = new PriorityQueue<>();

    public void insert(T index, I item) {
        map.put(index, item);
        if (!pq.contains(item)) {
            pq.add(item);
        }
    }

    public void change(T index, I item) {
        if (map.containsKey(index)) {
            pq.remove(map.get(index));
        }
        map.put(index, item);
        pq.add(item);
    }

    public void delete(T index) {
        if (map.containsKey(index)) {
            pq.remove(map.get(index));
            map.remove(index);
        }
    }

    public boolean contains(T index) {
        return map.containsKey(index);
    }

    public I poll() {
        I i = pq.poll();
        T key = map.entrySet().stream().filter(x -> x.getValue().equals(i)).findFirst().get().getKey();
        map.remove(key);
        return i;
    }

    public T pollIndex() {
        I i = pq.poll();
        T key = map.entrySet().stream().filter(x -> x.getValue().equals(i)).findFirst().get().getKey();
        map.remove(key);
        return key;
    }

    public boolean isEmpty() {
        return pq.isEmpty();
    }

    public int size() {
        return pq.size();
    }
}
