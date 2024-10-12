package com.pixelgamelibrary.backend.libgdx.files;

import com.pixelgamelibrary.backend.libgdx.files.DesktopAndroidStorage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.pixelgamelibrary.api.Pixel;
import com.pixelgamelibrary.api.Platform;
import com.pixelgamelibrary.api.interfaces.PixelBackend;
import com.pixelgamelibrary.api.files.RegularFileType;
import com.pixelgamelibrary.api.files.StorageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import static org.mockito.Mockito.*;
import com.pixelgamelibrary.api.interfaces.Files;
import com.pixelgamelibrary.api.interfaces.Audio;
import com.pixelgamelibrary.api.interfaces.Graphics;
import com.pixelgamelibrary.api.interfaces.Input;
import com.pixelgamelibrary.api.interfaces.Internal;
import com.pixelgamelibrary.api.interfaces.Net;
import com.pixelgamelibrary.api.interfaces.Utils;
import com.pixelgamelibrary.api.interfaces.App;
import com.pixelgamelibrary.api.game.Game;
import com.pixelgamelibrary.api.PixelFeature;
import com.pixelgamelibrary.api.app.ClipBoard;
import com.pixelgamelibrary.api.app.LogLevel;
import com.pixelgamelibrary.api.app.Preferences;

class DesktopAndroidStorageTest {

    private DesktopAndroidStorage storage;
    private FileHandle mockFileHandle;

       //
    @BeforeAll
    static void setUpBeforeAll() {
        PixelBackend dummyPixelBackend = new PixelBackend() {
            @Override
            public App app() {
                return new App() {
                    @Override
                    public Platform getPlatform() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void exit() {
                    }

                    @Override
                    public void log(String msg) {

                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    public void debug(String msg) {

                    }

                    @Override
                    public void warn(String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setAppName(String appName) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public String getAppName() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public boolean isAppNameSet() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setGame(Game game) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public Game getGame() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
                    public ClipBoard getClipBoard() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public boolean isFeatureEnabled(String feature) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void runLater(Runnable r) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void fatal(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void error(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void log(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void warn(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void debug(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void trace(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                };
            }

            @Override
            public Graphics graphics() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Audio audio() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Input input() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Net net() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Files files() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Utils utils() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Internal internal() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        Pixel.initBackend(dummyPixelBackend);
}
    @BeforeEach
    void setUp() {
        mockFileHandle = mock(FileHandle.class);
        //
        Gdx.files = Mockito.mock(com.badlogic.gdx.Files.class);
           // Mock FileHandle
        //FileHandle mockFileHandle = Mockito.mock(FileHandle.class);
        when(Gdx.files.local(Mockito.anyString())).thenReturn(mockFileHandle);
        
        
     
        //
                
        storage = new DesktopAndroidStorage("testStorage") {
            protected FileHandle createLibGdxFileHandle(String path) {
                return mockFileHandle;
            }

            @Override
            public Platform getPlatform() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }

    @Test
    void testConstructor_NullStorageName_ShouldThrowException() {
        Exception exception = assertThrows(StorageException.class, () -> new DesktopAndroidStorage(null) {
            
            protected FileHandle createLibGdxFileHandle(String path) {
                return mock(FileHandle.class);
            }

            @Override
            public Platform getPlatform() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        assertEquals("storageName == null || storageName.trim().isEmpty()", exception.getMessage());
    }

    @Test
    void testChangeDirectory_NonExistingDirectory_ShouldReturnErrorMessage() {
        when(mockFileHandle.exists()).thenReturn(false);
        String result = storage.changeDirectory("nonExistingDir");
        assertEquals("Directory does not exist: nonExistingDir", result);
    }

    @Disabled @Test
    void testCreateDirectory_ExistingDirectory_ShouldReturnWarning() {
        when(storage.createLibGdxFileHandle(anyString())).thenReturn(mockFileHandle);
        when(mockFileHandle.exists()).thenReturn(true);
        
        
        String result = storage.createDirectory("existingDir");
        assertEquals("Directory already exists: existingDir", result);
    }

    @Test
    void testCreateDirectory_NewDirectory_ShouldCreateDirectory() {
        when(mockFileHandle.exists()).thenReturn(false);
        String result = storage.createDirectory("newDir");
        assertEquals("", result);
        verify(mockFileHandle, times(2)).mkdirs();
    }

    @Test
    void testTouch_ShouldCreateFile() {
        String result = storage.touch("testFile.txt");
        assertEquals("", result);
        verify(mockFileHandle).writeString("", false);
    }

    @Test
    void testRemove_ShouldDeleteFile() {
        when(mockFileHandle.delete()).thenReturn(true);
        boolean result = storage.remove("testFile.txt");
        assertTrue(result);
        verify(mockFileHandle).delete();
    }

    @Test
    void testMove_ShouldMoveFile() {
        storage.move("sourceFile.txt", "targetFile.txt");
        verify(mockFileHandle).moveTo(mockFileHandle);
    }

    @Test
    void testReadString_ShouldReturnFileContent() {
        when(mockFileHandle.readString()).thenReturn("File content");
        String result = storage.readString("testFile.txt");
        assertEquals("File content", result);
    }

    @Disabled @Test
    void testReadBytes_ShouldReturnFileBytes() throws IOException {
        InputStream mockInputStream = mock(InputStream.class);
        when(mockFileHandle.read()).thenReturn(mockInputStream);
        when(mockInputStream.readAllBytes()).thenReturn(new byte[]{1, 2, 3});

        byte[] result = storage.readBytes("testFile.bin");
        assertArrayEquals(new byte[]{1, 2, 3}, result);
    }

    @Test
    void testWriteString_ShouldWriteToFile() {
        String result = storage.writeString("testFile.txt", "Hello World");
        assertEquals("", result);
        verify(mockFileHandle).writeString("Hello World", false);
    }

    @Test
    void testExists_ShouldReturnTrueIfFileExists() {
        when(mockFileHandle.exists()).thenReturn(true);
        assertTrue(storage.exists("testFile.txt"));
    }

    @Test
    void testIsDirectory_ShouldReturnTrueForDirectory() {
        when(mockFileHandle.isDirectory()).thenReturn(true);
        assertTrue(storage.isDirectory("testDir"));
    }

    @Test
    void testIsFile_ShouldReturnTrueForFile() {
        when(mockFileHandle.isDirectory()).thenReturn(false);
        assertTrue(storage.isFile("testFile.txt"));
    }

    @Disabled @Test
    void testDebug_ShouldReturnFileTree() {
        when(mockFileHandle.isDirectory()).thenReturn(true);
        when(mockFileHandle.name()).thenReturn("root");

        String result = storage.debug();
        assertEquals("root\n", result);
    }

    @Test
    void testRemoveDirectory_ShouldReturnTrueIfDirectoryDeleted() {
        when(mockFileHandle.deleteDirectory()).thenReturn(true);
        assertTrue(storage.removeDirectory("testDir"));
    }

    @Test
    void testGetRegularFileType_ShouldReturnTextForTextFile() {
        when(mockFileHandle.readString()).thenReturn("This is text");
        assertEquals(RegularFileType.TEXT, storage.getRegularFileType("testFile.txt"));
    }

    @Disabled @Test
    void testGetRegularFileType_ShouldReturnBinaryForBinaryFile() {
        when(mockFileHandle.readString()).thenThrow(new RuntimeException());
        assertEquals(RegularFileType.BINARY, storage.getRegularFileType("testFile.bin"));
    }
}
