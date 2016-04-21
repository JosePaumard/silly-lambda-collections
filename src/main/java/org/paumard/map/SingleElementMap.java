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
import java.util.function.Supplier;

import org.paumard.collection.SingletonList;
import org.paumard.collection.SingletonSet;

@FunctionalInterface
public interface SingleElementMap<K, V> extends Map<K, V>, Supplier<Map.Entry<K, V>> {

    static <K, V> SingleElementMap<K, V> of (K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return () -> KeyValueEntry.of(key, value);
    }

    default Set<Entry<K, V>> entrySet() {
        return SingletonSet.of(get());
    }


    default V get(Object key) {
        Map.Entry<K, V> entry = get();
		return entry.equals(key) ? entry.getValue() : null;
    }

    default int size() {
        return 1;
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
        Map.Entry<K, V> entry = get();
		action.accept(entry.getKey(), entry.getValue());
    }

    default Set<K> keySet() {
        return SingletonSet.of(get().getKey());
    }

    default Collection<V> values() {
        return SingletonList.of(get().getValue());
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
