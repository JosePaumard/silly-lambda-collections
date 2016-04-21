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

package org.paumard.collection.spliterator;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public class TwoElementsSpliterator<E> implements Spliterator<E> {

    private final IntFunction<E> function;
    private int index = 0;
	private int characteristics;

    public TwoElementsSpliterator(IntFunction<E> function, int characteristics) {
        this.function = function;
        this.characteristics = characteristics;
    }

    @Override
    public boolean tryAdvance(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        if (index == 2)
            return false;

        action.accept(function.apply(index++));
        return true;
    }

    @Override
    public Spliterator<E> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 2 - index;
    }

    @Override
    public int characteristics() {
        return this.characteristics;
    }
}
