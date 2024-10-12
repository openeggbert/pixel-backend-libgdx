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

import com.badlogic.gdx.utils.ObjectMap;
import com.pixelgamelibrary.api.utils.collections.EntryImpl;
import com.pixelgamelibrary.api.utils.collections.List;
import com.pixelgamelibrary.api.utils.collections.Map;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author robertvokac
 */
public class LibGdxObjectMapImpl<K,V> implements Map<K,V> {
    private final ObjectMap internalMap = new ObjectMap<K,V> ();

    @Override
    public int size() {
        return internalMap.size;
    }

    @Override
    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return internalMap.containsValue(value, true);
    }

    @Override
    public V get(Object key) {
        return (V) internalMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        Object result = internalMap.put(key, value);
        if(result == null) {
            return null;
        }
        return (V) result;
    }

    @Override
    public V remove(Object key) {
        return (V) internalMap.remove(key);
    }

    @Override
    public void putAll(java.util.Map<? extends K, ? extends V> m) {
        ObjectMap<K,V> om = new ObjectMap<>();
        for(K key: m.keySet()) {
            om.put(key, m.get(key));
        }
        internalMap.putAll(om);
    }

    @Override
    public void clear() {
        internalMap.clear();
    }

    @Override
    public Set<K> keySet() {
        var keys = internalMap.keys();
        Set<K> set = new LibGdxObjectSetImpl<>();
        for (Object key : keys) {
            set.add((K) key);
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new LibGdxListImpl<>();
        for(K key:keySet()) {
            values.add(get(key));
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K,V>> set = new LibGdxObjectSetImpl<>();
        for(Object key:internalMap.keys()) {
            K keyK = (K) key;
            set.add(new EntryImpl<>(keyK, get(key)));
            
        }
        return set;
    }


}
