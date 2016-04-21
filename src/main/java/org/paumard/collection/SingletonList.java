/*
 * Copyright (C) 2016 José Paumard
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.paumard.collection;

import org.paumard.collection.iterator.SingletonIterator;
import org.paumard.collection.spliterator.SingletonSpliterator;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@FunctionalInterface
public interface SingletonList<E> extends List<E>, Supplier<E> {

    default Supplier<E> indexOutOfBoundsException() { 
        return () -> { throw new IndexOutOfBoundsException(); };
    }
    
    default Supplier<E> noSuchElementException() {
    	return () -> { throw new NoSuchElementException(); };
    }
    
    static <E> SingletonList<E> of(E e) {
        Objects.requireNonNull(e);
        return () -> e;
    }

    default int size() {
        return 1;
    }

    default boolean isEmpty() {
        return false;
    }

    default boolean contains(Object o) {
        return get().equals(o);
    }

    default Iterator<E> iterator() {
        return new SingletonIterator<>(this);
    }

    default Object[] toArray() {
    	return new Object[] { get() };
    }

    @SuppressWarnings("unchecked")
	default <T> T[] toArray(T[] a) {
    	return (T[]) Arrays.copyOf(new Object[] { get() }, 1, a.getClass());
    }

    default E get(int index) {
        return (index == 0 ? this : indexOutOfBoundsException()).get();
    }

    default int indexOf(Object o) {
        return get().equals(o) ? 0 : -1;
    }

    default int lastIndexOf(Object o) {
        return indexOf(o);
    }

    default ListIterator<E> listIterator(int index) {
        return new ListIterator<E>() {
            private boolean done = false;

            public boolean hasNext() {
                return !done;
            }

            public E next() {
                E result = (done ? noSuchElementException() : SingletonList.this).get();
                done = true;
                return result;
            }

            public boolean hasPrevious() {
                return done;
            }

            public E previous() {
                E result = (!done ? noSuchElementException() : SingletonList.this).get();
                done = false;
                return result;
            }

            public int nextIndex() {
                return done ? 1 : 0;
            }

            public int previousIndex() {
                return done ? 0 : 1;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void set(E e) {
                throw new UnsupportedOperationException();
            }

            public void add(E e) {
                throw new UnsupportedOperationException();
            }
        };
    }

    default Spliterator<E> spliterator() {
        return new SingletonSpliterator<>(this);
    }

    default ListIterator<E> listIterator() {
        return listIterator(0);
    }

    default List<E> subList(int fromIndex, int toIndex) {
        Supplier<List<E>> supplier = () -> {throw new IndexOutOfBoundsException();};
        return fromIndex == 0 ?
                toIndex == 0 ? Collections.emptyList() :
                toIndex == 1 ? this : supplier.get() : supplier.get();
    }

    default boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return c.stream().allMatch(e -> get().equals(e));
    }

    default void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        action.accept(get());
    }

    default boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    default boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    default boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    default boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    default boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    default boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    default void clear() {
        throw new UnsupportedOperationException();
    }

    default E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    default void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    default E remove(int index) {
        throw new UnsupportedOperationException();
    }

    default void sort(Comparator<? super E> comparator) {
        throw new UnsupportedOperationException();
    }

    default boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    default void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }
}
