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
import com.pixelgamelibrary.api.graphics.ColorMode;
import com.pixelgamelibrary.api.graphics.Pixmap;
import com.pixelgamelibrary.backend.libgdx.utils.LibGdxBackendUtils;

/**
 *
 * @author robertvokac
 */
public class LibGdxTexture implements com.pixelgamelibrary.api.graphics.Texture {

    private com.badlogic.gdx.graphics.Texture internalTexture;
    private boolean disposed = false;

    public LibGdxTexture(com.badlogic.gdx.graphics.Texture internalTextureIn) {
        this.internalTexture = internalTextureIn;
    }

    public LibGdxTexture(String assetPath) {
        this(new com.badlogic.gdx.graphics.Texture(assetPath));
    }

    public LibGdxTexture(File file) {
        this(new com.badlogic.gdx.graphics.Texture(LibGdxBackendUtils.convertToLibGdxFileHandle(file)));
    }

    public LibGdxTexture(Pixmap pixmap) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public LibGdxTexture(int width, int height) {
        this(new com.badlogic.gdx.graphics.Texture(width, height, Format.RGBA8888));
    }

    com.badlogic.gdx.graphics.Texture getInternalTexture() {
        return this.internalTexture;
    }

    @Override
    public void dispose() {
        this.disposed = true;
        this.internalTexture.dispose();
    }

    @Override
    public void draw(Pixmap pixmap, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getWidth() {
        return this.internalTexture.getWidth();
    }

    @Override
    public int getHeight() {
        return this.internalTexture.getHeight();
    }

    @Override
    public int getDepth() {
        return this.internalTexture.getDepth();
    }

    @Override
    public void makeColorTransparent(int r, int g, int b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void scale(double d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setColorMode(ColorMode colorMode, int bitCount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isDisposed() {
        return this.disposed;
    }

}
