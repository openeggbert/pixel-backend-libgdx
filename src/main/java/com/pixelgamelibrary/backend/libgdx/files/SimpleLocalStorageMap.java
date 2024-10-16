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
package com.pixelgamelibrary.backend.libgdx.files;

import com.badlogic.gdx.Preferences;
import com.pixelgamelibrary.api.files.map.SimpleMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author robertvokac
 */
public class SimpleLocalStorageMap implements SimpleMap {

    private final Preferences preferences;

    public SimpleLocalStorageMap(Preferences preferencesIn) {
        this.preferences = preferencesIn;
    }

    @Override
    public void putString(String key, String val) {
        preferences.putString(key, val);
    }

    @Override
    public void put(Map<String, String> map) {
        preferences.put(map);
    }

    @Override
    public String getString(String key) {
        return preferences.getString(key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    @Override
    public Map<String, String> getReadOnlyMap() {
        Map<String, ?> map = preferences.get();
        Map<String, String> result = new HashMap<>();
        for (String key : map.keySet()) {
            Object o = map.get(key);
            if (o instanceof String) {
                result.put(key, (String) o);
            }
        }
        return Collections.unmodifiableMap(result);
    }

    @Override
    public boolean contains(String key) {
        return preferences.contains(key);
    }

    @Override
    public void clear() {
        preferences.clear();
    }

    @Override
    public void remove(String key) {
        preferences.remove(key);
    }

    @Override
    public void flush() {
        preferences.flush();
    }

    @Override
    public List<String> keyList() {
        return preferences.get().keySet().stream().collect(Collectors.toList());
    }

}
