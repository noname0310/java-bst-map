import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * BST Map which is not balanced.
 * @param <K> Key type
 * @param <V> Value type
 */
public class TreeMap<K extends Comparable<K>, V> {
    private static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left;
        public Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private int size;
    private Node<K, V> root;

    /**
     * Create an empty map.
     */
    public TreeMap() {
        size = 0;
        root = null;
    }

    private Node<K, V> findNode(K key) {
        Node<K, V> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                break;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    private Node<K, V> findParentNode(K key) {
        Node<K, V> current = root;
        Node<K, V> parent = null;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                break;
            } else if (cmp < 0) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }
        return parent;
    }

    private interface TraversalCallback<K, V> {
        boolean call(Node<K, V> node);
    }

    private void inorderTraversal(TraversalCallback<K, V> callback) {
        Stack<Node<K, V>> stack = new Stack<>();
        Node<K, V> current = root;

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node<K, V> node = stack.pop();
                if (!callback.call(node)) {
                    return;
                }
                current = node.right;
            }
        }
    }

    /**
     * Get the size of the map.
     * @return Size
     */
    public int size() {
        return this.size;
    }

    /**
     * Check if the map is empty.
     * @return True if the map is empty, otherwise false
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Check if the map contains a key.
     * @param key Key
     * @return True if the map contains the key, otherwise false
     * @throws NullPointerException If the key is null
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        return findNode(key) != null;
    }

    /**
     * Check if the map contains a value.
     * @param value Value
     * @return True if the map contains the value, otherwise false
     * @throws NullPointerException If the value is null
     */
    public boolean containsValue(V value) {
        if (value == null) {
            throw new NullPointerException("Value cannot be null");
        }
        boolean[] found = new boolean[]{ false };
        inorderTraversal(node -> {
            if (node.value.equals(value)) {
                found[0] = true;
                return false;
            }
            return true;
        });

        return found[0];
    }

    /**
     * Get the value of a key.
     * @param key Key
     * @return Value if the key exists, otherwise null
     * @throws NullPointerException If the key is null
     */
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        Node<K, V> node = findNode(key);
        return node == null ? null : node.value;
    }

    /**
     * Put a key-value pair into the map.
     * @param key Key
     * @param value Value
     * @return Old value if the key exists, otherwise null
     * @throws NullPointerException If the key or value is null
     */
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        if (value == null) {
            throw new NullPointerException("Value cannot be null");
        }

        Node<K, V> parentNode = findParentNode(key);

        if (parentNode == null) {
            if (this.root != null) {
                V oldValue = this.root.value;
                this.root.key = key;
                this.root.value = value;
                return oldValue;
            } else {
                this.root = new Node<>(key, value);
                this.size += 1;
                return null;
            }
        }

        int cmp = key.compareTo(parentNode.key);

        if (cmp < 0) {
            if (parentNode.left != null) {
                V oldValue = parentNode.left.value;
                parentNode.left.key = key;
                parentNode.left.value = value;
                return oldValue;
            } else {
                parentNode.left = new Node<>(key, value);
                this.size += 1;
                return null;
            }
        } else/* if (0 < cmp)*/ {
            if (parentNode.right != null) {
                V oldValue = parentNode.right.value;
                parentNode.right.key = key;
                parentNode.right.value = value;
                return oldValue;
            } else {
                parentNode.right = new Node<>(key, value);
                this.size += 1;
                return null;
            }
        }
    }

    /**
     * Remove a key-value pair from the map.
     * @param key Key
     * @return Old value if the key exists, otherwise null
     * @throws NullPointerException If the key is null
     */
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        Node<K, V> parentNode = findParentNode(key);
        Node<K, V> node = parentNode == null 
            ? this.root 
            : (parentNode.left != null && key.compareTo(parentNode.left.key) == 0 
                ? parentNode.left
                : parentNode.right);
        
        if (node == null) {
            return null;
        }

        V oldValue = node.value;

        if (node.left == null && node.right == null) { // Leaf node
            if (parentNode == null) {
                this.root = null;
            } else {
                if (parentNode.left == node) parentNode.left = null;
                else parentNode.right = null;
            }
        } else if (node.left == null || node.right == null) { // Only one child
            Node<K, V> child = node.left == null ? node.right : node.left;
            if (parentNode == null) {
                this.root = child;
            } else {
                if (parentNode.left == node) parentNode.left = child;
                else parentNode.right = child;
            }
        } else { // Both children exist
            Node<K, V> successor = node.right;
            Node<K, V> successorParent = node;
            // guarantee successor has only right child
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            if (successorParent.left == successor) successorParent.left = successor.right;
            else successorParent.right = successor.right;

            successor.left = node.left;
            successor.right = node.right;

            if (parentNode == null) {
                root = successor;
            } else {
                if (parentNode.left == node) parentNode.left = successor;
                else parentNode.right = successor;
            }
        }

        this.size -= 1;
        return oldValue;
    }

    /**
     * Clear the map.
     */
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    /**
     * Get the Collection of values.
     * @return Collection of values
     */
    public List<V> values() {
        List<V> values = new ArrayList<>();
        inorderTraversal(node -> {
            values.add(node.value);
            return true;
        });
        return values;
    }
}
