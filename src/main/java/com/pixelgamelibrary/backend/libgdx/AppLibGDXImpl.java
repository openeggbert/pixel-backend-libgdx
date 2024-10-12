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
package com.pixelgamelibrary.backend.libgdx;

import com.badlogic.gdx.Application;
import static com.badlogic.gdx.Application.ApplicationType.Android;
import static com.badlogic.gdx.Application.ApplicationType.Desktop;
import static com.badlogic.gdx.Application.ApplicationType.WebGL;
import static com.badlogic.gdx.Application.ApplicationType.iOS;
import com.badlogic.gdx.Gdx;
import com.pixelgamelibrary.api.PixelException;
import com.pixelgamelibrary.api.Platform;
import com.pixelgamelibrary.api.interfaces.App;
import com.pixelgamelibrary.api.game.Game;
import com.pixelgamelibrary.api.PixelFeature;
import com.pixelgamelibrary.api.app.ClipBoard;
import com.pixelgamelibrary.api.app.LogLevel;
import com.pixelgamelibrary.api.app.Preferences;

/**
 *
 * @author robertvokac
 */
public class AppLibGDXImpl implements App {

    private static final String DEFAULT_APP_NAME = "pixel-app";

    private String appName = null;
    private Game game;

    @Override
    public void exit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Platform getPlatform() {
        Application.ApplicationType applicationType = Gdx.app.getType();
        switch (applicationType) {
            case Desktop:
                return Platform.DESKTOP;
            case Android:
                return Platform.ANDROID;
            case WebGL:
                return Platform.WEB;
            case iOS:
                return Platform.IOS;
            default:
                throw new PixelException("Unsupported platform: " + applicationType);
        }
    }

    @Override
    public void log(String tag, String msg) {
        Application app = Gdx.app;
        if (app != null) {
            Gdx.app.log(tag, msg);
        }
    }

    @Override
    public void error(String tag, String msg) {

        Application app = Gdx.app;
        if (app != null) {
            Gdx.app.error(tag, msg);
        }
    }

    @Override
    public void debug(String tag, String msg) {
        Application app = Gdx.app;
        if (app != null) {
            Gdx.app.debug(tag, msg);
        }
    }

    @Override
    public void warn(String tag, String msg) {
        log(msg);
    }

        @Override
    public void fatal(String tag, String msg) {
        error(tag, msg);
    }

    @Override
    public void trace(String tag, String msg) {
        debug(tag, msg);
    }
    
    @Override
    public void setAppName(String appNameIn) {
        if (appNameIn != null) {
            throw new UnsupportedOperationException("App name was already set.");
        }
        this.appName = appNameIn;
    }

    @Override
    public String getAppName() {
        return isAppNameSet() ? appName : DEFAULT_APP_NAME;
    }

    @Override
    public boolean isAppNameSet() {
        return appName != null;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void setLogLevel(LogLevel logLevel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Preferences getPreferences(String preferencesName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isFeatureEnabled(PixelFeature feature) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isMobileDevice() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void runLater(Runnable runnable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ClipBoard getClipBoard() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isFeatureEnabled(String feature) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}
