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

@FunctionalInterface
public interface SingletonSet<E> extends Set<E>, Supplier<E> {

    static <E> SingletonSet<E> of(E e) {
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

    default Spliterator<E> spliterator() {
        return new SingletonSpliterator<>(this);
    }

    default boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return c.stream().allMatch(e -> get().equals(e));
    }

    default Object[] toArray() {
    	return new Object[] { get() };
    }

    default void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        action.accept(get());
    }

    @SuppressWarnings("unchecked")
	default <T> T[] toArray(T[] a) {
    	return (T[]) Arrays.copyOf(new Object[] { get() }, 1, a.getClass());
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
