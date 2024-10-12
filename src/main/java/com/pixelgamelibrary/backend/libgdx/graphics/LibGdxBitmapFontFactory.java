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
package com.pixelgamelibrary.backend.libgdx.graphics;

import com.pixelgamelibrary.api.files.File;
import com.pixelgamelibrary.api.graphics.BitmapFont;
import com.pixelgamelibrary.api.graphics.BitmapFontFactory;
import com.pixelgamelibrary.api.graphics.TextureRegion;


/**
 *
 * @author robertvokac
 */
public class LibGdxBitmapFontFactory implements BitmapFontFactory{

    @Override
    public BitmapFont create(boolean flip) {
        return new LibGdxBitmapFont(flip);
    }

    @Override
    public BitmapFont create(File fontFile, TextureRegion region, boolean flip) {
        return new LibGdxBitmapFont(fontFile, region, flip);
    }

    @Override
    public BitmapFont create(File fontFile, boolean flip) {
        return new LibGdxBitmapFont(fontFile, flip);
    }

    @Override
    public BitmapFont create(File fontFile, File imageFile, boolean flip) {
        return new LibGdxBitmapFont(fontFile, imageFile, flip);
    }

}
