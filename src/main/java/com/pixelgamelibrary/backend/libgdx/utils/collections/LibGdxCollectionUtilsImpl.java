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

import com.pixelgamelibrary.api.utils.CollectionUtils;
import com.pixelgamelibrary.api.utils.collections.List;
import com.pixelgamelibrary.api.utils.collections.Map;
import com.pixelgamelibrary.api.utils.collections.Set;

/**
 *
 * @author robertvokac
 */
public class LibGdxCollectionUtilsImpl implements CollectionUtils {
    
    @Override
    public <K, V> Map<K, V> objectMap() {
        return new LibGdxObjectMapImpl<>();
    }

    @Override
    public <T> Set<T> objectSet() {
        return new LibGdxObjectSetImpl<>();
    }

    @Override
    public <T> List<T> list() {
        return new LibGdxListImpl<>();
    }

}
