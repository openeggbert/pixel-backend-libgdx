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
package com.pixelgamelibrary.backend.libgdx.game;

import com.pixelgamelibrary.api.game.GameWrapper;
import com.pixelgamelibrary.api.OnSetScreenListener;
import com.pixelgamelibrary.backend.libgdx.screen.LibGdxScreen;
import com.pixelgamelibrary.api.game.Game;
import com.pixelgamelibrary.api.screen.Screen;

/**
 *
 * @author robertvokac
 */
public class LibGdxGame extends com.badlogic.gdx.Game implements OnSetScreenListener {

    private final Game game;

    @Override
    public void onSetScreen(Screen screen) {
        setScreen(new LibGdxScreen(screen));
        System.out.println("LibGdxGame:onSetScreen");
    }

    class LibGdxGameWrapper extends GameWrapper {

        private final LibGdxGame libGdxGame;

        public LibGdxGameWrapper(Game gameI, LibGdxGame libGdxGame) {
            super(gameI);
            this.libGdxGame = libGdxGame;
            gameI.setOnSetScreenListener(libGdxGame);
        }

//        @Override
//        public void setScreen(ScreenI screen) {
//            System.out.println("LibGdxGame: setScreen");
//            libGdxGame.setScreen(new LibGdxScreen(screen));
//        }
//
//        @Override
//        public ScreenI getScreen() {
//            System.out.println("LibGdxGame: getScreen");
//            LibGdxScreen screen = (LibGdxScreen) libGdxGame.getScreen();
//            return screen.getPixelScreen();
//        }
        

    }

    public LibGdxGame(Game game) {
        this.game = new LibGdxGameWrapper(game, this);
    }

    @Override
    public void create() {
        game.create();
    }
//
//    @Override
//    public void resize(int width, int height) {
//        game.resize(width, height);
//    }
//private int i = 0;
//    @Override
//    public void render() {
//        i++;
//        System.out.println("Calling LibGdxGame.render " + i);
//        game.render();
//    }
//
//    @Override
//    public void pause() {
//        game.pause();
//    }
//
//    @Override
//    public void resume() {
//        game.resume();
//    }
//
//    @Override
//    public void dispose() {
//        game.dispose();
//    }

}
