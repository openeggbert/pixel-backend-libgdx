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

import com.badlogic.gdx.utils.Array;
import com.pixelgamelibrary.api.utils.collections.List;
import com.pixelgamelibrary.api.utils.collections.PixelCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author robertvokac
 */
public class LibGdxListImpl<T> extends PixelCollection<T> implements List<T> {
    private final Array<T> internalArray = new Array<> ();

    @Override
    public int size() {
        return internalArray.size;
    }

    @Override
    public boolean isEmpty() {
        return internalArray.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return internalArray.contains((T) o, true);
    }

    @Override
    public Iterator<T> iterator() {
        return internalArray.iterator();
    }

    @Override
    public boolean add(T e) {
        internalArray.add(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return internalArray.removeValue((T) o, true);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        internalArray.clear();
    }

    @Override
    public T get(int index) {
        return internalArray.get(index);
    }

    @Override
    public T set(int index, T element) {
        internalArray.set(index, element);
        return element;
    }

    @Override
    public void add(int index, T element) {
        set(index, element);
    }

    @Override
    public T remove(int index) {
        return internalArray.removeIndex(index);
    }

    @Override
    public int indexOf(Object o) {
        return internalArray.indexOf((T) o, true);
    }

    @Override
    public int lastIndexOf(Object o) {
        return internalArray.lastIndexOf((T) o, true);
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public java.util.List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
