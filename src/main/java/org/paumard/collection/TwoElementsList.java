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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.paumard.collection.iterator.TwoElementsIterator;
import org.paumard.collection.spliterator.TwoElementsSpliterator;

@FunctionalInterface
public interface TwoElementsList<E> extends List<E>, IntFunction<E> {

    static <E> TwoElementsList<E> of(E e1, E e2) {
        Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        return i -> i == 0 ? e1 : e2;
    }

    default E get(int index) {
        return this.apply(index);
    }

    default int size() {
        return 2;
    }

    default boolean isEmpty() {
        return false;
    }

    default boolean contains(Object o) {
        return get(0).equals(o) || get(1).equals(o);
    }

    default Iterator<E> iterator() {
        return new TwoElementsIterator<>(this);
    }

    default Spliterator<E> spliterator() {
        return new TwoElementsSpliterator<>(this, 
        		Spliterator.CONCURRENT | Spliterator.IMMUTABLE | 
        		Spliterator.NONNULL | Spliterator.SIZED);
    }

    default Object[] toArray() {
        return new Object[] { get(0), get(1) };
    }

    @SuppressWarnings("unchecked")
	default <T> T[] toArray(T[] a) {
    	return (T[]) Arrays.copyOf(new Object[] { get(0), get(1) }, 2, a.getClass());
    }

    default int indexOf(Object o) {
        return get(0).equals(o) ? 0 : get(1).equals(o) ? 1 : -1;
    }

    default int lastIndexOf(Object o) {
        return get(1).equals(o) ? 1 : get(0).equals(o) ? 0 : -1;
    }

    default ListIterator<E> listIterator() {
        return listIterator(0);
    }

    default ListIterator<E> listIterator(final int index) {
        return new ListIterator<E>() {
            private int cursor = index;

            public boolean hasNext() {
                return cursor < 2;
            }

            public E next() {
                return get(cursor++);
            }

            public boolean hasPrevious() {
                return cursor > 0;
            }

            public E previous() {
                return get(--cursor);
            }

            public int nextIndex() {
                return cursor;
            }

            public int previousIndex() {
                return cursor - 1;
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

    default List<E> subList(int fromIndex, int toIndex) {
        Supplier<List<E>> supplier = () -> {throw new IndexOutOfBoundsException();};
        return fromIndex == 0 && toIndex == 0 ? Collections.emptyList() :
               fromIndex == 1 && toIndex == 1 ? Collections.emptyList() :
               fromIndex == 0 && toIndex == 1 ? SingletonList.of(get(0)) :
               fromIndex == 1 && toIndex == 2 ? SingletonList.of(get(1)) :
               fromIndex == 0 && toIndex == 2 ? this : supplier.get();
    }

    default boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return c.stream().allMatch(e -> apply(0).equals(e) || apply(1).equals(e));
    }

    default void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        action.accept(apply(0));
        action.accept(apply(1));
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
}
