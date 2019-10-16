package ru.otus.l02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DIYarrayListTest {
    private DIYarrayList<Integer> diYarrayList;
    private Integer item = 1000;
    private int index = 12;

    private DIYarrayList<Integer> fillList() {
        DIYarrayList<Integer> diYarrayList = new DIYarrayList();
        for (int i = 0; i < 25; i++) {
            Integer element = (int)(Math.random() * 100);
            diYarrayList.add(element);
        }
        return diYarrayList;
    }

    @BeforeEach
    private void beforeEach() {
        diYarrayList = fillList();
    }

    @AfterEach
    private void afterEach() {
        diYarrayList = null;
    }

    @Test
    @DisplayName("add( T t )")
    public void addTest() {
        diYarrayList.add(item);
        int actualIndex = diYarrayList.size() - 1;
        assertEquals(item, diYarrayList.get(actualIndex));
    }

    @Test
    @DisplayName("add( int index, T element )")
    public void addToIndexTest() {
        diYarrayList.add(index, item);
        assertEquals(item, diYarrayList.get(index));
    }

    @Test
    @DisplayName("T set( int index, T element )")
    public void getTest() {
        diYarrayList.set(index, item);
        assertEquals(item, diYarrayList.get(index));
    }

    @Test
    @DisplayName("T remove( int index )")
    public void removeByIndexTest() {
        Integer expected = diYarrayList.get(index + 1);
        diYarrayList.remove(index);
        assertEquals(expected, diYarrayList.get(index));
    }

    @Test
    @DisplayName("remove( Object o )")
    public void removeObjectTest() {
        diYarrayList.add(item);
        diYarrayList.remove(item);
        for(Integer element: diYarrayList) {
            assertFalse(item.equals(element));
        }
    }

    @Test
    @DisplayName("indexOf( Object o )")
    public void indexOfTest() {
        diYarrayList.add(index, item);
        int actual = diYarrayList.indexOf(item);
        assertEquals(index, actual);
    }

    @Test
    @DisplayName("contains( Object o )")
    public void containsTest() {
        diYarrayList.add(item);
        assertTrue(diYarrayList.contains(item));
    }

    @Test
    @DisplayName("List<T> subList( int fromIndex, int toIndex )")
    public void subListTest() {
        int fromIndex = index;
        int toIndex = index + 5;
        List<Integer> subList = diYarrayList.subList(fromIndex, toIndex);
        for(int i = 0; i < subList.size(); i++) {
            assertEquals(diYarrayList.get(fromIndex + i), subList.get(i));
        }
    }

    @Test
    @DisplayName("Collections.addAll(Collection<? super T> c, T... elements)")
    public void addAllTest() {
        Collections.addAll( diYarrayList, item, item, item );
        for(int i = diYarrayList.size()-1; i <= 3; i--) {
            assertEquals(item, diYarrayList.get(i));
        }
    }

    @Test
    @DisplayName("Collections.copy(List<? super T> dest, List<? extends T> src)")
    public void copyTest() {
        DIYarrayList<Integer> dest = new DIYarrayList<>();
        for(int i = 0; i < diYarrayList.size(); i++) {
            dest.add(null);
        }
        Collections.copy(dest, diYarrayList);
        for(int i = 0; i < dest.size(); i++) {
            assertEquals(diYarrayList.get(i), dest.get(i));
        }
    }

    @Test
    @DisplayName("Collections.sort(List<T> list, Comparator<? super T> c)")
    public void sortTest() {
        Collections.sort(diYarrayList, Integer::compareTo);
        Integer pred = diYarrayList.get(0);
        Integer next;
        for(int i = 1; i < diYarrayList.size(); i++) {
            next = diYarrayList.get(i);
            assertTrue(next.compareTo(pred) >= 0);
            pred = next;
        }
    }
}