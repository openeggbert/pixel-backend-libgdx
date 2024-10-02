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
package com.pixelgamelibrary.backend.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.pixelgamelibrary.api.PixelException;
import com.pixelgamelibrary.api.graphics.Cursor;
import com.pixelgamelibrary.api.graphics.Monitor;
import com.pixelgamelibrary.api.graphics.Pixmap;
import com.pixelgamelibrary.backend.libgdx.graphics.LibGdxMonitor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author robertvokac
 */
public class GraphicsLibGDXImpl implements com.pixelgamelibrary.api.interfaces.Graphics {

    @Override
    public List<Monitor> getMonitors() {
        List<Monitor> result = new ArrayList<>();
        for(Graphics.Monitor m:Gdx.graphics.getMonitors()) {
            result.add(new LibGdxMonitor(m));
        }
        return result;
    }

    @Override
    public Monitor getMonitor() {
        return new LibGdxMonitor(Gdx.graphics.getMonitor());
    }

    @Override
    public Monitor getPrimaryMonitor() {
        return new LibGdxMonitor(Gdx.graphics.getPrimaryMonitor());
    }

    @Override
    public String getTitle() {
        throw new PixelException("Unsupported operation");
    }

    /**
     *
     * @param title
     */
    @Override
    public void setTitle(String title) {
        Gdx.graphics.setTitle(title);
    }

    @Override
    public Cursor newCursor(Pixmap pixMap, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCursor(Cursor cursor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float getDeltaTime() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setTargetFPS() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getTargetFPS() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
