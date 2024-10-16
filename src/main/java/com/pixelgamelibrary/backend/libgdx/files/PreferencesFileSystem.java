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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.pixelgamelibrary.api.Platform;
import com.pixelgamelibrary.api.files.map.MapFileSystem;

/**
 *
 * @author robertvokac
 */
public class PreferencesFileSystem extends MapFileSystem {

    
    public Platform getPlatform() {
        return Platform.WEB;
    }

    public PreferencesFileSystem() {
        this("com.pixelgamelibrary.backend.libgdx.files.PreferencesFileSystem");
    }

    public PreferencesFileSystem(String preferencesName) {
        this(Gdx.app.getPreferences(preferencesName));
    }

    public PreferencesFileSystem(Preferences preferences) {
        super(new SimpleLocalStorageMap(preferences));
    }

}
