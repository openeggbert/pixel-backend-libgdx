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
import com.pixelgamelibrary.api.graphics.Texture;
import com.pixelgamelibrary.api.graphics.TextureFactory;

/**
 *
 * @author robertvokac
 */
public class LibGdxTextureFactory implements TextureFactory{


    @Override
    public Texture create(String assetPath) {
        return new LibGdxTexture(assetPath);
    }

    @Override
    public Texture create(File file) {
        return new LibGdxTexture(file);
    }

    @Override
    public Texture create(com.pixelgamelibrary.api.graphics.Pixmap pixmap) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Texture create(int width, int height) {
        return new LibGdxTexture(width, height);
    }
    
    @Override
    public Texture createTransparent(int width, int height) {
                com.badlogic.gdx.graphics.Pixmap pixmap = new com.badlogic.gdx.graphics.Pixmap(width, height, com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();
        
        com.badlogic.gdx.graphics.Texture texture = new com.badlogic.gdx.graphics.Texture(pixmap);
        texture.setFilter(com.badlogic.gdx.graphics.Texture.TextureFilter.Linear, com.badlogic.gdx.graphics.Texture.TextureFilter.Linear);
        return new LibGdxTexture(texture);
        
    }

}
