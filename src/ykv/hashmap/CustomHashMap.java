package ykv.hashmap;

import java.util.Objects;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_FILL = 0.75;
    private Node<K, V>[] buckets;
    private int size;

    public CustomHashMap() {
        this.size = 0;
        this.buckets = new Node[DEFAULT_CAPACITY];
    }

    public CustomHashMap(int capacity) {
        this.size = 0;
        this.buckets = new Node[capacity];
    }

    public void put(K key, V value) {
        if (key == null) {
            putForNull(value);
            return;
        }

        int hash = hash(key);
        int index = indexForHash(hash, buckets.length);

        Node<K, V> newNode = new Node<>(hash, key, value, null);
        Node<K, V> currentNode = buckets[index];

        if (currentNode == null) {
            buckets[index] = newNode;
            size++;
        } else {
            while (currentNode.next != null) {
                if (currentNode.hash == hash && (key.equals(currentNode.key))) {
                    break;
                }
                currentNode = currentNode.next;
            }

            if (key.equals(currentNode.key)) {
                currentNode.value = value;
            } else {
                currentNode.next = newNode;
                size++;
            }
        }

        if ((double) size / buckets.length > DEFAULT_FILL) {
            resize(buckets.length * 2);
        }
    }

    public V get(K key) {
        if (key == null) {
            return getForNull();
        }

        int index = indexForHash(hash(key), buckets.length);

        Node<K, V> currentNode = buckets[index];

        while (currentNode != null) {
            if (key.equals(currentNode.key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public void remove(K key) {
        if (key == null) {
            removeForNull();
            return;
        }

        int hash = hash(key);
        int index = indexForHash(hash, buckets.length);

        Node<K, V> currentNode = buckets[index];
        Node<K, V> prevNode = null;

        while (currentNode != null) {
            if (key.equals(currentNode.key)) {
                if (prevNode == null) {
                    buckets[index] = currentNode.next;
                } else {
                    prevNode.next = currentNode.next;
                }
                size--;
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    private void putForNull(V value) {
        if (buckets[0] == null) {
            buckets[0] = new Node<>(0, null, value, null);
            size++;
        } else {
            Node<K, V> currentNode = buckets[0];
            while (currentNode.next != null && currentNode.key != null) {
                currentNode = currentNode.next;
            }

            if (currentNode.key == null) {
                currentNode.value = value;
            } else {
                currentNode.next = new Node<>(0, null, value, null);
                size++;
            }
        }

        if ((double) size / buckets.length > DEFAULT_FILL) {
            resize(buckets.length * 2);
        }

    }

    private V getForNull() {
        Node<K, V> currentNode = buckets[0];
        while (currentNode != null) {
            if (currentNode.key == null) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    private void removeForNull() {
        Node<K, V> currentNode = buckets[0];
        Node<K, V> prevNode = null;

        while (currentNode != null) {
            if (currentNode.key == null) {
                if (prevNode == null) {
                    buckets[0] = currentNode.next;
                } else {
                    prevNode.next = currentNode.next;
                }
                size--;
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

    }

    private int hash(K key) {
        return key == null ? 0 : Objects.hash(key.hashCode());
    }

    private int indexForHash(int hash, int capacity) {
        return hash % capacity;
    }

    private void resize(int newCapacity) {
        Node<K, V>[] newBuckets = new Node[newCapacity];

        for (Node<K, V> node : buckets) {
            Node<K, V> currentNode = node;
            while (currentNode != null) {
                K key = currentNode.key;
                int hash = hash(key);
                int index = indexForHash(hash, newCapacity);

                Node<K, V> newNode = new Node<>(hash, key, currentNode.value, null);
                if (newBuckets[index] == null) {
                    newBuckets[index] = newNode;
                } else {
                    Node<K, V> current = newBuckets[index];
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = newNode;
                }
                currentNode = currentNode.next;
            }
        }
        buckets = newBuckets;
    }

    // ** Node  **
    // ****************

    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
