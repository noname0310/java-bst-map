import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class TreeMapTest {

    @org.junit.jupiter.api.Test
    void size() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        assertEquals(0, map.size());
        map.put(1, 1);
        assertEquals(1, map.size());
        map.put(2, 2);
        assertEquals(2, map.size());
        map.put(3, 3);
        assertEquals(3, map.size());

        map.remove(1);
        assertEquals(2, map.size());
        map.remove(2);
        assertEquals(1, map.size());

        map.clear();
        assertEquals(0, map.size());

        map.put(1, 1);
        assertEquals(1, map.size());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        assertTrue(map.isEmpty());
        map.put(1, 1);
        assertFalse(map.isEmpty());
        map.remove(1);
        assertTrue(map.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void containsKey() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        assertFalse(map.containsKey(1));
        map.put(1, 1);
        assertTrue(map.containsKey(1));
        map.remove(1);
        assertFalse(map.containsKey(1));

        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        assertTrue(map.containsKey(1));
        assertTrue(map.containsKey(2));
        assertTrue(map.containsKey(3));
        assertFalse(map.containsKey(4));

        map.remove(1);
        assertFalse(map.containsKey(1));
        assertTrue(map.containsKey(2));
        assertTrue(map.containsKey(3));

        map.remove(2);
        assertFalse(map.containsKey(1));
        assertFalse(map.containsKey(2));
        assertTrue(map.containsKey(3));

        map.remove(3);
        assertFalse(map.containsKey(1));
        assertFalse(map.containsKey(2));
        assertFalse(map.containsKey(3));

        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        assertTrue(map.containsKey(1));
        assertTrue(map.containsKey(2));
        assertTrue(map.containsKey(3));

        assertThrows(NullPointerException.class, () -> map.containsKey(null));
    }

    @org.junit.jupiter.api.Test
    void containsValue() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        assertFalse(map.containsValue(1));
        map.put(1, 1);
        assertTrue(map.containsValue(1));
        map.remove(1);
        assertFalse(map.containsValue(1));

        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        assertTrue(map.containsValue(1));
        assertTrue(map.containsValue(2));
        assertTrue(map.containsValue(3));
        assertFalse(map.containsValue(4));

        map.remove(1);
        assertFalse(map.containsValue(1));
        assertTrue(map.containsValue(2));
        assertTrue(map.containsValue(3));

        map.remove(2);
        assertFalse(map.containsValue(1));
        assertFalse(map.containsValue(2));
        assertTrue(map.containsValue(3));

        map.remove(3);
        assertFalse(map.containsValue(1));
        assertFalse(map.containsValue(2));
        assertFalse(map.containsValue(3));

        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        assertTrue(map.containsValue(1));
        assertTrue(map.containsValue(2));
        assertTrue(map.containsValue(3));

        assertThrows(NullPointerException.class, () -> map.containsValue(null));
    }

    @org.junit.jupiter.api.Test
    void get() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        assertNull(map.get(1));
        map.put(1, 1);
        assertEquals(1, map.get(1));
        map.remove(1);
        assertNull(map.get(1));

        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        assertEquals(1, map.get(1));
        assertEquals(2, map.get(2));
        assertEquals(3, map.get(3));
        assertNull(map.get(4));

        map.remove(1);
        assertNull(map.get(1));
        assertEquals(2, map.get(2));
        assertEquals(3, map.get(3));

        map.remove(2);
        assertNull(map.get(1));
        assertNull(map.get(2));
        assertEquals(3, map.get(3));

        map.remove(3);
        assertNull(map.get(1));
        assertNull(map.get(2));
        assertNull(map.get(3));

        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        assertEquals(1, map.get(1));
        assertEquals(2, map.get(2));
        assertEquals(3, map.get(3));

        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        assertEquals(2, map.get(1));
        assertEquals(3, map.get(2));
        assertEquals(4, map.get(3));

        map.put(1, 3);
        map.put(2, 4);
        map.put(3, 5);
        assertEquals(3, map.get(1));
        assertEquals(4, map.get(2));
        assertEquals(5, map.get(3));

        assertThrows(NullPointerException.class, () -> map.get(null));
    }

    @org.junit.jupiter.api.Test
    void put() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        assertNull(map.put(1, 1));
        assertEquals(1, map.put(1, 2));
        assertEquals(2, map.put(1, 3));
        assertEquals(3, map.put(1, 4));
        
        assertNull(map.put(2, 1));
        assertEquals(1, map.put(2, 2));
        assertEquals(2, map.put(2, 3));
        assertEquals(3, map.put(2, 4));

        assertNull(map.put(3, 1));
        assertEquals(1, map.put(3, 2));
        assertEquals(2, map.put(3, 3));
        assertEquals(3, map.put(3, 4));

        map.remove(1);
        assertNull(map.put(1, 1));
        assertEquals(1, map.put(1, 2));
        assertEquals(2, map.put(1, 3));
        assertEquals(3, map.put(1, 4));

        assertThrows(NullPointerException.class, () -> map.put(null, 1));
        assertThrows(NullPointerException.class, () -> map.put(1, null));
        assertThrows(NullPointerException.class, () -> map.put(null, null));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        assertNull(map.remove(1));
        map.put(1, 1);
        assertEquals(1, map.remove(1));
        assertNull(map.remove(1));

        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        assertEquals(1, map.remove(1));
        assertEquals(2, map.remove(2));
        assertEquals(3, map.remove(3));
        assertNull(map.remove(4));

        assertThrows(NullPointerException.class, () -> map.remove(null));
    }

    @org.junit.jupiter.api.Test
    void removeBothChildrenCase1() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(8, 8);
        map.put(3, 3);
        map.put(2, 2);
        map.put(5, 5);
        map.put(10, 10);
        map.put(9, 9);
        map.put(14, 14);
        map.put(11, 11);
        map.put(16, 16);
        map.remove(10);
        
        assertEquals(8, map.size());
        assertEquals(null, map.get(10));
    }

    @org.junit.jupiter.api.Test
    void removeBothChildrenCase2() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(8, 8);
        map.put(3, 3);
        map.put(2, 2);
        map.put(5, 5);
        map.put(10, 10);
        map.put(14, 14);
        map.put(11, 11);
        map.put(16, 16);
        map.remove(8);
        
        assertEquals(7, map.size());
        assertEquals(null, map.get(8));
    }

    @org.junit.jupiter.api.Test
    void removeOneChildCase1() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(8, 8);
        map.put(3, 3);
        map.put(2, 2);
        map.put(5, 5);
        map.put(10, 10);
        map.put(14, 14);
        map.put(11, 11);
        map.put(16, 16);
        map.remove(11);
        
        assertEquals(7, map.size());
        assertEquals(null, map.get(11));

        map.clear();
        map.put(10, 10);
        map.put(8, 8);
        map.put(3, 3);
        map.put(2, 2);
        map.remove(3);

        assertEquals(3, map.size());
        assertEquals(null, map.get(3));
    }

    @org.junit.jupiter.api.Test
    void removeOneChildCase2() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(8, 8);
        map.put(3, 3);
        map.put(2, 2);
        map.put(5, 5);
        map.put(10, 10);
        map.put(14, 14);
        map.put(11, 11);
        map.put(16, 16);
        map.remove(16);
        
        assertEquals(7, map.size());
        assertEquals(null, map.get(16));

        map.clear();
        map.put(10, 10);
        map.put(18, 18);
        map.put(113, 113);
        map.put(1112, 1112);
        map.remove(113);

        assertEquals(3, map.size());
        assertEquals(null, map.get(113));
    }

    @org.junit.jupiter.api.Test
    void clear() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.clear();
        assertEquals(0, map.size());
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.clear();
        assertEquals(0, map.size());
    }

    @org.junit.jupiter.api.Test
    void values() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Collection<Integer> values = map.values();
        assertEquals(0, values.size());
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        values = map.values();
        assertEquals(3, values.size());
        assertTrue(values.contains(1));
        assertTrue(values.contains(2));
        assertTrue(values.contains(3));
        assertFalse(values.contains(4));
        map.remove(1);
        values = map.values();
        assertEquals(2, values.size());
        assertFalse(values.contains(1));
        assertTrue(values.contains(2));
        assertTrue(values.contains(3));
        assertFalse(values.contains(4));
        map.remove(2);
        values = map.values();
        assertEquals(1, values.size());
        assertFalse(values.contains(1));
        assertFalse(values.contains(2));
        assertTrue(values.contains(3));
        assertFalse(values.contains(4));
        map.clear();
        values = map.values();
        assertEquals(0, values.size());
    }
}
