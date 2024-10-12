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

import com.pixelgamelibrary.api.graphics.ShapeRenderer;

/**
 *
 * @author robertvokac
 */
public class LibGdxSpriteBatch implements com.pixelgamelibrary.api.graphics.SpriteBatch {

    private com.badlogic.gdx.graphics.g2d.SpriteBatch internalBatch;
    private boolean disposed = false;
    private ShapeRenderer shapeRenderer = null;

    public LibGdxSpriteBatch() {
        this(new com.badlogic.gdx.graphics.g2d.SpriteBatch());
    }

    public LibGdxSpriteBatch(com.badlogic.gdx.graphics.g2d.SpriteBatch libGdxG2dSpriteBatchIn) {
        this.internalBatch = libGdxG2dSpriteBatchIn;
    }
    com.badlogic.gdx.graphics.g2d.SpriteBatch getInternalBatch() {
        return this.internalBatch;
    }

    @Override
    public void begin() {
        this.internalBatch.begin();
    }

    @Override
    public void end() {
        this.internalBatch.end();
    }

    @Override
    public void draw(com.pixelgamelibrary.api.graphics.Texture texture, int x, int y, int width, int height) {
        LibGdxTexture libGdxTexture = (LibGdxTexture) texture;
        this.internalBatch.draw(libGdxTexture.getInternalTexture(), x, y, width, height);
    }

    @Override
    public void draw(com.pixelgamelibrary.api.graphics.Texture texture, int x, int y) {
        LibGdxTexture libGdxTexture = (LibGdxTexture) texture;
        this.internalBatch.draw(libGdxTexture.getInternalTexture(), x, y);
    }
    

    @Override
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override
    public void dispose() {
        this.disposed = true;
        this.internalBatch.dispose();
    }

    @Override
    public ShapeRenderer drawShape() {
        if(shapeRenderer == null) {
            shapeRenderer = new LibGdxShapeRenderer(internalBatch);
        }
        return shapeRenderer;
    }

}
