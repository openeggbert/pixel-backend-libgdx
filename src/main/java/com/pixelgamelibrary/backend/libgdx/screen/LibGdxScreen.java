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
package com.pixelgamelibrary.backend.libgdx.screen;

import com.pixelgamelibrary.api.ScreenWrapper;
import lombok.Getter;
import com.pixelgamelibrary.api.Screen;

/**
 *
 * @author robertvokac
 */
public class LibGdxScreen extends com.badlogic.gdx.ScreenAdapter {

    @Getter
    private final Screen pixelScreen;

    class LibGdxScreenWrapper extends ScreenWrapper {

        private final LibGdxScreen libGdxScreen;

        public LibGdxScreenWrapper(Screen screen, LibGdxScreen libGdxScreenIn) {
            super(screen);
            this.libGdxScreen = libGdxScreenIn;
        }

    }

    public LibGdxScreen(Screen screen) {
        this.pixelScreen = new LibGdxScreenWrapper(screen, this);
        System.out.println("aaaaaa");
    }

    @Override
    public void render(float delta) {
        System.out.println("LibGdxScreen : render");
        pixelScreen.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        pixelScreen.resize(width, height);
    }

    @Override
    public void show() {
        pixelScreen.show();
    }

    @Override
    public void hide() {
        pixelScreen.hide();
    }

    @Override
    public void pause() {
        pixelScreen.pause();
    }

    @Override
    public void resume() {
        pixelScreen.resume();
    }

    @Override
    public void dispose() {
        pixelScreen.dispose();
    }

}
