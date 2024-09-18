///////////////////////////////////////////////////////////////////////////////////////////////
// Pixel: Game library.
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

import com.pixelgamelibrary.api.interfaces.PixelBackend;
import com.pixelgamelibrary.api.interfaces.Files;
import com.pixelgamelibrary.api.interfaces.Audio;
import com.pixelgamelibrary.api.interfaces.Graphics;
import com.pixelgamelibrary.api.interfaces.Input;
import com.pixelgamelibrary.api.interfaces.Internal;
import com.pixelgamelibrary.api.interfaces.Net;
import com.pixelgamelibrary.api.interfaces.Utils;
import com.pixelgamelibrary.api.interfaces.App;

/**
 *
 * @author robertvokac
 */
public class PixelBackendLibGDX implements PixelBackend {

    private App pixelAppLibGdxImpl = null;
    private Graphics pixelGraphicsLibGdxImpl = null;
    private Audio pixelAudioLibGdxImpl = null;
    private Input pixelInputLibGdxImpl = null;
    private Net pixelNetLibGdxImpl = null;
    private Files pixelFilesLibGdxImpl = null;
    private Utils pixelUtilsLibGdxImpl = null;
    private Internal pixelInternalLibGdxImpl = null;
    

    @Override
    public Graphics graphics() {
        if (pixelGraphicsLibGdxImpl == null) {
            pixelGraphicsLibGdxImpl = new GraphicsLibGDXImpl();
        }
        return pixelGraphicsLibGdxImpl;
    }

    @Override
    public Audio audio() {

        if (pixelAudioLibGdxImpl == null) {
            pixelAudioLibGdxImpl = new AudioLibGDXImpl();
        }
        return pixelAudioLibGdxImpl;
    }

    @Override
    public Input input() {

        if (pixelInputLibGdxImpl == null) {
            pixelInputLibGdxImpl = new InputLibGDXImpl();
        }
        return pixelInputLibGdxImpl;
    }

    @Override
    public Net net() {

        if (pixelNetLibGdxImpl == null) {
            pixelNetLibGdxImpl = new NetLibGDXImpl();
        }
        return pixelNetLibGdxImpl;
    }

    @Override
    public Files files() {

        if (pixelFilesLibGdxImpl == null) {
            pixelFilesLibGdxImpl = new FilesLibGDXImpl();
        }
        return pixelFilesLibGdxImpl;
    }

    @Override
    public Utils utils() {

        if (pixelUtilsLibGdxImpl == null) {
            pixelUtilsLibGdxImpl = new UtilsLibGDXImpl(app());
        }
        return pixelUtilsLibGdxImpl;
    }

    @Override
    public App app() {
        if (pixelAppLibGdxImpl == null) {
            pixelAppLibGdxImpl = new AppLibGDXImpl();
        }
        return pixelAppLibGdxImpl;
    }

    @Override
    public Internal internal() {

        if (pixelInternalLibGdxImpl == null) {
            pixelInternalLibGdxImpl = new InternalLibGDXImpl();
        }
        return pixelInternalLibGdxImpl;
    }

}
