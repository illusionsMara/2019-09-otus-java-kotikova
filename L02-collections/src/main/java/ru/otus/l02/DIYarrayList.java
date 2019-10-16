package ru.otus.l02;

import java.util.*;
import java.util.function.UnaryOperator;

public class DIYarrayList<T> implements List<T> {

    private static final int DEFAULT_VOLUME = 10;
    private static final float MAGNIFICATION_FACTOR = 1.5f;
    private int volume;
    private int size;
    private T[] array;

    public DIYarrayList() {
        this.volume = DEFAULT_VOLUME;
        this.array = (T[])new Object[volume];
    }

    public DIYarrayList(int volume) {
        this.volume = volume;
        this.array = (T[])new Object[volume];
    }

    @Override
    public boolean add( T t ) {
        if(isTheArrayFull()) {
            expandArray();
        }
        array[size++] = t;
        return true;
    }

    private boolean isTheArrayFull() {
        return size == volume;
    }

    private void expandArray() {
        int newVolume = (int)(MAGNIFICATION_FACTOR * volume);
        T[] newArray = (T[])new Object[newVolume];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        volume = newVolume;
    }

    @Override
    public void add( int index, T element ) {
        if(isIndexOutOfBound( index )) {
            throw new IndexOutOfBoundsException();
        }
        if(isTheArrayFull()) {
            expandArrayFromIndex( index );
        } else {
            shiftTheseElementsToTheRight( index, size );
        }
        array[index] = element;
        size++;
    }

    private boolean isIndexOutOfBound(int index) {
        return index < 0 || index >= size;
    }

    private void expandArrayFromIndex( int index) {
        int newVolume = (int)(MAGNIFICATION_FACTOR * volume);
        T[] newArray = (T[])new Object[newVolume];
        for(int i = 0; i < size; i++) {
            if(i < index) {
                newArray[i] = array[i];
            } else {
                newArray[i+1] = array[i];
            }
        }
        array = newArray;
        volume = newVolume;
    }

    private void shiftTheseElementsToTheRight(int beginIndex, int endIndex) {
        for(int i = endIndex; i > beginIndex; i--) {
            array[i] = array[i-1];
        }
    }

    @Override
    public T get( int index ) {
        if(isIndexOutOfBound( index )) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public T set( int index, T element ) {
        if(isIndexOutOfBound( index )) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
        return element;
    }

    @Override
    public T remove( int index ) {
        if(isIndexOutOfBound( index )) {
            throw new IndexOutOfBoundsException();
        }
        T itemToBeRemoved = array[index];
        shiftTheseElementsToTheLeft( index + 1, size );
        array[--size] = null;
        return itemToBeRemoved;
    }

    @Override
    public boolean remove( Object o ) {
        int indexOfRemovedItem = indexOf( o );
        if(indexOfRemovedItem == -1) {
            return false;
        }
        shiftTheseElementsToTheLeft( indexOfRemovedItem + 1, size );
        array[--size] = null;
        return true;
    }

    private void shiftTheseElementsToTheLeft(int beginIndex, int endIndex) {
        for(int i = beginIndex; i < endIndex; i++) {
            array[i-1] = array[i];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf( Object o ) {
        for(int i = 0; i < size; i++ ) {
            if( o == null ? array[i] == null : ((T)o).equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains( Object o ) {
        return indexOf( o ) >= 0;
    }

    @Override
    public void clear() {
        array = (T[])new Object[volume];
        size = 0;
    }

    @Override
    public List<T> subList( int fromIndex, int toIndex ) {
        if(fromIndex == toIndex) {
            return Collections.EMPTY_LIST;
        } else {
            int newVolume = toIndex - fromIndex;
            List newList = new DIYarrayList( newVolume );
            for(int i = fromIndex; i < toIndex; i++) {
                newList.add( array[i] );
            }
            return newList;
        }
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public void sort( Comparator<? super T> c ) {
        if( size > 1 ) {
            Arrays.sort(array, 0, size, c);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DIYarrayIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DIYarrayListIterator( 0 );
    }

    @Override
    public ListIterator<T> listIterator( int index ) {
        if(isIndexOutOfBound( index )) {
            throw new IndexOutOfBoundsException();
        }
        return new DIYarrayListIterator( index );
    }

    private class DIYarrayListIterator extends DIYarrayIterator implements ListIterator<T> {
        DIYarrayListIterator(int index) {
            super();
            pointer = index;
        }

        @Override
        public void set( T t ) {
            DIYarrayList.this.set( pointerToPreviousItem, t );
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add( T t ) {
            throw new UnsupportedOperationException();
        }
    }

    private class DIYarrayIterator implements Iterator<T> {
        int pointer;
        int pointerToPreviousItem = -1;

        @Override
        public boolean hasNext() {
            return pointer < size;
        }

        @Override
        public T next() {
            if( hasNext() ) {
                pointerToPreviousItem = pointer;
                return array[pointer++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public <T1> T1[] toArray( T1[] a ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll( Collection<?> c ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll( Collection<? extends T> c ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll( int index, Collection<? extends T> c ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll( Collection<?> c ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll( Collection<?> c ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll( UnaryOperator<T> operator ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf( Object o ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException();
    }
}
