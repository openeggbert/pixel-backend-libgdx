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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pixelgamelibrary.api.graphics.Color;
import com.pixelgamelibrary.api.graphics.Texture;
import com.pixelgamelibrary.api.math.Angle;
import com.pixelgamelibrary.backend.libgdx.utils.LibGdxBackendUtils;

/**
 *
 * @author robertvokac
 */
public class LibGdxShapeRenderer implements com.pixelgamelibrary.api.graphics.ShapeRenderer {

    private space.earlygrey.shapedrawer.ShapeDrawer internalShapeRenderer;
    private boolean disposed = false;

    public LibGdxShapeRenderer(SpriteBatch spriteBatchIn) {
        this(new space.earlygrey.shapedrawer.ShapeDrawer(spriteBatchIn, new TextureRegion()));
    }

    public LibGdxShapeRenderer(space.earlygrey.shapedrawer.ShapeDrawer internalShapeRendererIn) {
        this.internalShapeRenderer = internalShapeRendererIn;
    }
    space.earlygrey.shapedrawer.ShapeDrawer getShapeRenderer() {
        return this.internalShapeRenderer;
    }
 
    @Override
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override
    public void dispose() {
        this.disposed = true;
    }

    @Override
    public void setColor(Color colorIn) {
        this.color = colorIn;
        internalShapeRenderer.setColor(LibGdxBackendUtils.convertToLibGdxColor(color));
    }
    public Color getColor() {
        return color;
    }
    private Color color = Color.BLACK;

    @Override
    public void filledRectangle(float x, float y, float width, float height, Angle rotation, Color color) {
        float anticlockwiseRadians = rotation == null || rotation.asDegrees() == 0f ? 0f : Angle.ofDegrees(360f - rotation.asDegrees()).asGradians();
        internalShapeRenderer.filledRectangle(x, y, width, height, anticlockwiseRadians);
    }

    @Override
    public void setTextureRegion(com.pixelgamelibrary.api.graphics.TextureRegion textureRegion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public com.pixelgamelibrary.api.graphics.TextureRegion getTextureRegion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setTexture(Texture texture) {
        com.badlogic.gdx.graphics.Texture gdxTexture = ((LibGdxTexture)texture).getInternalTexture();
        
        internalShapeRenderer.setTextureRegion(new com.badlogic.gdx.graphics.g2d.TextureRegion(gdxTexture));
    }

}
