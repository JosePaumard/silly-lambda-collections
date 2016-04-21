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

package org.paumard.map;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

import org.paumard.collection.TwoElementsSet;

@FunctionalInterface
public interface TwoElementsMap<K, V> extends Map<K, V>, IntFunction<Map.Entry<K, V>> {

    static <K, V> TwoElementsMap<K, V> of(K key1, V value1, K key2, V value2) {
        Objects.requireNonNull(key1);
        Objects.requireNonNull(value1);
        Objects.requireNonNull(key2);
        Objects.requireNonNull(value2);
        if (key1.equals(key2))
        	throw new IllegalArgumentException("duplicate keys");
        return i -> i == 0 ? KeyValueEntry.of(key1, value1) : KeyValueEntry.of(key2, value2);
    }

    default Set<Entry<K, V>> entrySet() {
        return TwoElementsSet.of(apply(0), apply(1));
    }


    default V get(Object key) {
        Map.Entry<K, V> entry0 = apply(0);
        Map.Entry<K, V> entry1 = apply(1);
		return entry0.equals(key) ? entry0.getValue() :
			   entry1.equals(key) ? entry1.getValue() :
                null;
    }

    default int size() {
        return 2;
    }

    default boolean isEmpty() {
        return false;
    }

    default boolean containsKey(Object key) {
        Objects.requireNonNull(key);
        return keySet().contains(key);
    }

    default boolean containsValue(Object value) {
        Objects.requireNonNull(value);
        return values().contains(value);
    }

    default void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        Map.Entry<K, V> entry0 = apply(0);
		action.accept(entry0.getKey(), entry0.getValue());
        Map.Entry<K, V> entry1 = apply(1);
		action.accept(entry1.getKey(), entry1.getValue());
    }

    default Set<K> keySet() {
        return TwoElementsSet.of(apply(0).getKey(), apply(1).getKey());
    }

    default Collection<V> values() {
        return TwoElementsSet.of(apply(0).getValue(), apply(1).getValue());
    }

    default V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    default V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    default void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    default void clear() {
        throw new UnsupportedOperationException();
    }

    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        throw new UnsupportedOperationException();
    }

    default V putIfAbsent(K key, V value) {
        throw new UnsupportedOperationException();
    }

    default boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    default boolean replace(K key, V oldValue, V newValue) {
        throw new UnsupportedOperationException();
    }

    default V replace(K key, V value) {
        throw new UnsupportedOperationException();
    }

    default V computeIfAbsent(K key,
                              Function<? super K, ? extends V> mappingFunction) {
        throw new UnsupportedOperationException();
    }

    default V computeIfPresent(K key,
                               BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    default V compute(K key,
                      BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    default V merge(K key, V value,
                    BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }
}
