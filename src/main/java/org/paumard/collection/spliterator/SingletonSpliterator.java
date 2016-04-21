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
import java.util.function.Supplier;

public class SingletonSpliterator<E> implements Spliterator<E> {

    private final Supplier<E> supplier;
    private boolean done = false;

    public SingletonSpliterator(Supplier<E> supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean tryAdvance(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        if (done)
            return false;
        done = true;
        action.accept(supplier.get());
        return true;
    }

    @Override
    public Spliterator<E> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return done ? 0 : 1;
    }

    @Override
    public int characteristics() {
        return Spliterator.CONCURRENT |
                Spliterator.IMMUTABLE | Spliterator.NONNULL |
                Spliterator.DISTINCT | Spliterator.SIZED | Spliterator.SORTED;
    }
}
