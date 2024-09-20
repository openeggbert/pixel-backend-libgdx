///////////////////////////////////////////////////////////////////////////////////////////////
// Pixel: LibGdx Backend.
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
package com.pixelgamelibrary.backend.libgdx.storage;

import com.pixelgamelibrary.api.Pixel;
import com.pixelgamelibrary.api.Platform;
import com.pixelgamelibrary.api.storage.Storage;
import com.pixelgamelibrary.api.storage.StorageException;

/**
 *
 * @author robertvokac
 */
public class StorageFactory {

    private StorageFactory() {
        //Not meant to be instantiated.
    }
    private static Storage storage = null;

    public static Storage getStorage() {
        final Platform platform = Pixel.app().getPlatform();
//        if (storage == null) {
//            storage = new PreferencesStorage();
//        }//todo fixme
        if (storage == null) {
            final String appName = Pixel.app().getAppName();

            if (platform.isDesktop()) {
                storage = new DesktopStorage(appName);
            }
            if (platform.isAndroid()) {
                storage = new AndroidStorage(appName);
            }
            if (platform.isWeb()) {
                storage = new PreferencesStorage(appName);
            }
        }
        if (storage == null) {
            throw new StorageException("Platform is not supported: " + platform);
        }
        return storage;
    }

}
