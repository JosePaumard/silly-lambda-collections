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
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import org.paumard.collection.iterator.TwoElementsIterator;
import org.paumard.collection.spliterator.TwoElementsSpliterator;

@FunctionalInterface
public interface TwoElementsSet<E> extends Set<E>, IntFunction<E> {

    static <E> TwoElementsSet<E> of(E e1, E e2) {
    	Objects.requireNonNull(e1);
        Objects.requireNonNull(e2);
        if (e1.equals(e2))
        	throw new IllegalArgumentException("duplicate elements");
        return i -> i == 0 ? e1 : e2;
    }

    default int size() {
        return 2;
    }

    default boolean isEmpty() {
        return false;
    }

    default boolean contains(Object o) {
        return apply(0).equals(o) || apply(1).equals(o);
    }

    default Iterator<E> iterator() {
        return new TwoElementsIterator<>(this);
    }

    default Spliterator<E> spliterator() {
        return new TwoElementsSpliterator<>(this, 
        		Spliterator.CONCURRENT | Spliterator.DISTINCT | Spliterator.IMMUTABLE | 
        		Spliterator.NONNULL | Spliterator.SIZED);
    }

    default Object[] toArray() {
    	return new Object[] { apply(0), apply(1) };
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

    @SuppressWarnings("unchecked")
	default <T> T[] toArray(T[] a) {
    	return (T[]) Arrays.copyOf(new Object[] { apply(0), apply(1) }, 2, a.getClass());
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

    default boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    default boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    default void clear() {
        throw new UnsupportedOperationException();
    }

    default boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }
}
