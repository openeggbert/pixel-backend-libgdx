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
package com.pixelgamelibrary.backend.libgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.pixelgamelibrary.api.PixelException;
import com.pixelgamelibrary.api.ViewMode;
import static com.pixelgamelibrary.api.ViewMode.WINDOW;
import com.pixelgamelibrary.api.graphics.Monitor;
import com.pixelgamelibrary.api.graphics.MonitorMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author robertvokac
 */
public class LibGdxMonitor implements Monitor {

    private final Graphics.Monitor internalMonitor;

    public LibGdxMonitor(Graphics.Monitor internalMonitorIn) {
        this.internalMonitor = internalMonitorIn;
    }

    @Override
    public String getName() {
        return internalMonitor.name;
    }

    @Override
    public int getVirtualWidth() {
        return internalMonitor.virtualX;
    }

    @Override
    public int getVirtualHeight() {
        return internalMonitor.virtualY;
    }

    @Override
    public MonitorMode getMonitorMode() {
        Graphics.DisplayMode displayModeFromLibGdx = Gdx.graphics.getDisplayMode(internalMonitor);
        return toMonitorMode(displayModeFromLibGdx);
    }

    private MonitorMode toMonitorMode(Graphics.DisplayMode displayModeFromLibGdx) {
        return new MonitorMode(
                displayModeFromLibGdx.width,
                displayModeFromLibGdx.height,
                displayModeFromLibGdx.refreshRate,
                displayModeFromLibGdx.bitsPerPixel);
    }

    @Override
    public List<MonitorMode> listSupportedMonitorModes() {
        Graphics.DisplayMode[] displayModesFromLibGdx = Gdx.graphics.getDisplayModes(internalMonitor);
        List<MonitorMode> result = new ArrayList<>();
        for (Graphics.DisplayMode mode : displayModesFromLibGdx) {
            result.add(toMonitorMode(mode));
        }
        return result;
    }

    @Override
    public boolean setMonitorMode(MonitorMode monitorMode) {
        Optional<Graphics.DisplayMode> libGdxDisplayModes = Arrays
                .asList(Gdx.graphics.getDisplayModes(internalMonitor))
                .stream()
                .filter(m
                        -> m.width == monitorMode.getWidth()
                && m.height == monitorMode.getHeight()
                && m.refreshRate == monitorMode.getRefreshRate()
                && m.bitsPerPixel == monitorMode.getBitsPerPixel()
                )
                .findFirst();
        if (libGdxDisplayModes.isEmpty()) {
            throw new PixelException("Unsupported MonitorMode: " + monitorMode.toString());
        }
        boolean success = Gdx.graphics.setFullscreenMode(libGdxDisplayModes.get());
        return success;
    }

    @Override
    public boolean setToOriginalMonitorMode() {
        if (originalLibGdxDisplayMode == null) {
            //nothing to do
            return true;
        }
        if (!Gdx.graphics.setFullscreenMode(originalLibGdxDisplayMode)) {
            Gdx.app.error("DefinitiveFrameworkLibGdxImpl", "Switching to original display mode failed.");
            return false;
        } else {
            return true;
        }

    }
    private Graphics.DisplayMode originalLibGdxDisplayMode = null;

    @Override
    public void setViewMode(ViewMode viewMode, int width, int height) {
        if (!isMonitorInUse() && !isFullscreen()) {
            return;
        }

        switch (viewMode) {

            case WINDOW: {
                if (width == 0) {
                    width = 640;
                }
                if (height == 0) {
                    height = 480;
                }

                Gdx.graphics.setWindowedMode(width, height);
            }
            break;
            case FULLSCREEN:
                Gdx.graphics.setWindowedMode(width, height);
                break;
            default:
                throw new PixelException("Unsupported ViewMode: " + viewMode);
        }
    }

    @Override
    public ViewMode getViewMode() {
        return Gdx.graphics.isFullscreen() ? ViewMode.FULLSCREEN : ViewMode.WINDOW;
    }

    @Override
    public boolean isMonitorInUse() {
        return this.internalMonitor == Gdx.graphics.getMonitor();
    }
    @Override
    public boolean isMonitorModeChangeSupported() {
        return Gdx.graphics.supportsDisplayModeChange();
    }

}
