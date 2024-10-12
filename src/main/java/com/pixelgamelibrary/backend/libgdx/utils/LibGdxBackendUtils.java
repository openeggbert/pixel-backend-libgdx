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
package com.pixelgamelibrary.backend.libgdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.pixelgamelibrary.api.Pixel;
import com.pixelgamelibrary.api.files.File;
import com.pixelgamelibrary.api.files.FileException;
import com.pixelgamelibrary.api.files.FileSystemType;

/**
 *
 * @author robertvokac
 */
public class LibGdxBackendUtils {

    private LibGdxBackendUtils() {
        //Not meant to be instantiated.
    }

    public static FileHandle convertToLibGdxFileHandle(File file) {
        boolean android = Pixel.app().getPlatform().isAndroid();
        boolean web = Pixel.app().getPlatform().isWeb();

        FileSystemType fileSystemType = file.getFileSystem().getFileSystemType();
        switch (fileSystemType) {
            case ASSETS:
                return android || web ? Gdx.files.internal(file.path()) : Gdx.files.classpath(file.path().startsWith("/") ? file.path().substring(1) : file.path());
            case LOCAL:
                return Gdx.files.local(file.path());
            case EXTERNAL:
                return Gdx.files.external(file.path());
            case RELATIVE:
                return Gdx.files.local(file.path());
            case ABSOLUTE:
                return Gdx.files.absolute(file.path());
            case TMP:
                throw new FileException("Unsupported FileSystemType: " + fileSystemType);
            case UNDEFINED:
                throw new FileException("Unsupported FileSystemType: " + fileSystemType);
            default:
                throw new FileException("Unsupported FileSystemType: " + fileSystemType);
        }

    }
    public static com.badlogic.gdx.graphics.Color convertToLibGdxColor(com.pixelgamelibrary.api.graphics.Color color) {
        return new com.badlogic.gdx.graphics.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    public static com.pixelgamelibrary.api.graphics.Color convertToPixelColor(com.badlogic.gdx.graphics.Color color) {
        return new com.pixelgamelibrary.api.graphics.Color(color.r, color.g, color.b, color.a);
    }
}
