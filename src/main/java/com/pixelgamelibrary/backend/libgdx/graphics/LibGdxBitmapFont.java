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

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.pixelgamelibrary.api.files.File;
import com.pixelgamelibrary.api.graphics.Color;
import com.pixelgamelibrary.api.graphics.SpriteBatch;
import com.pixelgamelibrary.api.graphics.TextureRegion;
import com.pixelgamelibrary.backend.libgdx.utils.LibGdxBackendUtils;

/**
 *
 * @author robertvokac
 */
public class LibGdxBitmapFont implements com.pixelgamelibrary.api.graphics.BitmapFont {

    private com.badlogic.gdx.graphics.g2d.BitmapFont internalBitmapFont;
    private boolean disposed = false;

    public LibGdxBitmapFont(boolean flip) {
        this.internalBitmapFont = new com.badlogic.gdx.graphics.g2d.BitmapFont(flip);
    }

    public LibGdxBitmapFont(File fontFile, TextureRegion region, boolean flip) {
        throw new UnsupportedOperationException();
        
    }

    public LibGdxBitmapFont(File fontFile, boolean flip) {
        this.internalBitmapFont = new com.badlogic.gdx.graphics.g2d.BitmapFont(LibGdxBackendUtils.convertToLibGdxFileHandle(fontFile), flip);
    }

    public LibGdxBitmapFont(File fontFile, File imageFile, boolean flip) {
        this.internalBitmapFont = new com.badlogic.gdx.graphics.g2d.BitmapFont(
                LibGdxBackendUtils.convertToLibGdxFileHandle(fontFile), 
                LibGdxBackendUtils.convertToLibGdxFileHandle(imageFile), 
                flip);
    }
    
    com.badlogic.gdx.graphics.g2d.BitmapFont getInternalBitmapFont() {
        return this.internalBitmapFont;
    }

    @Override
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override
    public void setScale(float f) {
        this.internalBitmapFont.getData().setScale(f);
    }

    @Override
    public void setColor(Color color) {
        this.internalBitmapFont.setColor(LibGdxBackendUtils.convertToLibGdxColor(color));
    }

    @Override
    public void draw(SpriteBatch batch, String text, float x, float y) {
        LibGdxSpriteBatch libGdxSpriteBatch = (LibGdxSpriteBatch) batch;
        this.internalBitmapFont.draw(libGdxSpriteBatch.getInternalBatch(), text, x, y);
    }

    @Override
    public void dispose() {
        this.internalBitmapFont.dispose();
    }

}
