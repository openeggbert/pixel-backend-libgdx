///////////////////////////////////////////////////////////////////////////////////////////////
// Pixel: LibGDX Backend.
// Copyright (C) 2024 the original author or authors.
//
// This program is free software: you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation, either version 3
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see
// <https://www.gnu.org/licenses/> or write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////
package com.pixelgamelibrary.backend.libgdx.utils.collections;

import com.badlogic.gdx.utils.ObjectSet;
import com.pixelgamelibrary.api.utils.collections.PixelCollection;
import com.pixelgamelibrary.api.utils.collections.Set;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author robertvokac
 */
public class LibGdxObjectSetImpl<T> extends PixelCollection<T> implements Set<T> {
    private final ObjectSet internalSet = new ObjectSet<T> ();

    @Override
    public int size() {
        return internalSet.size;
    }

    @Override
    public boolean isEmpty() {
        return internalSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return internalSet.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return internalSet.iterator();
    }

    @Override
    public boolean add(T e) {
        return internalSet.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return internalSet.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return internalSet.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        internalSet.clear();
    }


}
