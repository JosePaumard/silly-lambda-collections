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

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface KeyValueEntry<K, V> extends Map.Entry<K, V>, Function<Boolean, Object> {

    static <K, V> KeyValueEntry<K, V> of(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return b -> b ? key : value;
    }

    @SuppressWarnings("unchecked")
	default K getKey() {
        return (K)apply(true);
    }

    @SuppressWarnings("unchecked")
    default V getValue() {
        return (V)apply(false);
    }

    default V setValue(V value) {
        throw new UnsupportedOperationException();
    }
}
